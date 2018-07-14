package com.android.greendao.insertlistobject;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by shahulhameed on 14/07/2018.
 */

@Entity
public class Employee {

    @Id
    private String Id;

    @NotNull
    private String employeedId;

    @NotNull
    private String Salary;

    @NotNull
    private String employeeType;

    @NotNull
    private String deptNumber;

    @NotNull
    private String branchId;

    @Generated(hash = 1792349960)
    public Employee(String Id, @NotNull String employeedId, @NotNull String Salary,
            @NotNull String employeeType, @NotNull String deptNumber,
            @NotNull String branchId) {
        this.Id = Id;
        this.employeedId = employeedId;
        this.Salary = Salary;
        this.employeeType = employeeType;
        this.deptNumber = deptNumber;
        this.branchId = branchId;
    }

    @Generated(hash = 202356944)
    public Employee() {
    }

    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getEmployeedId() {
        return this.employeedId;
    }

    public void setEmployeedId(String employeedId) {
        this.employeedId = employeedId;
    }

    public String getSalary() {
        return this.Salary;
    }

    public void setSalary(String Salary) {
        this.Salary = Salary;
    }

    public String getEmployeeType() {
        return this.employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getDeptNumber() {
        return this.deptNumber;
    }

    public void setDeptNumber(String deptNumber) {
        this.deptNumber = deptNumber;
    }

    public String getBranchId() {
        return this.branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

}
