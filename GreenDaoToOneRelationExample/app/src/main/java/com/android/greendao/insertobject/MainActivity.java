package com.android.greendao.insertobject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import example.liststorage.greendao.android.com.greendaoliststorage.R;

/**
 * Created by shahulhameed on 14/07/2018.
 */

public class MainActivity extends AppCompatActivity {

    DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        daoSession = ((MyApplication)getApplication()).getDaoSession();

        insertDataIntoDatabase();
        queryResultFromDatabase();
    }

    private void insertDataIntoDatabase() {
        Student student = new Student();
        student.setStudentId(123);
        student.setAge("16");
        student.setEmail("student@example.com");
        student.setFirstname("John");
        student.setLastname("Murphy");
        student.setYearOfBirth("2002");


        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setAddress("MountainView,Calfornia,US");
        contactInfo.setFatherName("Benjamin");
        contactInfo.setMotherName("Riba");
        contactInfo.setMobileNumber("123456789");
        contactInfo.setEmail("contactinfo@example.com");


        student.setContactInfo(contactInfo);

        daoSession.getStudentDao().insertOrReplace(student);

        daoSession.getContactInfoDao().insertOrReplace(contactInfo);
    }

    private void queryResultFromDatabase() {
        QueryBuilder<Student> queryBuilder = daoSession.getStudentDao().queryBuilder();
        queryBuilder.join(ContactInfo.class, ContactInfoDao.Properties.Id);
        List<Student> joinedResult = queryBuilder.list();

        Gson gson = new Gson();
        String result = gson.toJson(joinedResult);
        ((TextView)findViewById(R.id.tvRedemptionData)).setText(""+result);
    }
}
