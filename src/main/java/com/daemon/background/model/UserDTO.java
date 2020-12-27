package com.daemon.background.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author dell
 * Created on 2019-11-07 11:43
 **/
@ApiModel(value = "用户实体类", description = "用户实体类")
public class UserDTO implements Serializable {

    @NotNull(message = "主键不能为空")
    @ApiModelProperty(example = "主键id")
    private Integer id;

    @ApiModelProperty(example="姓名")
    private String userName;

    @ApiModelProperty(example="密码")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
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
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
