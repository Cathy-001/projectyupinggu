package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.net.Inet4Address;

/**
 * @ClassName reviewResult
 * @Description 审核结果
 * @Author Halo
 **/
@Data
@TableName(value="review_result")
public class ReviewResult {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 项目id
     */
   private Integer projectId;

    /**
     * 审核人id
     */
   private Integer reviewerId;

    /**
     * 审核结果 0 审核驳回 1审核通过
     */
   private Integer result;
}
