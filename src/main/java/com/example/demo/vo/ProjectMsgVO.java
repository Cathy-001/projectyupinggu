package com.example.demo.vo;

import com.example.demo.bean.ReviewResult;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ProjectMsgVO
 * @Description TODO
 * @Author Halo
 **/
@Data
public class ProjectMsgVO {

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 项目总投资
     */
    private Double investment;

    /**
     * 申请专项经费
     */
    private Double specialMoney;

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
     * 单位名称
     */
    private String companyName;
}
