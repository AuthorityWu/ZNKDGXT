package intelligent_express_cabinets.demo.entity;



/**
 * 响应码枚举，对应HTTP状态码
 */
public enum ResultCode {
    SUCCESS(200),//成功
    FAIL(400),//失败
    UNAUTHORIZED(401),//未认证（签名错误）
    FORBIDDEN(403),//禁止访问
    NOT_FOUND(404),//接口不存在
    INTERNAL_SERVER_ERROR(500);//服务器内部错误

    public int code;
    ResultCode(int code) {
        this.code = code;
    }
}
