package com.yuxin.distributeids.util;

import java.time.*;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author shenshixi
 * @Date 2021/12/28 14:28
 * @Version 1.0
 */
public class DateTimeUtil {
    /**
     * 获取当天的结束时间
     */
    public static Instant getEndTime() {
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        Instant instant = endTime.toInstant(ZoneOffset.ofHours(8));
        return instant.plusMillis(TimeUnit.HOURS.toMillis(8));
    }

}
