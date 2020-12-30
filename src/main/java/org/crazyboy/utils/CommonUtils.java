package org.crazyboy.utils;

import cn.hutool.core.date.DateTime;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: kevin
 * @date: 2020/9/26
 * @time: 上午12:42
 * @description:
 **/

@Component
public class CommonUtils {

    public String localDate2String(LocalDate localDate) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(fmt);
    }

    public String localDateTime2String(LocalDateTime localDateTime) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(fmt);
    }

    public String getCurrentTimestamp() {
        return String.valueOf(System.currentTimeMillis());
    }


    /**
     * 获取当前时间戳 (毫秒)
     *
     * @return
     */
    public String getCurrentTimeStamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 时间戳 --> yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp
     * @return
     */
    public String formatTimestamp(String timestamp) {
        long time = Long.parseLong(timestamp);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    /**
     * 时间戳 --> yyyy-MM-dd
     *
     * @param timestamp
     * @return
     */
    public String formatTimestamp2Date(String timestamp) {
        long time = Long.parseLong(timestamp);
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

}
