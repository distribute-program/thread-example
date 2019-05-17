package com.weixiaoyi.thread.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/16 17:00
 * @description：查询数据返回结果封装类
 * @modified By：
 * @version: 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FindDataBo implements Serializable {

    private static final long serialVersionUID = 783199877045483154L;

    private String name;

}
