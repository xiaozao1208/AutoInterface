package com.learn.bean;


import lombok.Data;

/**
 * lombok 使用
 * 1.安装 安装lombok插件：步骤：File ——》Settings——》Plugins.
 * 搜索lombok，点击安装install
 * 2.项目添加 lombok 依赖
 * 3.@Data 注释即可；-- 不需要写get和set方法；
 */
@Data
public class User {

    private String username;
    private String password;
    private String name;
    private String age;
    private String sex;

}
