package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName ProjectMsg
 * @Description 项目基本信息（主表）
 * @Author Halo
 **/
@Data
@TableName(value="projectmsg")
public class ProjectMsg {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 单位id
     */
    private Long companyId;

    /**
     * 指南方向
     */
    private String direction;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目实施地点
     */
    private String location;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 经济行业领域
     */
    private String economicFiled;

    /**
     * 细分领域1
     */
    private String filedOne;

    /**
     * 细分领域2
     */
    private String filedTwo;

    /**
     * 项目总投资
     */
    private Double investment;

    /**
     * 申请专项经费
     */
    private Double specialMoney;

    /**
     * 项目负责人id
     */
    //private Long leaderId;

    /**
     * 合作单位id
     */
    //private Long cooperationId;

    /**
     * 主要研究内容与目标
     */
    private String researchContent;

    /**
     * 创新点与关键技术
     */
    private String innovationPoint;

    /**
     * 现有基础和实施条件
     */
    private String basics;

    /**
     * 预期结果与经济、社会效益
     */
    private String expectation;

    /**
     * 考核指标
     */
    private String assessIndicators;

    /**
     * 项目总人数
     */
    private Integer totalStaff;

    /**
     * 高级职称人数
     */
    private Integer seniorStaff;

    /**
     * 中级职称人数
     */
    private Integer midStaff;

    /**
     * 初级职称人数
     */
    private Integer juniorStaff;

    /**
     * 博士人数
     */
    private Integer doctor;

    /**
     * 硕士人数
     */
    private Integer master;

    /**
     * 学士人数
     */
    private Integer bachelor;

    /**
     * 其它人数
     */
    private Integer elseNum;

    /**
     * 主要研究人员id
     */
    //private String researchers;

    /**
     * 项目可行性报告
     */
    private String feasibilityReport;

    /**
     * 附件清单
     */
    private String attachment;

    /**
     * 工作进度 0未提交  1已提交  2审批通过 3审批驳回
     */
    private Integer state;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人id
     */
    private Long createId;

}
