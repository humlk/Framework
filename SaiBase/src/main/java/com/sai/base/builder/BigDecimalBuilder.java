package com.sai.base.builder;

import java.math.BigDecimal;

/**
 * BigDecimal 运算
 */
public class BigDecimalBuilder {
    private BigDecimal mBigDecimal;

    public BigDecimalBuilder(String value) {
        mBigDecimal = getBigDecimal(value);
    }

    /**
     * 加法
     *
     * @param value
     * @return
     */
    public BigDecimalBuilder add(String value) {
        mBigDecimal = mBigDecimal.add(getBigDecimal(value));
        return this;
    }

    public BigDecimalBuilder add(String... nums) {

        for (String num : nums) {
            add(num);
        }
        return this;
    }

    /**
     * 减法
     *
     * @param value
     * @return
     */
    public BigDecimalBuilder sub(String value) {
        mBigDecimal = mBigDecimal.subtract(getBigDecimal(value));
        return this;
    }


    /**
     * 乘法
     *
     * @param value
     * @return
     */
    public BigDecimalBuilder multiply(String value) {
        mBigDecimal = mBigDecimal.multiply(getBigDecimal(value));
        return this;
    }

    /**
     * 除法
     *
     * @param value 如果为0则自动转为1
     * @return
     */
    public BigDecimalBuilder divide(String value) {
        return divide(value, 2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 除法
     *
     * @param value 如果为0则自动转为1
     * @param scale        保留小数点位数
     * @param roundingMode 保留规则
     * @return
     */
    public BigDecimalBuilder divide(String value, int scale, int roundingMode) {
        mBigDecimal = mBigDecimal.divide(getBigDecimal(value, BigDecimal.ONE), scale, roundingMode);
        return this;
    }

    /**
     * 求余
     *
     * @param value 如果为0则自动转为1
     * @return
     */
    public BigDecimalBuilder remainder(String value) {
        mBigDecimal = mBigDecimal.remainder(getBigDecimal(value, BigDecimal.ONE));
        return this;
    }

    /**
     *
     * @param num
     * @return
     */
    public int compare(String num) {
        BigDecimal b = getBigDecimal(num);
        return mBigDecimal.compareTo(b);
    }

    private BigDecimal getBigDecimal(String num) {
        return getBigDecimal(num, BigDecimal.ZERO);
    }

    private BigDecimal getBigDecimal(String num, BigDecimal def) {
        if (num != null && num.length() > 0) {
            return new BigDecimal(num);
        }
        return def;
    }

    public BigDecimal build() {
        return mBigDecimal;
    }

    public static void main(String[] args){
        System.out.print(2%1);
    }
}
