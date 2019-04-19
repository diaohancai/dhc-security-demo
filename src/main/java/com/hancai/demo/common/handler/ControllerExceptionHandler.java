package com.hancai.demo.common.handler;

import com.hancai.demo.common.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller统一异常处理器
 *
 * @author diaohancai
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * rest api 指定MyException的异常处理
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleMyException(MyException ex) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("errMsg", ex.getMessage());
        return result;
    }

}
