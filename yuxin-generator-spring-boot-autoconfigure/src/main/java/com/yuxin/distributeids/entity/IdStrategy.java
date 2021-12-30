package com.yuxin.distributeids.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (IdStrategy)实体类
 *
 * @author makejava
 * @since 2021-12-28 11:52:16
 */
public class IdStrategy implements Serializable {
    private static final long serialVersionUID = 839194310566929437L;
    /**
     * 策略主键
     */
    private int id;
    /**
     * 策略名称
     */
    private String idName;
    /**
     * ID前缀
     */
    private String idPrefix;
    /**
     * ID后缀
     */
    private String idSuffix;
    /**
     * redis自增步长
     */
    private int idStep;
    /**
     * 组合成ID的时间格式
     */
    private String idTimePattern;
    /**
     * ID长度：id_length=prefix+id_time_pattern+redis自增数值+suffix
     */
    private int idLength;
    /**
     * ID是否可用；0-否，1-是
     */
    private int idStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 用于缓存规则策略的key，同时也是作为查询该规则的条件
     */
    private String idKey;
    /**
     * redis过期时间，单位毫秒
     */
    private long idKeyExpire;
    /**
     * redis自增数值的key,不配置，则所有的规则共用一个默认key
     */
    private String idIncrKey;
    /**
     * redis自增数值的key,单位毫秒
     */
    private long idIncrKeyExpire;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdPrefix() {
        return idPrefix;
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    public String getIdSuffix() {
        return idSuffix;
    }

    public void setIdSuffix(String idSuffix) {
        this.idSuffix = idSuffix;
    }

    public int getIdStep() {
        return idStep;
    }

    public void setIdStep(int idStep) {
        this.idStep = idStep;
    }

    public String getIdTimePattern() {
        return idTimePattern;
    }

    public void setIdTimePattern(String idTimePattern) {
        this.idTimePattern = idTimePattern;
    }

    public int getIdLength() {
        return idLength;
    }

    public void setIdLength(int idLength) {
        this.idLength = idLength;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIdKey() {
        return idKey;
    }

    public void setIdKey(String idKey) {
        this.idKey = idKey;
    }

    public long getIdKeyExpire() {
        return idKeyExpire;
    }

    public void setIdKeyExpire(long idKeyExpire) {
        this.idKeyExpire = idKeyExpire;
    }

    public String getIdIncrKey() {
        return idIncrKey;
    }

    public void setIdIncrKey(String idIncrKey) {
        this.idIncrKey = idIncrKey;
    }

    public long getIdIncrKeyExpire() {
        return idIncrKeyExpire;
    }

    public void setIdIncrKeyExpire(long idIncrKeyExpire) {
        this.idIncrKeyExpire = idIncrKeyExpire;
    }

}

