package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ProjectMember
 * @Description 项目人员
 * @Author Halo
 **/
@Data
@TableName(value="project_member")
public class ProjectMember {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 项目角色 0负责人 1研究员
     */
    private Integer projectRole;

    /**
     * 姓名
     */
    private String name;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 证件类型 0 居民身份证 1护照 2港澳通行证
     */
    private Integer cardClass;

    /**
     * 证件号码
     */
    private String cardNumber;

    /**
     * 性别 0男 1女
     */
    private Integer gender;

    /**
     * 从事专业
     */
    private String major;

    /**
     * 职称
     */
    private String jobTitle;

    /**
     * 职务
     */
    private String position;

    /**
     * 学历 0本科 1硕士 2博士 3博士后
     */
    private Integer education;

    /**
     * 所在单位
     */
    private String unit;

    /**
     * 手机
     */
    private String phone;

    /**
     * 项目分工
     */
    private String workDivision;

    /**
     * 个人简介
     */
    private String individualResume;
}
