package com.example.demo.controller;

import com.example.demo.service.SysUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName SysUserController
 * @Description TODO
 * @Author Halo
 **/
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    private SysUserService sysUserService;
}
