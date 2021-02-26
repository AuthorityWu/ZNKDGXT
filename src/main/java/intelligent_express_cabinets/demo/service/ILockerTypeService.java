package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.LockerType;
import intelligent_express_cabinets.demo.entity.Lockers;

import java.util.List;


public interface ILockerTypeService extends IService<LockerType> {
    Lockers createByLockerType(Integer lockerTypeId, String longitude, String latitude);
    List<Lockers> getLockerByLockerType(LockerType lockerType);
}
