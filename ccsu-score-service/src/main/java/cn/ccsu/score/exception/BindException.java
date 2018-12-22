package cn.ccsu.score.exception;

import cn.ccsu.score.entity.Result;
import cn.ccsu.score.enums.CodeMessage;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class BindException {

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest request,Exception e){
        e.printStackTrace();
        if (e instanceof org.springframework.validation.BindException){
            org.springframework.validation.BindException exception = (org.springframework.validation.BindException) e;
            List<ObjectError> errorList = exception.getAllErrors();
            return Result.error(CodeMessage.BIND_ERROR.fillArgs(errorList.get(0).getDefaultMessage())).toString();
        }else {
            return null;
        }
    }
}
