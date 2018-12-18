package cn.ccsu.score.entity;

import cn.ccsu.score.enums.CodeMessage;
import com.alibaba.fastjson.JSONObject;

public class Result<T>{
    private int code;
    private String msg;
    private T data;

    private Result(T data) {
        this.code = CodeMessage.SUCCESS.getResultCode();
        this.msg = CodeMessage.SUCCESS.getResultMessage();
        this.data = data;
    }

    private Result(CodeMessage codeMessage){
        this.code = codeMessage.getResultCode();
        this.msg = codeMessage.getResultMessage();
    }

    public static <T> Result<T> success(T data){
        return new Result<>(data);
    }

    public static <T> Result<T> error(CodeMessage codeMessage){
        return new Result<>(codeMessage);
    }

    @Override
    public String toString() {
        String dataJson = JSONObject.toJSONString(data);

        return "{" +
                "\"code\":" + code +
                ",\"msg\":" + "\"" + msg + "\"" +
                ",\"data\":" + dataJson +
                '}';
    }
}
