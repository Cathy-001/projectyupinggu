package com.example.demo.service;

import com.example.demo.bean.SysUser;
import com.example.demo.dao.SysUserDao;
import com.example.demo.enums.GeneralResponseEnums;
import com.example.demo.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author Halo
 **/
@Service
public class SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    /**
     * 添加用户
     * @param user
     * @return
     */
    public Boolean addSysUser(SysUser user){

        int result = sysUserDao.insert(user);

        if(result == 0){
            throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
        }

        return true;
    }

    /**
     * 列表查询
     * @return
     */
    public List<SysUser> getList(){

        return sysUserDao.getList();
    }

}
