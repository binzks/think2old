package org.think2.context;

/**
 * Created by zhoubin on 15/11/27.
 * context类的异常类，主要用于抓取抛出那些调用必定会打印异常的异常
 */
public class Think2ContextException extends RuntimeException {

    public Think2ContextException() {
        super();
    }

    public Think2ContextException(String message) {
        super(message);
    }

    public Think2ContextException(String message, Throwable cause) {
        super(message, cause);
    }

    public Think2ContextException(Throwable cause) {
        super(cause);
    }

    protected Think2ContextException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
