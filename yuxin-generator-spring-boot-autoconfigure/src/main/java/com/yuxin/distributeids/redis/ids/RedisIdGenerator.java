package com.yuxin.distributeids.redis.ids;


import com.yuxin.distributeids.entity.IdStrategy;
import com.yuxin.distributeids.service.IdStrategyService;
import com.yuxin.distributeids.util.DateFormatConstant;
import com.yuxin.distributeids.util.DateTimeUtil;
import com.yuxin.distributeids.util.IdConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author shenshixi
 * @Date 2021/12/17 17:38
 * @Version 1.0
 */


public class RedisIdGenerator {

    @Autowired
    private RedisIdService redisIdService;

    @Autowired
    private IdStrategyService idStrategyService;


    /**
     * 格式：默认日期格式（yyyyMMddhhmmssSSS） + 8位redis自增数
     * 超过99999999则从零开始
     *
     * @param idKey 数据库中的配置的id_key，用于缓存策略规则的key，同时也是查询策略的条件
     * @return
     */
    public String generateId(String idKey) {

        if (StringUtils.isEmpty(idKey)) {
            //默认情况
            String id = getDefaultIds(IdConstant.DEFAULT_ID_KEY);
            return id;
        }
        //配置有规则策略
        IdStrategy idStrategy = idStrategyService.getByIdKey(idKey);
        if (!StringUtils.isEmpty(idStrategy)) {
            return parseIdStrategy(idStrategy);
        }
        //查询条件不为空，但是没有配置规则策略，方法的id还是默认策略
        return getDefaultIds(IdConstant.DEFAULT_ID_KEY);
    }

    /**
     * 批量获取生成的id
     *
     * @param idKey 数据库中的配置的id_key，用于缓存策略规则的key，同时也是查询策略的条件
     * @param nums
     * @return
     */
    public List<String> bathGenerateIds(String idKey, int nums) {

        List<String> batchIds = new ArrayList<>();
        //idKey为空
        if (StringUtils.isEmpty(idKey)) {
            getDefaultBatch(batchIds, nums);
            return batchIds;
        }
        //配置有规则策略
        IdStrategy idStrategy = idStrategyService.getByIdKey(idKey);
        if (!StringUtils.isEmpty(idStrategy)) {
            for (int i = 0; i < nums; i++) {
                batchIds.add(parseIdStrategy(idStrategy));
            }
            return batchIds;
        }

        //idKey不为空，没有规则，则返回默认情况
        return getDefaultBatch(batchIds, nums);
    }


    /**
     * 解析id策略，组装成指定规则
     *
     * @param idStrategy
     * @return
     */
    private String parseIdStrategy(IdStrategy idStrategy) {

        String idPrefix = idStrategy.getIdPrefix() == null ? "" : idStrategy.getIdPrefix();
        String idTimePattern = idStrategy.getIdTimePattern() == null ? "" : idStrategy.getIdTimePattern();
        String idSuffix = idStrategy.getIdSuffix() == null ? "" : idStrategy.getIdSuffix();
        int idLength = idStrategy.getIdLength();
        int idStep = idStrategy.getIdStep();

        String idIncrKey = idStrategy.getIdIncrKey();
        //自增数值的key的过期时间不设置，则取过期时间为日终
        long idIncrKeyExpire = idStrategy.getIdIncrKeyExpire() <= 0 ? DateTimeUtil.getEndTime().toEpochMilli() : idStrategy.getIdIncrKeyExpire();

        int tmpPrefixLength = idPrefix == "" ? 0 : idPrefix.length();
        int timePatternLength = idTimePattern == "" ? 0 : idTimePattern.length();
        int tmpSuffixLength = idSuffix == "" ? 0 : idSuffix.length();

        int length = (idLength - tmpPrefixLength - timePatternLength - tmpSuffixLength);
        if (length <= 0) {
            throw new IllegalArgumentException("id长度必须大于prefix+id_time_pattern+suffix");
        }
        long num = redisIdService.increment(idIncrKey, idIncrKeyExpire, TimeUnit.MILLISECONDS, idStep);
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(idTimePattern);
        String id = idPrefix + LocalDateTime.now().format(ofPattern) + String.format("%0" +
                length + "d", num) + idSuffix;
        return id;
    }

    /**
     * 默认方式
     *
     * @param key
     */
    private String getDefaultIds(String key) {
        //默认,过期时间，到当天结束过期
        long num = redisIdService.increment(key, DateTimeUtil.getEndTime());
        DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern(DateFormatConstant.DATETIME_SSS_FORMAT_COMPACT);
        String id = LocalDateTime.now().format(ofPattern) + String.format("%09d", num);
        return id;
    }

    /**
     * 批量获取id
     *
     * @param batchIds
     * @param nums
     * @return
     */
    private List<String> getDefaultBatch(List<String> batchIds, int nums) {
        //批量获取默认方式
        for (int i = 0; i < nums; i++) {
            String id = getDefaultIds(IdConstant.DEFAULT_ID_KEY);
            batchIds.add(id);
        }
        return batchIds;
    }
}
