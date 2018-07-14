package com.android.greendao.insertlistobject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
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
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setId("1");
        companyDetails.setDateTime("2108-Jul-14");
        companyDetails.setAddress("Netherlands");
        companyDetails.setBranchId("1");
        companyDetails.setName("Google");
        companyDetails.setNumberOfEmployees("200000");


        Employee employee1 = new Employee();
        employee1.setId("1");
        employee1.setBranchId("1");
        employee1.setDeptNumber("123");
        employee1.setEmployeedId("666666");
        employee1.setEmployeeType("Software Engineer");
        employee1.setSalary("$100000");

        Employee employee2 = new Employee();
        employee2.setId("2");
        employee2.setBranchId("1");
        employee2.setDeptNumber("123");
        employee2.setEmployeedId("777777");
        employee2.setEmployeeType("Manager");
        employee2.setSalary("$300000");

        Employee employee3 = new Employee();
        employee3.setId("3");
        employee3.setBranchId("1");
        employee3.setDeptNumber("321");
        employee3.setEmployeedId("999999");
        employee3.setEmployeeType("CEO");
        employee3.setSalary("$600000");

        List<Employee> employeesList = new ArrayList<>();
        employeesList.add(employee1);
        employeesList.add(employee2);
        employeesList.add(employee3);



        daoSession.getCompanyDetailsDao().insertOrReplace(companyDetails);

        daoSession.getEmployeeDao().insertOrReplaceInTx(employeesList);;
    }

    private void queryResultFromDatabase() {
        QueryBuilder<CompanyDetails> queryBuilder = daoSession.getCompanyDetailsDao().queryBuilder();
        queryBuilder.join(Employee.class, EmployeeDao.Properties.BranchId)
                .where(EmployeeDao.Properties.BranchId.eq("1"));
        List<CompanyDetails> joinedResult = queryBuilder.list();

        Gson gson = new Gson();
        String result = gson.toJson(joinedResult);
        ((TextView)findViewById(R.id.tvRedemptionData)).setText(""+result);
    }
}
