package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.LockersMapper;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.service.ILockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LockersServiceImpl extends ServiceImpl<LockersMapper, Lockers> implements ILockersService {

    @Autowired
    private LockersMapper lockersMapper;

    @Override
    public List<Lockers> getLockerBoxes(int boxSize) {
        return lockersMapper.getLockerBoxes(boxSize);
    }
}
