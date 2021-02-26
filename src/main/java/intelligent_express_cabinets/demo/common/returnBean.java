package intelligent_express_cabinets.demo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class returnBean {

    /**
     * code:统一返回json信息中的状态码
     * messsage:统一返回json信息中的提示信息
     * object:统一返回json信息中的数据
     */
    private long code;
    private String message;
    private Object object;

    /**
     * 成功返回结果（形参只有message）
     */
    public static returnBean success(String message){
        return new returnBean(200,message,null);
    }

    /**
     * 成功返回结果（形参有message和object）
     */
    public static returnBean success(String message,Object object){
        return new returnBean(200,message,object);
    }

    /**
     * 失败返回结果（形参只有message）
     */
    public static returnBean error(String message){
        return new returnBean(400,message,null);
    }

    /**
     * 失败返回结果（形参有message和object）
     */
    public static returnBean error(String message,Object object){
        return new returnBean(400,message,object);
    }

}
