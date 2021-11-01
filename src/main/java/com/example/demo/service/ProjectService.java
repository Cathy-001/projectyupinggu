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
import com.example.demo.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;

import java.util.List;

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
        projectMsg.setState(0);
        projectMsg.setCompanyId(company.getId());

        int insert1 = projectMsgDao.insert(projectMsg);
        if(insert1 == 0){
            throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
        }

        //添加合作单位
        for (CooperationUnit unit : project.getCooperationUnitList()) {
            CooperationUnit cooperationUnit = new CooperationUnit();
            BeanUtils.copyProperties(unit,cooperationUnit);
            cooperationUnit.setProjectId(projectMsg.getId());
            int ret1 = cooperationUnitDao.insert(cooperationUnit);
            if(ret1 == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        //添加项目成员
        for (ProjectMember member : project.getProjectMemberList()) {
            ProjectMember projectMember = new ProjectMember();
            BeanUtils.copyProperties(member,projectMember);
            projectMember.setProjectId(projectMsg.getId());
            int ret2 = projectMemberDao.insert(projectMember);
            if(ret2 == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        //添加项目计划
        for (ProjectPlan plan : project.getProjectPlanList()) {
            ProjectPlan projectPlan = new ProjectPlan();
            BeanUtils.copyProperties(plan,projectPlan);
            projectPlan.setProjectId(projectMsg.getId());
            int ret3 = projectPlanDao.insert(projectPlan);
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
    public Boolean deleteProjectById(Integer id){

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

    /**
     * 根据id进行查询
     * @param id 项目基本信息id
     * @return
     */
    public ProjectVO getProjectById(Integer id){

        //Project project = new Project();
        ProjectVO vo = new ProjectVO();

        //查询项目基本信息
        ProjectMsg projectMsg = projectMsgDao.selectById(id);
        vo.setProjectMsg(projectMsg);

        //查询单位信息
        Company company = companyDao.selectById(projectMsg.getCompanyId());
        vo.setCompany(company);

        //查询合作单位信息
        LambdaQueryWrapper<CooperationUnit> queryWrapperCooperationUnit = new LambdaQueryWrapper<>();
        queryWrapperCooperationUnit.eq(CooperationUnit::getProjectId,id);
        List<CooperationUnit> cooperationUnitList = cooperationUnitDao.selectList(queryWrapperCooperationUnit);
        vo.setCooperationUnitList(cooperationUnitList);

        //查询项目人员信息
        LambdaQueryWrapper<ProjectMember> memberLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberLambdaQueryWrapper.eq(ProjectMember::getProjectId,id);
        List<ProjectMember> projectMemberList = projectMemberDao.selectList(memberLambdaQueryWrapper);

        //过滤负责人
        for(int i = 0;i < projectMemberList.size();i++){

            //是否是负责人
            if(projectMemberList.get(i).getProjectRole() == 0){
                //获取负责人信息
                vo.setLeader(projectMemberList.get(i));
                projectMemberList.remove(i);
                break;
            }
        }

        vo.setProjectMemberList(projectMemberList);

        //查询项目进度计划
        LambdaQueryWrapper<ProjectPlan> planLambdaQueryWrapper = new LambdaQueryWrapper<>();
        planLambdaQueryWrapper.eq(ProjectPlan::getProjectId,id);
        List<ProjectPlan> projectPlanList = projectPlanDao.selectList(planLambdaQueryWrapper);
        vo.setProjectPlanList(projectPlanList);

        return vo;
    }

    /**
     * 修改项目信息
     * @param project 项目信息
     * @return
     */
    public Boolean patchById(Project project){

        //修改项目基本信息
        int ret1 = projectMsgDao.updateById(project.getProjectMsg());
        if(ret1 == 0){
            throw new CustomizeException(GeneralResponseEnums.UPDATE_FAILED);
        }

        //修改单位基本信息
        int ret2 = companyDao.updateById(project.getCompany());
        if(ret2 == 0){
            throw new CustomizeException(GeneralResponseEnums.UPDATE_FAILED);
        }

        //修改项目进度计划
        //删除原有项目计划，重新添加
        LambdaQueryWrapper<ProjectPlan> planLambdaQueryWrapper = new LambdaQueryWrapper<>();
        planLambdaQueryWrapper.eq(ProjectPlan::getProjectId,project.getProjectMsg().getId());
        int ret3 = projectPlanDao.delete(planLambdaQueryWrapper);
        for (ProjectPlan plan : project.getProjectPlanList()) {
            ProjectPlan projectPlan = new ProjectPlan();
            BeanUtils.copyProperties(plan,projectPlan);
            projectPlan.setProjectId(project.getProjectMsg().getId());
            int retPlan = projectPlanDao.insert(projectPlan);
            if(retPlan == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        //修改合作单位信息
        //删除原有合作单位信息，重新添加
        LambdaQueryWrapper<CooperationUnit> cooperationUnitLambdaQueryWrapper = new LambdaQueryWrapper<>();
        cooperationUnitLambdaQueryWrapper.eq(CooperationUnit::getProjectId,project.getProjectMsg().getId());
        int ret4 = cooperationUnitDao.delete(cooperationUnitLambdaQueryWrapper);
        for (CooperationUnit unit : project.getCooperationUnitList()) {
            CooperationUnit cooperationUnit = new CooperationUnit();
            BeanUtils.copyProperties(unit,cooperationUnit);
            cooperationUnit.setProjectId(project.getProjectMsg().getId());
            int retUnit = cooperationUnitDao.insert(cooperationUnit);
            if(retUnit == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        //修改项目组成员信息
        //删除原有成员，重新添加
        LambdaQueryWrapper<ProjectMember> memberLambdaQueryWrapper = new LambdaQueryWrapper<>();
        memberLambdaQueryWrapper.eq(ProjectMember::getProjectId,project.getProjectMsg().getId());
        int ret5 = projectMemberDao.delete(memberLambdaQueryWrapper);
        for (ProjectMember member : project.getProjectMemberList()) {
            ProjectMember projectMember = new ProjectMember();
            BeanUtils.copyProperties(member,projectMember);
            projectMember.setProjectId(project.getProjectMsg().getId());
            int retMember = projectMemberDao.insert(projectMember);
            if(retMember == 0){
                throw new CustomizeException(GeneralResponseEnums.ADD_FAILED);
            }
        }

        return true;
    }
}
