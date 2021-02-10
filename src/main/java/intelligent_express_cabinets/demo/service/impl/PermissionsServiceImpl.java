package intelligent_express_cabinets.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import intelligent_express_cabinets.demo.dao.PermissionsMapper;
import intelligent_express_cabinets.demo.entity.Permissions;
import intelligent_express_cabinets.demo.service.IPermissionsService;
import org.springframework.stereotype.Service;


@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

}
