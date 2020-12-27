package com.daemon.background.service;

import com.daemon.background.model.UserDTO;
import com.daemon.background.model.UserVO;

import java.util.List;
import java.util.Map;

/**
 * @author dell
 * Created on 2019-07-25 14:36
 **/
public interface UserService {

    public List<Map<String, String>> selectList();

    public List<Map<String, String>> selectList1();

    public int insertUser(UserDTO userDTO);

    public int insertUser1(UserDTO userDTO);

    public UserVO selectById(UserDTO userDTO);
}
