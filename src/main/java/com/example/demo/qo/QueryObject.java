package com.example.demo.qo;

import lombok.Data;

/**
 * @ClassName QueryObject
 * @Description TODO
 * @Author Halo
 **/
@Data
public class QueryObject {

    /**
     * 分页偏移量
     */
    private Integer current;

    /**
     * 每一页的数量
     */
    private Integer size;

    /**
     * 关键字
     */
    private String key;
}
