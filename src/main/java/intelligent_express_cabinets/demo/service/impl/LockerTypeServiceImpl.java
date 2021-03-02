package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.LockerTypeMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.service.IBoxesService;
import intelligent_express_cabinets.demo.service.ILockerTypeService;
import intelligent_express_cabinets.demo.service.ILockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LockerTypeServiceImpl extends ServiceImpl<LockerTypeMapper, LockerType> implements ILockerTypeService {
    @Autowired
    IBoxesService boxesService;
    @Autowired
    ILockersService lockersService;

    @Override
    public Lockers createByLockerType(Integer lockerTypeId,String longitude,String latitude){
        LockerType lockerType=this.getById(lockerTypeId);
        Lockers lockers=new Lockers();
        lockers.setLockerTypeId(lockerTypeId);
        lockers.setLockerStatus(1);
        lockers.setLongitude(longitude);
        lockers.setLatitude(latitude);
        lockers.setSmallBoxAbleNumber(lockerType.getSmallBoxNumber());
        lockers.setMiddleBoxAbleNumber(lockerType.getMiddleBoxNumber());
        lockers.setBigBoxAbleNumber(lockerType.getBigBoxNumber());
        lockersService.save(lockers);
        int num=0;
        for (; num < lockers.getSmallBoxAbleNumber(); num++) {
            Boxes boxes=new Boxes();
            boxes.setLockerId(lockers.getLockerId());
            boxes.setLockerBoxId(num+1);
            boxes.setBoxSize(1);
            boxes.setBoxStatus(1);
            boxesService.save(boxes);
        }
        for (;num < lockers.getSmallBoxAbleNumber()+lockers.getMiddleBoxAbleNumber(); num++) {
            Boxes boxes=new Boxes();
            boxes.setLockerId(lockers.getLockerId());
            boxes.setLockerBoxId(num+1);
            boxes.setBoxSize(2);
            boxes.setBoxStatus(1);
            boxesService.save(boxes);
        }
        for (;num < lockers.getSmallBoxAbleNumber()
                +lockers.getMiddleBoxAbleNumber()
                +lockers.getBigBoxAbleNumber(); num++) {
            Boxes boxes=new Boxes();
            boxes.setLockerId(lockers.getLockerId());
            boxes.setLockerBoxId(num+1);
            boxes.setBoxSize(3);
            boxes.setBoxStatus(1);
            boxesService.save(boxes);
        }
        return lockers;
    }

    @Override
    public List<Lockers> getLockerByLockerType(LockerType lockerType) {
        QueryWrapper<Lockers> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_type_id",lockerType.getLockerTypeId());
        return lockersService.list(queryWrapper);

    }
}
