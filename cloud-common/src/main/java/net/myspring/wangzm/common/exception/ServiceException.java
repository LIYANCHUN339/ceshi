/*******************************************************************************
 * Copyright (c) 2005, 2014 myspring.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package net.myspring.wangzm.common.exception;

/**
 * Service层公用的Exception.
 * <p>
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 *
 * @author calvin
 */
public class ServiceException extends RuntimeException {

    private String errCode;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, String errCode) {
        super(message);
        this.errCode = errCode;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

}
