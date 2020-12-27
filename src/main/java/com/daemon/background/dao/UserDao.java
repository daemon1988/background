package com.daemon.background.dao;

import com.daemon.background.model.UserDTO;
import com.daemon.background.model.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author dell
 * Created on 2019-07-25 11:25
 **/
@Repository
public interface UserDao {

    @Select("select * from sys_user")
    public List<Map<String, String>> selectList();

    @Select("select * from sys_user")
    public List<Map<String, String>> selectList1();

    @Insert({"insert into user (username, password) values (#{userName}, #{password})"})
    public int insertUser(UserDTO userDTO);

    @Insert({"insert into user (username, password) values (#{userName}, #{password})"})
    public int insertUser1(UserDTO userDTO);

    @Select({"select username, password from user where id = #{id}"})
    public UserVO selectById(UserDTO userDTO);
}
