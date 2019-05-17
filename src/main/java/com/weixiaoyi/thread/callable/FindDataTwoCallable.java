package com.weixiaoyi.thread.callable;

import com.weixiaoyi.thread.bo.FindDataBo;

import java.util.concurrent.Callable;

/**
 * @author ：yuanLong Wei
 * @date ：Created in 2019/5/16 16:57
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class FindDataTwoCallable implements Callable<FindDataBo> {

    @Override
    public FindDataBo call() throws Exception {
        return new FindDataBo("FindDataTwoCallable");
    }

}
