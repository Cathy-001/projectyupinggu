package com.example.demo.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.SysUser;

import java.util.List;

public interface SysUserDao extends BaseMapper<SysUser> {

    List<SysUser> getList();
}
