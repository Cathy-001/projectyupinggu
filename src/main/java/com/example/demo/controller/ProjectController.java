package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.dto.ResultDTO;
import com.example.demo.enums.GeneralResponseEnums;
import com.example.demo.qo.Project;
import com.example.demo.qo.ProjectMsgQO;
import com.example.demo.service.ProjectService;
import com.example.demo.vo.ProjectMsgVO;
import com.example.demo.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 列表查询
     * @param projectMsgQO
     * @return 返回项目基本信息列表
     */
    @GetMapping("/getList")
    public ResultDTO<IPage<ProjectMsgVO>> getListByQO(ProjectMsgQO projectMsgQO){

        if(projectMsgQO.getCurrent() == null || projectMsgQO.getSize() == null){
            return ResultDTO.errorOf(GeneralResponseEnums.REQUEST_ERROR);
        }
        return ResultDTO.successOf(projectService.getListByQO(projectMsgQO),GeneralResponseEnums.SUCCESS);
    }

    /**
     * 根据id进行查询
     * @param id 醒目基本信息id
     * @return 返回项目信息
     */
    @GetMapping("/getProject")
    public ResultDTO<ProjectVO> getProjectById(Integer id){

        return ResultDTO.successOf(projectService.getProjectById(id),GeneralResponseEnums.SUCCESS);
    }

    /**
     * 修改项目信息
     * @param project
     * @return
     */
    @PostMapping("/update")
    public ResultDTO<Boolean> updateById(Project project){

        project.getProjectMsg().setCreateId(null);
        project.getProjectMsg().setCreateTime(null);

        return ResultDTO.successOf(projectService.patchById(project),GeneralResponseEnums.SUCCESS);
    }

}
