package com.example.demo.controller;

import com.example.demo.dto.ResultDTO;
import com.example.demo.enums.GeneralResponseEnums;
import com.example.demo.qo.Project;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @ClassName ProjectController
 * @Description TODO
 * @Author Halo
 **/
@Controller
@RequestMapping("/project")
public class ProjectController extends BaseController{

    @Autowired
    private ProjectService projectService;


    /**
     * 添加项目信息
     * @param project
     * @return
     */
    @PostMapping("/add")
    public ResultDTO<Boolean> addProject(Project project){

        project.getProjectMsg().setCreateTime(new Date(System.currentTimeMillis()));
        project.getProjectMsg().setCreateId(getSessionUserid());
        return ResultDTO.successOf(projectService.addProject(project), GeneralResponseEnums.SUCCESS);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/deleteByIds")
    public ResultDTO<Boolean> deleteProjectByIds(int[] ids){
        return ResultDTO.successOf(projectService.deleteByIds(ids),GeneralResponseEnums.SUCCESS);
    }

}
