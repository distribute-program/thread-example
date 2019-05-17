package com.weixiaoyi.thread.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/17 13:55
 * @description：睡眠十秒请求参数封装类
 * @modified By：
 * @version: 1.0
 */
@Data
@ToString
public class SleepTenVo implements Serializable {

    private static final long serialVersionUID = 5886585142537618471L;

    private String param;

}
