package com.winson.tutorial.drools.bean;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 16-7-14.
 */
public class PointDomain {

    private static final Logger logger = Logger.getLogger(PointDomain.class);

    private String userName;
    // 是否当日生日
    private boolean birthDay;

// 增加积分数目
    private long point;

// 当月购物次数
    private int buyNums;
// 当月退货次数
    private int backNums;

// 当月购物总金额
    private double buyMoney;

// 当月退货总金额
    private double backMondy;

// 当月信用卡还款次数
    private int billThisMonth;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isBirthDay() {
        return birthDay;
    }

    public void setBirthDay(boolean birthDay) {
        this.birthDay = birthDay;
    }

    public long getPoint() {
        return point;
    }

    public void setPoint(long point) {
        this.point = point;
    }

    public int getBuyNums() {
        return buyNums;
    }

    public void setBuyNums(int buyNums) {
        this.buyNums = buyNums;
    }

    public int getBackNums() {
        return backNums;
    }

    public void setBackNums(int backNums) {
        this.backNums = backNums;
    }

    public double getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(double buyMoney) {
        this.buyMoney = buyMoney;
    }

    public double getBackMondy() {
        return backMondy;
    }
    public void setBackMondy(double backMondy) {
        this.backMondy = backMondy;
    }

    public int getBillThisMonth() {
        return billThisMonth;
    }

    public void setBillThisMonth(int billThisMonth) {
        this.billThisMonth = billThisMonth;
    }

    public void recordPointLog(String type, int points, boolean isToAdd) {
        logger.info(new StringBuilder(isToAdd?"增加":"减少").append("对").append( userName).append("的类型为").append(type)
                .append("的积分操作记录:").append(points).append(";").toString());
    }
}
