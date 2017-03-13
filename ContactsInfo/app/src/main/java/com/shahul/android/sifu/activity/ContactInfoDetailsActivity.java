package com.shahul.android.sifu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shahul.android.sifu.R;
import shahul.network.retrofit.entity.ContactsInfo;

public class ContactInfoDetailsActivity extends AppCompatActivity {

    private ContactsInfo mContactInfo;
    private int mPosition;

    private Toolbar mToolbar;

    private RelativeLayout rlPhoneNumber, rlBusinessPhoneNumber, rlBusinessPersonalPhoneNumber,
            rlPersonalEmail, rlBusinessEmail, rlNote, rlFirstName, rlMiddleName, rlLastName,
            rlGender, rlJobDescritpion;
    private TextView tvPhoneNumber,tvBusinessPhoneNumber, tvBusinessPersonalPhoneNumber,
            tvPersonalEmail, tvBussinessEmail, tvNoteDescrption, tvFirstNameValue, tvMiddleNameValue,
            tvLastNameValue, tvGenderValue, tvJobDescritpionValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info_details);
        setIntentData();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        rlPhoneNumber = (RelativeLayout) findViewById(R.id.rlPhoneNumber) ;
        rlBusinessPhoneNumber = (RelativeLayout) findViewById(R.id.rlBusinessPhoneNumber) ;
        rlBusinessPersonalPhoneNumber = (RelativeLayout) findViewById(R.id.rlBusinessPersonalPhoneNumber) ;
        rlPersonalEmail = (RelativeLayout) findViewById(R.id.rlPersonalEmail) ;
        rlBusinessEmail = (RelativeLayout) findViewById(R.id.rlBusinessEmail) ;
        rlFirstName = (RelativeLayout) findViewById(R.id.rlFirstName) ;
        rlMiddleName = (RelativeLayout) findViewById(R.id.rlMiddleName) ;
        rlLastName = (RelativeLayout) findViewById(R.id.rlLastName) ;
        rlGender = (RelativeLayout) findViewById(R.id.rlGender) ;
        rlJobDescritpion = (RelativeLayout) findViewById(R.id.rlJobDescritpion) ;
        rlNote = (RelativeLayout) findViewById(R.id.rlNote) ;

        tvPhoneNumber = (TextView) findViewById(R.id.tvPhoneNumber) ;
        tvBusinessPhoneNumber = (TextView) findViewById(R.id.tvBusinessPhoneNumber) ;
        tvBusinessPersonalPhoneNumber = (TextView) findViewById(R.id.tvBusinessPersonalPhoneNumber) ;
        tvPersonalEmail = (TextView) findViewById(R.id.tvPersonalEmail) ;
        tvBussinessEmail = (TextView) findViewById(R.id.tvBussinessEmail) ;
        tvFirstNameValue = (TextView) findViewById(R.id.tvFirstNameValue) ;
        tvMiddleNameValue = (TextView) findViewById(R.id.tvMiddleNameValue) ;
        tvLastNameValue = (TextView) findViewById(R.id.tvLastNameValue) ;
        tvGenderValue = (TextView) findViewById(R.id.tvGenderValue) ;
        tvJobDescritpionValue = (TextView) findViewById(R.id.tvJobDescritpionValue) ;
        tvNoteDescrption = (TextView) findViewById(R.id.tvNoteDescrption) ;

        bindData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setIntentData(){

        Intent intent = getIntent();
        if(intent != null){
            mContactInfo = (ContactsInfo) intent.getSerializableExtra("contactInfo");
            mPosition = (int) intent.getIntExtra("position", -1);
        }
    }

    private void bindData(){
        if(mContactInfo  == null){
            return;
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(mContactInfo.getFullName() != null && !mContactInfo.getFullName().isEmpty()){
            getSupportActionBar().setTitle(mContactInfo.getFullName());
        }

        if(mContactInfo.getFirstName() != null && !mContactInfo.getFirstName().isEmpty()){
            rlFirstName.setVisibility(View.VISIBLE);
            tvFirstNameValue.setText(mContactInfo.getFirstName());
        }

        if(mContactInfo.getMiddleName() != null && !mContactInfo.getMiddleName().isEmpty()){
            rlMiddleName.setVisibility(View.VISIBLE);
            tvMiddleNameValue.setText(mContactInfo.getMiddleName());
        }

        if(mContactInfo.getLastName() != null && !mContactInfo.getLastName().isEmpty()){
            rlLastName.setVisibility(View.VISIBLE);
            tvLastNameValue.setText(mContactInfo.getLastName());
        }

        if(mContactInfo.getGender() != null && !mContactInfo.getGender().isEmpty()){
            rlGender.setVisibility(View.VISIBLE);
            String gender = "Male";
            if(mContactInfo.getGender().equalsIgnoreCase("F")){
                gender = "Female";
            }
            tvGenderValue.setText(gender);
        }

        if(mContactInfo.getPhone() != null && !mContactInfo.getPhone().isEmpty()){
            rlPhoneNumber.setVisibility(View.VISIBLE);
            tvPhoneNumber.setText(mContactInfo.getPhone());
        }

        if(mContactInfo.getBusinessPhone() != null && !mContactInfo.getBusinessPhone().isEmpty()){
            rlBusinessPhoneNumber.setVisibility(View.VISIBLE);
            tvBusinessPhoneNumber.setText(mContactInfo.getBusinessPhone());
        }

        if(mContactInfo.getBusinessMobile() != null && !mContactInfo.getBusinessMobile().isEmpty()){
            rlBusinessPersonalPhoneNumber.setVisibility(View.VISIBLE);
            tvBusinessPersonalPhoneNumber.setText(mContactInfo.getBusinessMobile());
        }

        if(mContactInfo.getEmail() != null && !mContactInfo.getEmail().isEmpty()){
            rlPersonalEmail.setVisibility(View.VISIBLE);
            tvPersonalEmail.setText(mContactInfo.getEmail());
        }

        if(mContactInfo.getBusinessEmail() != null && !mContactInfo.getBusinessEmail().isEmpty()){
            rlBusinessEmail.setVisibility(View.VISIBLE);
            tvBussinessEmail.setText(mContactInfo.getBusinessEmail());
        }

        if(mContactInfo.getJobTitleDescription() != null && !mContactInfo.getJobTitleDescription().isEmpty()){
            rlJobDescritpion.setVisibility(View.VISIBLE);
            tvJobDescritpionValue.setText(mContactInfo.getJobTitleDescription());
        }

        if(mContactInfo.getNotes() != null && !mContactInfo.getNotes().isEmpty()){
            rlNote.setVisibility(View.VISIBLE);
            tvNoteDescrption.setText(mContactInfo.getNotes());
        }

    }

}
