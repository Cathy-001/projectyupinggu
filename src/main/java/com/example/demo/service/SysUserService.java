package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.bean.ProjectMsg;
import com.example.demo.bean.SysUser;
import com.example.demo.dao.ProjectMsgDao;
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

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectMsgDao projectMsgDao;

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
     * 注销当前用户
     * @param id 用户id
     * @return
     */
    public Boolean deleteSysUser(Long id){

        //删除该用户的提交的项目记录
        LambdaQueryWrapper<ProjectMsg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ProjectMsg::getCreateId,id);
        List<ProjectMsg> projectMsgList = projectMsgDao.selectList(queryWrapper);

        int[] ids = new int[projectMsgList.size()];
        int i = 0;
        for (ProjectMsg projectMsg : projectMsgList) {
            ids[i++] = projectMsg.getId();
        }

        Boolean ret = projectService.deleteByIds(ids);
        if(ret == false){
            throw new CustomizeException(GeneralResponseEnums.DELETE_FAILED);
        }

        //删除用户本身
        int result = sysUserDao.deleteById(id);
        if(result == 0){
            throw new CustomizeException(GeneralResponseEnums.DELETE_FAILED);
        }

        return true;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public Boolean updateById(SysUser user){

        int ret = sysUserDao.updateById(user);
        if(ret == 0){
            throw new CustomizeException(GeneralResponseEnums.UPDATE_FAILED);
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
