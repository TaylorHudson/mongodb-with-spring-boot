package br.com.mongodbwithspringboot.entities;

import org.springframework.data.mongodb.core.mapping.Field;

public class Department {

    @Field(name = "department_name")
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
