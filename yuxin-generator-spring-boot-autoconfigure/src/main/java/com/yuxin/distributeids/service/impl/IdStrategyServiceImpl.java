package com.yuxin.distributeids.service.impl;

import com.yuxin.distributeids.entity.IdStrategy;
import com.yuxin.distributeids.mapper.IdStrategyMapper;
import com.yuxin.distributeids.redis.RedisService;
import com.yuxin.distributeids.service.IdStrategyService;
import com.yuxin.distributeids.util.DateTimeUtil;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * (IdStrategy)表服务实现类
 *
 * @author makejava
 * @since 2021-12-22 09:24:13
 */
public class IdStrategyServiceImpl implements IdStrategyService {

    @Resource
    private RedisService redisService;

    @Resource
    private IdStrategyMapper idStrategyMapper;


    /**
     * 根据idKey获取策略规则
     *
     * @return
     */
    @Override
    public IdStrategy getByIdKey(String idKey) {

        Object o1 = redisService.get(idKey);
        IdStrategy o = (IdStrategy) o1;
        if (!ObjectUtils.isEmpty(o)) {
            return o;
        }

        IdStrategy idStrategy = this.idStrategyMapper.getByIdKey(idKey);
        if (!ObjectUtils.isEmpty(idStrategy)) {
            //过期时间，不配置则设置为日终
            long idKeyExpire = idStrategy.getIdKeyExpire() <= 0 ? DateTimeUtil.getEndTime().toEpochMilli() : idStrategy.getIdKeyExpire();
            redisService.set(idKey, idStrategy, idKeyExpire);
        }
        return idStrategy;
    }

}
