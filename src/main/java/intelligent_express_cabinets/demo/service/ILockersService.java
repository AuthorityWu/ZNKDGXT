package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Lockers;

import java.util.List;


public interface ILockersService extends IService<Lockers> {

    List<Lockers> getLockerBoxes(int boxSize);
}
