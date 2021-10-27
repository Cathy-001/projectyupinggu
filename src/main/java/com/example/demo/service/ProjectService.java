package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.bean.*;
import com.example.demo.dao.*;
import com.example.demo.enums.GeneralResponseEnums;
import com.example.demo.exception.CustomizeException;
import com.example.demo.qo.Project;
import com.example.demo.qo.ProjectMsgQO;
import com.example.demo.vo.ProjectMsgVO;
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


    /**
     * 添加项目信息
     * @param project
     * @return
     */
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

    /**
     * 删除项目信息
     * @param id
     * @return
     */
    public Boolean deleteProjectById(int id){

        //删除单位信息
        ProjectMsg projectMsg = projectMsgDao.selectById(id);
        int result = companyDao.deleteById(projectMsg.getCompanyId());
        if(result == 0){
            throw new CustomizeException(GeneralResponseEnums.DELETE_FAILED);
        }

        //删除项目基本信息
        int ret = projectMsgDao.deleteById(id);
        if(ret == 0){
            throw new CustomizeException(GeneralResponseEnums.DELETE_FAILED);
        }

        //删除项目组成员
        LambdaQueryWrapper<ProjectMember> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(ProjectMember::getProjectId,id);
        int delete = projectMemberDao.delete(queryWrapper1);

        //删除项目计划
        LambdaQueryWrapper<ProjectPlan> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(ProjectPlan::getProjectId,id);
        int delete1 = projectPlanDao.delete(queryWrapper2);

        //删除合作单位
        LambdaQueryWrapper<CooperationUnit> queryWrapper3 = new LambdaQueryWrapper<>();
        queryWrapper3.eq(CooperationUnit::getProjectId,id);
        int delete2 = cooperationUnitDao.delete(queryWrapper3);

        return true;
    }

    /**
     * 批量删除
     * @param idList
     * @return
     */
    public Boolean deleteByIds(int[] idList){

        if(idList == null || idList.length == 0){
            return true;
        }

        for (int id : idList) {
            deleteProjectById(id);
        }

        return true;
    }

    /**
     * 列表查询
     * @param projectMsgQO
     * @return
     */
    public IPage<ProjectMsgVO> getListByQO(ProjectMsgQO projectMsgQO){

        Page<ProjectMsgVO> page = new Page<>(projectMsgQO.getCurrent(),projectMsgQO.getSize());

        //排序
        OrderItem orderItem = new OrderItem();
        orderItem.setColumn("create_time").setAsc(false);
        page.getOrders().add(orderItem);

        IPage<ProjectMsgVO> list = projectMsgDao.getListByQO(page, projectMsgQO);

        return list;
    }

}
