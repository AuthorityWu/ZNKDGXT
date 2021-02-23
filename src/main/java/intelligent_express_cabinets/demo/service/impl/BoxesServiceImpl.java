package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.BoxesMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Codes;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.entity.Missions;
import intelligent_express_cabinets.demo.service.IBoxesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@Service
public class BoxesServiceImpl extends ServiceImpl<BoxesMapper, Boxes> implements IBoxesService {
    @Autowired
    CodesServiceImpl codesService;
    @Autowired
    LockersServiceImpl lockersService;
    @Autowired
    MissionsServiceImpl missionsService;

    public void bindCode(Boxes boxes, Integer codeId){

        Codes codes=codesService.getById(codeId);
        if (codes!=null){
            codes.setBoxId(boxes.getBoxId());
            codes.setCodeStatus(2);
            codesService.saveOrUpdate(codes);
        }
    }
    public void unBindCode(Boxes boxes){

        QueryWrapper<Codes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("box_id",boxes.getBoxId());

        Codes codes=codesService.getOne(queryWrapper);
        if (codes!=null){
            codes.setBoxId(0);
            codes.setCodeStatus(1);
            codesService.saveOrUpdate(codes);
        }
    }

    public Boxes findByLocker(Integer LockerId,Integer lockerBoxId){
        QueryWrapper<Boxes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",LockerId)
                    .eq("locker_box_id",lockerBoxId);
        return this.getOne(queryWrapper);
    }
    public void useBox(Boxes boxes){
        boxes.setBoxStatus(2);
        this.saveOrUpdate(boxes);

        QueryWrapper<Lockers> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",boxes.getLockerId());
        Lockers lockers=lockersService.getOne(queryWrapper);
        switch (boxes.getBoxSize()){
            case 1:lockers.setSmallBoxAbleNumber(lockers.getSmallBoxAbleNumber()-1);
                    break;
            case 2:lockers.setMiddleBoxAbleNumber(lockers.getMiddleBoxAbleNumber()-1);
            break;
            case 3:lockers.setBigBoxAbleNumber(lockers.getBigBoxAbleNumber()-1);
        }
        lockersService.saveOrUpdate(lockers);

    }
    public void unUseBox(Boxes boxes){
        boxes.setBoxStatus(1);
        this.saveOrUpdate(boxes);

        QueryWrapper<Lockers> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",boxes.getLockerId());
        Lockers lockers=lockersService.getOne(queryWrapper);
        switch (boxes.getBoxSize()){
            case 1:lockers.setSmallBoxAbleNumber(lockers.getSmallBoxAbleNumber()+1);
                break;
            case 2:lockers.setMiddleBoxAbleNumber(lockers.getMiddleBoxAbleNumber()+1);
                break;
            case 3:lockers.setBigBoxAbleNumber(lockers.getBigBoxAbleNumber()+1);
        }
        lockersService.saveOrUpdate(lockers);
    }

    public void reportBroken(Boxes boxes){
        boxes.setBoxStatus(0);
        this.saveOrUpdate(boxes);

        QueryWrapper<Lockers> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",boxes.getLockerId());
        Lockers lockers=lockersService.getOne(queryWrapper);
        switch (boxes.getBoxSize()){
            case 1:lockers.setSmallBoxAbleNumber(lockers.getSmallBoxAbleNumber()-1);
                break;
            case 2:lockers.setMiddleBoxAbleNumber(lockers.getMiddleBoxAbleNumber()-1);
                break;
            case 3:lockers.setBigBoxAbleNumber(lockers.getBigBoxAbleNumber()-1);
                break;
        }
        lockersService.saveOrUpdate(lockers);

        Missions missions=new Missions();
        missions.setLockerId(lockers.getLockerId());
        missions.setMissionType(1);
        missions.setMissionContent("柜机编号#"+lockers.getLockerId()+" 柜子编号#"+boxes.getLockerBoxId()+"的柜子已被报告损坏");
        missions.setMissionUrgency(3);
        missions.setMissionStartTime(LocalDateTime.now());
        missions.setMissionStatus(1);
        missionsService.save(missions);



    }
}
