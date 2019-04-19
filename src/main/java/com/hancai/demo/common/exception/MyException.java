package com.hancai.demo.common.exception;

/**
 * 自定义异常
 *
 * @author diaohancai
 */
public class MyException extends RuntimeException {

    public MyException(String errMsg) {
        super(errMsg);
    }

}
