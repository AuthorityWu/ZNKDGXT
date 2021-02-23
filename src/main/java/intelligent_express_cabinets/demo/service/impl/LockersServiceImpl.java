package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.LockersMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.service.ILockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LockersServiceImpl extends ServiceImpl<LockersMapper, Lockers> implements ILockersService {
    @Autowired
    BoxesServiceImpl boxesService;
    @Autowired
    LockerTypeServiceImpl lockerTypeService;

    public void createByLockerType(Integer lockerTypeId,Float longitude,Float latitude){
        LockerType lockerType=lockerTypeService.getById(lockerTypeId);
        Lockers lockers=new Lockers();
        lockers.setLockerStatus(1);
        lockers.setLongitude(longitude);
        lockers.setLatitude(latitude);
        lockers.setSmallBoxAbleNumber(lockerType.getSmallBoxNumber());
        lockers.setMiddleBoxAbleNumber(lockerType.getMiddleBoxNumber());
        lockers.setBigBoxAbleNumber(lockerType.getBigBoxNumber());
        this.save(lockers);
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
    }
}
