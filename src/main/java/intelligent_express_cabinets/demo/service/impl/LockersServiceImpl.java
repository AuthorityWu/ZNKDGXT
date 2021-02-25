package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.LockersMapper;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Lockers;
import intelligent_express_cabinets.demo.service.IBoxesService;
import intelligent_express_cabinets.demo.service.ILockersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LockersServiceImpl extends ServiceImpl<LockersMapper, Lockers> implements ILockersService {

    @Autowired
    private LockersMapper lockersMapper;

    @Autowired
    IBoxesService boxesService;

    @Override
    public List<Lockers> getLockerBoxes(int boxSize) {
        return lockersMapper.getLockerBoxes(boxSize);
    }

    @Override
    public List<Boxes> getBoxByLockerId(Integer lockerId) {
        QueryWrapper<Boxes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",lockerId);
        return boxesService.list(queryWrapper);

    }

    @Override
    public void delete(Integer lockerId) {
        QueryWrapper<Boxes> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("locker_id",lockerId);
        boxesService.remove(queryWrapper);
        this.removeById(lockerId);
    }


}
