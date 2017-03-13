package shahul.network.retrofit.entity;

import com.google.gson.annotations.SerializedName;

import java.io.InputStream;
import java.io.Serializable;

/**
 * Created by shahulhameed on 09/03/2017.
 */

public class ContactsInfo implements Serializable{

    @SerializedName("Account")
    private String account;

    @SerializedName("BusinessEmail")
    private String businessEmail;

    @SerializedName("BusinessPhone")
    private String businessPhone;

    @SerializedName("BusinessMobile")
    private String businessMobile;

    @SerializedName("Email")
    private String email;

    @SerializedName("FirstName")
    private String firstName;

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("Gender")
    private String gender;

    @SerializedName("ID")
    private String id;

    @SerializedName("JobTitleDescription")
    private String jobTitleDescription;

    @SerializedName("LastName")
    private String lastName;

    @SerializedName("MiddleName")
    private String middleName;

    @SerializedName("Mobile")
    private String mobile;

    @SerializedName("Notes")
    private String notes;

    @SerializedName("Phone")
    private String phone;

    @SerializedName("PictureThumbnailUrl")
    private String pictureThumbnailUrl;

    public ContactsInfo() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getBusinessMobile() {
        return businessMobile;
    }

    public void setBusinessMobile(String businessMobile) {
        this.businessMobile = businessMobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobTitleDescription() {
        return jobTitleDescription;
    }

    public void setJobTitleDescription(String jobTitleDescription) {
        this.jobTitleDescription = jobTitleDescription;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPictureThumbnailUrl() {
        return pictureThumbnailUrl;
    }

    public void setPictureThumbnailUrl(String pictureThumbnailUrl) {
        this.pictureThumbnailUrl = pictureThumbnailUrl;
    }
}
