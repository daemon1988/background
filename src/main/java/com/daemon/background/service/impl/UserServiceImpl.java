package com.daemon.background.service.impl;

import com.daemon.background.annotation.DataSourceAnnotation;
import com.daemon.background.dao.UserDao;
import com.daemon.background.dynamic.DynamicDataSourceContextHolder;
import com.daemon.background.model.UserDTO;
import com.daemon.background.model.UserVO;
import com.daemon.background.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author dell
 * Created on 2019-07-25 14:37
 **/
@Service(value = "UserService")
@Transactional
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public List<Map<String, String>> selectList() {
        return userDao.selectList();
    }

    @Override
    public List<Map<String, String>> selectList1() {
        return userDao.selectList1();
    }

    @Override
    @DataSourceAnnotation("master")
    public int insertUser(UserDTO userDTO) {
        logger.info("username:"+userDTO.getUserName()+",password:"+userDTO.getPassword());
        int i = 1/ 0;
        return userDao.insertUser(userDTO);
    }

    @Override
    @DataSourceAnnotation("slave1")
    public int insertUser1(UserDTO userDTO) {
        logger.info("username:"+userDTO.getUserName()+",password:"+userDTO.getPassword());
        return userDao.insertUser1(userDTO);
    }

    @Override
    public UserVO selectById(UserDTO userDTO) {

        return userDao.selectById(userDTO);
    }
}
