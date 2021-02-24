package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Codes;


public interface ICodesService extends IService<Codes> {

    Codes getByMaxId();

    Codes getCodeByOrderId(Integer orderId);

}
