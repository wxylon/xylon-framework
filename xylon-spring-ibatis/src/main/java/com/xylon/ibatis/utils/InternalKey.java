package com.xylon.ibatis.utils;

import java.nio.ByteBuffer;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
/***
 * 使用数据源和日期组合而成的标识<br/>
 * skuId(4个字节-int类型)+offsetDays(2个字节short类型)
 * @author Administrator
 */
class InternalKey {
    static final DateTime START = new DateTime(2006, 1, 1, 0, 0, 0);
    /**数据源标识*/
    private final int skuId;
    /**与06年1月1日相隔天数*/
    private final short offsetDays;
    /**日期标识*/
    private final Date date;

    InternalKey(int skuId, Date date) {
        this.skuId = skuId;
        this.offsetDays = offsetDays(date);
        this.date = date;
    }

    InternalKey(byte[] bytes) {
        if (bytes.length != 6) {
            throw new IllegalArgumentException("Length should be 6.");
        }
        //创建初始容量6个字节
        ByteBuffer buffer = ByteBuffer.allocate(6).put(bytes);
        //读取从下标0开始的int型
        this.skuId = buffer.getInt(0);
        //读取从下标4开始的short型
        this.offsetDays = buffer.getShort(4);
        //还原时间
        this.date = START.plusDays(buffer.getShort(4)).toDate();
    }

    int getSkuId() {
        return skuId;
    }

    Date getDate() {
        return date;
    }

    byte[] getBytes() {
        return ByteBuffer.allocate(6).putInt(skuId).putShort(offsetDays).array();
    }

    /**
     * 指定日期与2006-1-1相隔的天数
     * @param date	指定日期
     * @return		相隔天数
     */
    static short offsetDays(Date date) {
        int days = Days.daysBetween(START, new DateTime(date)).getDays();
        if (days > Short.MAX_VALUE || days < Short.MIN_VALUE) {
            throw new IllegalArgumentException("Invalid date.");
        }
        return (short) days;
    }

    /**
     * hashCode 
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + skuId;
        result = prime * result + offsetDays;
        return result;
    }

    /**
     * equals
     */
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InternalKey other = (InternalKey) obj;
        if (skuId != other.skuId)
            return false;
        if (offsetDays != other.offsetDays)
            return false;
        return true;
    }

}
