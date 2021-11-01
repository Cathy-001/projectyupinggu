package com.example.demo.vo;

import com.example.demo.bean.*;
import lombok.Data;

import java.util.List;

/**
 * @ClassName ProjectVO
 * @Description TODO
 * @Author Halo
 **/
@Data
public class ProjectVO {

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
     * 项目负责人
     */
    private ProjectMember leader;

    /**
     * 项目成员
     */
    private List<ProjectMember> projectMemberList;

    /**
     * 项目进度计划
     */
    private List<ProjectPlan> projectPlanList;
}
