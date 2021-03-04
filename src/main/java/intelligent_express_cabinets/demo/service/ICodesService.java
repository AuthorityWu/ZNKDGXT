package intelligent_express_cabinets.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import intelligent_express_cabinets.demo.entity.Boxes;
import intelligent_express_cabinets.demo.entity.Codes;

import javax.swing.*;


public interface ICodesService extends IService<Codes> {

    Codes getByMaxId();

    Codes getCodeByOrderId(Integer orderId);

    Codes getByBox(Boxes boxes);

    Codes getNotUsed();

}
