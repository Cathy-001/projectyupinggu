package com.example.demo.service;

import com.example.demo.bean.*;
import com.example.demo.dao.*;
import com.example.demo.enums.GeneralResponseEnums;
import com.example.demo.exception.CustomizeException;
import com.example.demo.qo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

/**
 * @ClassName ProjectService
 * @Description TODO
 * @Author Halo
 **/
@Service
public class ProjectService {


    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ProjectMsgDao projectMsgDao;

    @Autowired
    private CooperationUnitDao cooperationUnitDao;

    @Autowired
    private ProjectMemberDao projectMemberDao;

    @Autowired
    private ProjectPlanDao projectPlanDao;


    public Boolean addProject(Project project){


        //添加单位信息
        Company company = new Company();

        BeanUtils.copyProperties(project.getCompany(),company);

        int insert = companyDao.insert(company);
        if(insert == 0){
            throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
        }

        //添加项目基本信息
        ProjectMsg projectMsg = new ProjectMsg();
        BeanUtils.copyProperties(project.getProjectMsg(),projectMsg);
        projectMsg.setCompanyId(company.getId());

        int insert1 = projectMsgDao.insert(projectMsg);
        if(insert1 == 0){
            throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
        }

        //添加合作单位
        for (CooperationUnit unit : project.getCooperationUnitList()) {

            unit.setProjectId(projectMsg.getId());
            int ret1 = cooperationUnitDao.insert(unit);
            if(ret1 == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        //添加项目成员
        for (ProjectMember member : project.getProjectMemberList()) {

            member.setProjectId(projectMsg.getId());
            int ret2 = projectMemberDao.insert(member);
            if(ret2 == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        //添加项目计划
        for (ProjectPlan plan : project.getProjectPlanList()) {
            plan.setProjectId(projectMsg.getId());
            int ret3 = projectPlanDao.insert(plan);
            if(ret3 == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        return true;
    }
}
