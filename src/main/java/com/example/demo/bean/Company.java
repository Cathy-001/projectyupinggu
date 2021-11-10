package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName Company
 * @Description 单位基本信息
 * @Author Halo
 **/
@Data
@TableName(value="company")
public class Company {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 注册所在地
     */
    private String location;

    /**
     * 单位地址
     */
    private String companyAddress;

    /**
     * 推荐部门
     */
    private String recommendDepart;

    /**
     * 注册资本
     */
    private Double capital;

    /**
     * 单位隶属
     */
    private String unitUnder;

    /**
     * 注册类型
     */
    private String registerType;

    /**
     * 注册日期
     */
    private String registerDate;

    /**
     * 所属国民经济行业
     */
    private String business;

    /**
     * 企业规模（0小型企业 1中型企业 2大型企业）
     */
    private Integer scale;

    /**
     * 单位性质
     */
    private String property;

    /**
     * 单位资质
     */
    private String certification;

    /**
     * 单位拥有研发机构状况
     */
    private String researchInstitution;

    /**
     * 单位所在重点园区
     */
    private String garden;

    /**
     * 员工人数
     */
    private Integer staffAll;

    /**
     * 直接从事研发人员人数
     */
    private Integer staffDirect;

    /**
     *  本科以上研发人员数
     */
    private Integer staffUndergrad;
}
