package intelligent_express_cabinets.demo.entity;

/**
 * 响应结果封装
 */
public class ResultResponse {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_NOT_FOUND_MESSAGE = "NOT FOUND";
    // 只返回状态
     public static Result success() {
         return new Result()
                 .setCode(ResultCode.SUCCESS)
                 .setMessage(DEFAULT_SUCCESS_MESSAGE);
     }
     // 成功返回数据
    public static Result success(Object data) {
         return new Result()
                 .setCode(ResultCode.SUCCESS)
                 .setMessage(DEFAULT_SUCCESS_MESSAGE)
                 .setData(data);
     }
     // 失败
    public static Result fail(String message) {
         return new Result()
                 .setCode(ResultCode.FAIL)
                 .setMessage(message);
     }
    // 未找到
    public static Result notFound() {
        return new Result()
                .setCode(ResultCode.NOT_FOUND)
                .setMessage(DEFAULT_NOT_FOUND_MESSAGE);
    }

}