package com.daemon.background.controller;

import com.alibaba.fastjson.JSONObject;
import com.daemon.background.model.UserDTO;
import com.daemon.background.model.UserVO;
import com.daemon.background.rabbitMQ.ProducerCallBack1;
import com.daemon.background.service.UserService;
import com.daemon.background.util.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

//import com.daemon.background.rabbitMQ.ProducerCallBack;

//import com.daemon.background.rabbitMQ.Producer;

/**
 * @author dell
 * Created on 2019-07-25 14:38
 **/
@Api(value = "UserController", tags = "测试接口")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProducerCallBack1 producer;
    //private Producer producer;

    @ApiOperation(value="获取全部信息", notes="获取全部信息")
    @GetMapping("/getAll")
    public ResultModel<UserVO> getAll() {
        
        List<Map<String, String>> list = userService.selectList();
        if (list != null && list.size() > 0) {
            JSONObject obj = null;
            for (Map<String, String> map : list) {
                obj = new JSONObject();
                obj.put("username", map.get("username"));
                obj.put("password", map.get("password"));
            }
            return ResultModel.ok(obj);
        } else {
            return ResultModel.ok();
        }
    }

    @ApiIgnore
    @ApiOperation(value="获取全部信息1", notes="获取全部信息1")
    @GetMapping("/getAll1")
    public ResultModel getAll1() {

        List<Map<String, String>> list = userService.selectList1();
        if (list != null && list.size() > 0) {
            JSONObject obj = null;
            for (Map<String, String> map : list) {
                obj = new JSONObject();
                obj.put("username", map.get("username"));
                obj.put("password", map.get("password"));
            }
            return ResultModel.ok(obj);
        } else {
            return ResultModel.ok();
        }
    }

    @ApiOperation(value="发送消息", notes = "发送消息")
    @PostMapping("/sendMsg")
    public ResultModel<String> sendMsg() {
        UserDTO user = new UserDTO();
        user.setUserName("zhangsan");
        user.setPassword("123456");
        //producer.send(user);
        producer.send("Hello World!");
        return ResultModel.ok();
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/insert")
    public ResultModel<UserVO> insertUser(@RequestBody UserDTO userDTO) {
        int i = userService.insertUser(userDTO);
        return ResultModel.ok();
    }

    @ApiOperation(value = "新增用户1", notes = "新增用户1")
    @PostMapping("/insert1")
    public ResultModel<UserVO> insertUser1(@RequestBody UserDTO userDTO) {
        int i = userService.insertUser1(userDTO);
        return ResultModel.ok();
    }

    @ApiOperation(value = "新增用户1", notes = "新增用户1")
    @PostMapping("/select")
    public ResultModel<UserVO> selectById(@Valid @RequestBody UserDTO userDTO) {
        UserVO vo = userService.selectById(userDTO);
        return ResultModel.ok();
    }
}
