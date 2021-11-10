package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName SysUser
 * @Description 系统用户
 * @Author Halo
 **/
@Data
@TableName(value = "sys_user")
public class SysUser {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色  0申报用户  1专家
     */
    private Integer role;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机或者邮箱
     */
    private String phoneOrEmail;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 性别 0男  1女
     */
    private Integer gender;

    /**
     * 密码
     */
    private String password;

    /**
     * 学历 0本科  1硕士 2博士 3博士后
     */
    private Integer education;

    /**
     * 专业
     */
    private String major;

    /**
     * 手机号码
     */
    private String mobilePhone;

    /**
     * 办公电话
     */
    private String officePhone;

    /**
     * 所在单位
     */
    private String unit;

    /**
     * 证件类型 0 居民身份证 1护照 2港澳通行证
     */
    private Integer cardClass;

    /**
     * 证件号码
     */
    private String cardNumber;
}
