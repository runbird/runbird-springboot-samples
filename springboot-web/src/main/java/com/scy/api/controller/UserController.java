package com.scy.api.controller;

import com.scy.api.annoation.LoginUser;
import com.scy.vo.LoginUserVO;
import com.scy.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名： UserController <br>
 * 描述：TODO <br>
 * 创建日期： 2019/12/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/")
    public List<UserVO> getUserList() {
        List<UserVO> users = new ArrayList<>(2);
        users.add(UserVO.builder().id(1L).name("test1").age(18).build());
        users.add(UserVO.builder().id(2L).name("test2").age(33).build());
        return users;
    }

    @GetMapping("/id")
    public void getLoginUserInfo(@LoginUser LoginUserVO userVO) {
        log.info(userVO.toString());
    }
}
