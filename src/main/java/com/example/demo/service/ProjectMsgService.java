package com.example.demo.service;

import com.example.demo.bean.ProjectMsg;
import com.example.demo.dao.ProjectMsgDao;
import com.example.demo.enums.GeneralResponseEnums;
import com.example.demo.exception.CustomizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProjectMsgService
 * @Description TODO
 * @Author Halo
 **/
@Service
public class ProjectMsgService {

    @Autowired
    private ProjectMsgDao projectMsgDao;


    /**
     * 添加项目基本信息
     * @param projectMsg
     * @return
     */
    public Boolean addProjectMsg(ProjectMsg projectMsg){

        int result = projectMsgDao.insert(projectMsg);

        if(result == 0){
            throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
        }

        return true;
    }


}
