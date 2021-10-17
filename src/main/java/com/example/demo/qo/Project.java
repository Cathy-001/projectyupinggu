package com.example.demo.qo;

import com.example.demo.bean.*;
import lombok.Data;

import java.util.List;

/**
 * @ClassName Project
 * @Description 项目信息
 * @Author Halo
 **/
@Data
public class Project {

    /**
     * 项目基本信息
     */
    private ProjectMsg projectMsg;

    /**
     * 合作单位
     */
    private List<CooperationUnit> cooperationUnitList;

    /**
     * 单位基本情况
     */
    private Company company;

    /**
     * 项目人员
     */
    private List<ProjectMember> projectMemberList;

    /**
     * 项目进度计划
     */
    private List<ProjectPlan> projectPlanList;
}
