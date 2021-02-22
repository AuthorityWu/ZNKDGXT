package intelligent_express_cabinets.demo.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

/**  * 统一API响应结果封装  */
@Data // 非空返回
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class Result {
    private int code;
    private String message = "success";
    private Object data;
    public Result setCode(ResultCode resultCode){
        this.code = resultCode.code;
        return this;
    }
    public Result setMessage(String message){
        this.message = message;
        return this;
    }
    public Result setData(Object data){
        this.data = data;
        return this;
    }
}
