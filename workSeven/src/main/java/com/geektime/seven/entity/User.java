package com.geektime.seven.entity;

import java.io.Serializable;

/**
 * 用户表
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4914045931594414258L;
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String telphone;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 生日日期
     */
    private String birthday;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 最后更新时间
     */
    private Long lastUpdateTime;

}
