package com.scy.controller;

import com.scy.modules.common.config.JwtUserDetailsService;
import com.scy.modules.common.config.jwt.JwtTokenTemplate;
import com.scy.param.JwtParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * @desc:
 * @author: Suocaiyuan
 * @date: 2019-12-14 14:46
 **/
@RestController
@RequestMapping(value = "auth")
public class HelloJwtController {

    @Autowired
    private JwtTokenTemplate jwtTokenTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "login")
    public String login(@RequestBody JwtParam body) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(body.getUsername());
        return jwtTokenTemplate.generateToken(userDetails);
    }

    @GetMapping(value = "hello")
    public String hello() {
        return "hello jwt";
    }
}
