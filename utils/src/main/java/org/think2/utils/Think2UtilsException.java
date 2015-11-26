package org.think2.utils;

/**
 * Created by zhoubin on 15/11/26.
 * Utils工具类的异常类，主要用于抓取抛出那些调用必定会打印异常的异常
 */
public class Think2UtilsException extends RuntimeException {

    public Think2UtilsException() {
        super();
    }

    public Think2UtilsException(String message) {
        super(message);
    }

    public Think2UtilsException(String message, Throwable cause) {
        super(message, cause);
    }

    public Think2UtilsException(Throwable cause) {
        super(cause);
    }

    protected Think2UtilsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
