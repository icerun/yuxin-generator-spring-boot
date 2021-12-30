package com.yuxin.distributeids.mapper;


import com.yuxin.distributeids.entity.IdStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * (IdStrategy)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-22 09:24:10
 */
@Mapper
public interface IdStrategyMapper {

    /**
     * 根据idKey获取策略规则
     *
     * @return
     */
    IdStrategy getByIdKey(String idKey);

}

