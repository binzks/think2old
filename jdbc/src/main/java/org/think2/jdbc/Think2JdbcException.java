package org.think2.jdbc;

/**
 * Created by zhoubin on 15/11/27.
 * jdbc类的异常类，主要用于抓取抛出那些调用必定会打印异常的异常
 */
public class Think2JdbcException extends RuntimeException {

    public Think2JdbcException() {
        super();
    }

    public Think2JdbcException(String message) {
        super(message);
    }

    public Think2JdbcException(String message, Throwable cause) {
        super(message, cause);
    }

    public Think2JdbcException(Throwable cause) {
        super(cause);
    }

    protected Think2JdbcException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
