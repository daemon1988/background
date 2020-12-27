package com.daemon.background.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回结果
 * @author dell
 * Created on 2019-07-26 17:25
 **/
@ApiModel(value="数据模型", description = "数据模型")
public class ResultModel<T> {
    /**
     * 返回码
     */
    @ApiModelProperty(example = "返回码")
    private int code;

    /**
     * 返回结果描述
     */
    @ApiModelProperty(example = "返回结果描述")
    private String message;

    /**
     * 返回内容
     *//*
    private Object content;*/

    /**
     * 泛型返回内容
     */
    private T data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /*public void setContent(Object content) {
        this.content = content;
    }*/

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /*public Object getContent() {
        return content;
    }*/

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultModel() {

    }

    public ResultModel(int code) {
        this.code = code;
        this.message = "";
        this.data = (T) "";
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = (T) "";
    }

    public ResultModel(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = (T) "";
    }

    public ResultModel(ResultStatus status, Object data) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = (T) data;
    }

    public static ResultModel ok(Object data) {
        return new ResultModel(ResultStatus.SUCCESS, data);
    }

    public static ResultModel ok(ResultStatus resultStatus) {
        return new ResultModel(resultStatus);
    }

    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    public static ResultModel error(ResultStatus error, Object data) {
        return new ResultModel(error, data);
    }

    /**************CALL FOR API****************/

    public static ResultModel success() {
        return new ResultModel(200);
    }

    public static ResultModel success(Object data) {
        return new ResultModel(200, "", data);
    }

    public static ResultModel fail(int code, String message) {
        return new ResultModel(code, message);
    }
}
