package com.android.greendao.insertobject;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by shahulhameed on 14/07/2018.
 */

@Entity
public class ContactInfo {

    @Id
    private long Id;

    @NotNull
    private String fatherName;

    @NotNull
    private String motherName;

    @NotNull
    private String email;

    @NotNull
    private String mobileNumber;

    @NotNull
    private String address;

    @Generated(hash = 2044437384)
    public ContactInfo(long Id, @NotNull String fatherName,
            @NotNull String motherName, @NotNull String email,
            @NotNull String mobileNumber, @NotNull String address) {
        this.Id = Id;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }

    @Generated(hash = 2019856331)
    public ContactInfo() {
    }

    public long getId() {
        return this.Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return this.motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
