package com.example.demo.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

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
    private Long id;

    /**
     * 项目id
     */
   private Long projectId;

    /**
     * 审核人id
     */
   private Long reviewerId;

    /**
     * 审核结果 0 审核驳回 1审核通过
     */
   private Integer result;
}
