package com.lip.exception;

/**
 * Created by Lip on 2016/8/16 0016.
 */
public class XiangGongException extends RuntimeException{
    public XiangGongException(String msg)
    {
        super(msg);
    }
    public XiangGongException(Throwable throwable)
    {
        super(throwable);
    }
}
