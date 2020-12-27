package com.daemon.background.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author dell
 * Created on 2019-11-07 11:43
 **/
@ApiModel(value="用户返回类",description = "用户返回类")
public class UserVO {

    @ApiModelProperty(example = "姓名")
    private String userName;

    @ApiModelProperty(example = "密码")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String username) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
