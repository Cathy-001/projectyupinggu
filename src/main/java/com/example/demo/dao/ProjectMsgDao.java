package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.bean.ProjectMsg;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


public interface ProjectMsgDao extends BaseMapper<ProjectMsg>{

    List<ProjectMsg> getList();

}
