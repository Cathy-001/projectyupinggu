package com.example.demo.controller;

import com.example.demo.bean.ProjectMsg;
import com.example.demo.dto.ResultDTO;
import com.example.demo.enums.GeneralResponseEnums;
import com.example.demo.service.ProjectMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProjectMsgController
 * @Description TODO
 * @Author Halo
 **/
@RestController()
@RequestMapping("/projectMsg")
public class ProjectMsgController extends BaseController{

    @Autowired
    private ProjectMsgService projectMsgService;

    /**
     * 添加项目信息
     * @param projectMsg
     * @return
     */
    @PostMapping("/add")
    public ResultDTO<Boolean> addProjectMsg(ProjectMsg projectMsg){

        projectMsg.setCreateTime(new Date());
        projectMsg.setCreatorId(getSessionUserid());
        return ResultDTO.successOf(projectMsgService.addProjectMsg(projectMsg),GeneralResponseEnums.SUCCESS);
    }

    /**
     * 列表查询
     * @return
     */
    @PostMapping("/list")
    public ResultDTO<List<ProjectMsg>> getList(){

        return ResultDTO.successOf(projectMsgService.getList(), GeneralResponseEnums.SUCCESS);
    }
}
