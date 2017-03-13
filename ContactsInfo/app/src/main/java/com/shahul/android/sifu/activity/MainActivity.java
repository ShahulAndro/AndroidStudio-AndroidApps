package com.shahul.android.sifu.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import com.shahul.android.sifu.R;
import shahul.mylibrary.adapter.RecyclerViewAdapter;
import shahul.network.retrofit.entity.ContactsInfo;
import shahul.network.retrofit.entity.ContactsResult;
import com.shahul.android.sifu.factory.ContactInfoFactory;
import com.shahul.android.sifu.listener.OnContactInfoUpdateListener;
import com.shahul.android.sifu.validator.ContactInfoDataValidator;

import java.util.ArrayList;

import shahul.mylibrary.listener.OnRecyclerViewItemClickListener;
import shahul.mylibrary.listener.RecyclerViewScrollListener;

import shahul.mylibrary.decoration.DividerItemDecoration;
import shahul.mylibrary.view.LoadingOverlay;
import shahul.network.retrofit.client.ApiClient;
import shahul.network.retrofit.client.ApiResult;
import shahul.network.retrofit.api.ContactsAPI;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();

    private LinearLayoutManager mLayoutManager;
    private RecyclerViewScrollListener mRecyclerViewScrollListener;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    private Subscription mContactResultSubscription;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivity = this;

        mRecyclerView = (RecyclerView) findViewById(R.id.rvContacts);
        mLayoutManager = new LinearLayoutManager(mActivity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        mRecyclerViewScrollListener = new RecyclerViewScrollListener(mLayoutManager);
        mRecyclerView.addOnScrollListener(mRecyclerViewScrollListener);

        mRecyclerViewAdapter = new RecyclerViewAdapter(new ContactInfoFactory( new ContactInfoItemUpdateListener()));
        mRecyclerViewAdapter.setOnItemClickListener(new MessageClickListener());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        mContactResultSubscription = getContactsResultWithLoadingIndicator();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    //Loading indicator with get contact results
    private Subscription getContactsResultWithLoadingIndicator() {
        ContactsAPI contactInfoService = ApiClient.getService(ContactsAPI.class);
        Observable<ApiResult<ContactsResult>> observable = contactInfoService
                .getAllContacts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());


        observable = observable.doOnSubscribe(new Action0() {
            @Override
            public void call() {
                LoadingOverlay.show(mActivity);
            }
        }).doOnTerminate(new Action0() {
            @Override
            public void call() {
                LoadingOverlay.dismiss(mActivity);
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LoadingOverlay.dismiss(mActivity);
                Log.e(TAG, "Exception: " + throwable);
            }
        });

        return observable.subscribe(new Action1<ApiResult<ContactsResult>>() {
            @Override
            public void call(ApiResult<ContactsResult> apiResult) {
                if (apiResult != null && apiResult.d != null && apiResult.d.getContactsInfoList() != null) {
                    updateResult(apiResult);
                }
            }
        });

    }

    //Direct call get contact results using rx + retrofit
    Observer<ApiResult<ContactsResult>> myObserver = new Observer<ApiResult<ContactsResult>>() {

        @Override
        public void onCompleted() {
                mContactResultSubscription.unsubscribe();
            }

        @Override
        public void onError(Throwable e) {
            // Called when the observable encounters an error
            Log.d(TAG, ">>>> onError gets called : " + e.getMessage());
        }

        @Override
        public void onNext(ApiResult<ContactsResult> apiResult) {
                updateResult(apiResult);
            }
    };

    private void updateResult(ApiResult<ContactsResult> apiResult){
        ContactInfoDataValidator contactInfoDataValidator = new ContactInfoDataValidator();
        ArrayList<ContactsInfo> validatedContactInfoList = contactInfoDataValidator.getValidateMergedData(apiResult.d.getContactsInfoList());
        mRecyclerViewAdapter.addAll(validatedContactInfoList);
    }

    private void startContactInfoDetailsActivity(ContactsInfo contactsInfo, int position){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ContactInfoDetailsActivity.class);
        intent.putExtra("contactInfo", contactsInfo);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    private class ContactInfoItemUpdateListener implements OnContactInfoUpdateListener {
        @Override
        public void updateContactInfo (ContactsInfo contactsInfo, int position) {
            ContactsInfo contactInfo = (ContactsInfo) mRecyclerViewAdapter.getItem(position);
            if (contactInfo == null) {
                return;
            }

            mRecyclerViewAdapter.update(position, contactInfo);
        }
    }

    private class MessageClickListener implements OnRecyclerViewItemClickListener {
        @Override
        public void onItemClick (View view, int position) {
            ContactsInfo contactInfo = (ContactsInfo) mRecyclerViewAdapter.getItem(position);
            if (contactInfo == null) {
                return;
            }

            startContactInfoDetailsActivity(contactInfo, position);
        }
    }
}
