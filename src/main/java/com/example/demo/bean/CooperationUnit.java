package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName CooperationUnit
 * @Description 合作单位信息
 * @Author Halo
 **/
@Data
@TableName(value="cooperation_unit")
public class CooperationUnit {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 单位名称
     */
    private String name;

    /**
     * 单位地址
     */
    private String location;

    /**
     * 联系人
     */
    private String linkMan;

    /**
     * 联系电话
     */
    private String linkNumber;

    /**
     * 附件
     */
    private String accessory;
}
