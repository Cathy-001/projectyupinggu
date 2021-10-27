package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.bean.ProjectMsg;
import com.example.demo.qo.ProjectMsgQO;
import com.example.demo.vo.ProjectMsgVO;


public interface ProjectMsgDao extends BaseMapper<ProjectMsg>{

    IPage<ProjectMsgVO> getListByQO(IPage<ProjectMsgVO> page, ProjectMsgQO projectMsgQO);
}
