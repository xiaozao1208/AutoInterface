package com.course.model;

import lombok.Data;

@Data
public class UpdateUserInfoCase {

    private int id;
    private int userId;
    private String UserName;
    private String sex;
    private String age;
    private String permission;
    private String isDelete;
    private String expected;
}
