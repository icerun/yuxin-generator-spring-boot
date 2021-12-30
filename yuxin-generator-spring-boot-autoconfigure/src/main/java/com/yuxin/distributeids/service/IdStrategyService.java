package com.yuxin.distributeids.service;


import com.yuxin.distributeids.entity.IdStrategy;

/**
 * (IdStrategy)表服务接口
 *
 * @author makejava
 * @since 2021-12-22 09:24:13
 */
public interface IdStrategyService {

    /**
     * 根据idKey获取策略规则
     *
     * @return
     */
    IdStrategy getByIdKey(String idKey);
}
