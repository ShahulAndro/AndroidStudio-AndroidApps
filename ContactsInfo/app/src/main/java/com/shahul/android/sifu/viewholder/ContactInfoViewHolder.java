package com.shahul.android.sifu.viewholder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.shahul.android.sifu.R;
import shahul.network.retrofit.entity.ContactsInfo;
import com.shahul.android.sifu.listener.OnContactInfoUpdateListener;
import shahul.mylibrary.listener.OnRecyclerViewItemClickListener;
import shahul.mylibrary.viewholder.BaseViewHolder;
import shahul.network.retrofit.client.ApiClient;
import shahul.network.retrofit.api.ContactsAPI;

import java.io.InputStream;

import okhttp3.ResponseBody;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by shahulhameed on 12/03/2017.
 */

public class ContactInfoViewHolder extends BaseViewHolder<ContactsInfo> implements View.OnClickListener{
    private static final String TAG = ContactInfoViewHolder.class.getSimpleName();

    private ImageView mIvThumbnail;
    private TextView mTvFullName;
    private TextView mTvJobDescription;
    private View mRlRootView;;

    private int                             mPosition;
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    private ContactsInfo                     mContactsInfo;
    private Activity mActivity;
    private OnContactInfoUpdateListener mOnContactInfoUpdateListener;

    private Subscription mContactResultSubscription;

    public ContactInfoViewHolder (View view) {
        super(view);
        mActivity = (Activity) view.getContext();

        mRlRootView = view;
        mIvThumbnail = (ImageView) view.findViewById(R.id.ivThumbnail);
        mTvFullName = (TextView) view.findViewById(R.id.tvFullName);
        mTvJobDescription = (TextView) view.findViewById(R.id.tvJobDescription);

        mRlRootView.setOnClickListener(this);
    }

    @Override
    public void bindData (ContactsInfo contactsInfo) {
        if (contactsInfo == null) {
            return;
        }

        mContactsInfo = contactsInfo;

        updateContactsInfo();
    }

    @Override
    public void setPosition (int position) {
        mPosition = position;
    }

    @Override
    public void setOnItemClickListener (OnRecyclerViewItemClickListener listener, int position) {
        mOnItemClickListener = listener;
        mPosition = position;
    }

    public void onClick (View view) {
        if (mOnItemClickListener == null) {
            return;
        }

        mOnItemClickListener.onItemClick(view, mPosition);
    }

    public void setContactsInfoItemUpdateClickListener (OnContactInfoUpdateListener listener) {
        mOnContactInfoUpdateListener = listener;
    }

    private void updateContactsInfo () {
        String fullName = mContactsInfo.getFullName();
        if(fullName != null && !fullName.isEmpty()) {
            mTvFullName.setText(mContactsInfo.getFullName());
        }

        if(mContactsInfo.getJobTitleDescription() != null && !mContactsInfo.getJobTitleDescription().isEmpty()) {
            mTvJobDescription.setText(mContactsInfo.getJobTitleDescription());
        }

        if(mContactsInfo.getPictureThumbnailUrl() != null && !mContactsInfo.getPictureThumbnailUrl().isEmpty()) {
            getProfileThumbnailFromApi();
        }else {
            setThumbnailWithProfileNameText();
        }
    }

    private void setThumbnailWithProfileNameText(){
        String fullName = mContactsInfo.getFullName();
        if(fullName == null || fullName.isEmpty()) {
            fullName = mContactsInfo.getFirstName()+ " " + mContactsInfo.getLastName();
        }
        String[] fullNamesSplitArray = fullName.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for(int fullNameSplitCount = 0; fullNameSplitCount < fullNamesSplitArray.length; fullNameSplitCount++){
            String firstLetter = String.valueOf(fullNamesSplitArray[fullNameSplitCount].charAt(0));
            if(firstLetter == null  || firstLetter.isEmpty()){
                continue;
            }

            stringBuilder.append(firstLetter);
        }

        ColorGenerator generator = ColorGenerator.MATERIAL;
        // generate random color
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(stringBuilder.toString(), color,10); // radius in px
        mIvThumbnail.setImageDrawable(drawable);
    }


    private void getProfileThumbnailFromApi(){
        ContactsAPI service = ApiClient.getService(ContactsAPI.class);

        mContactResultSubscription = service.getProfileThumbnail()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(myProfileThumbNailObserver);
    }

    private void updateProfileThumbnail(InputStream inputStream){
        if(inputStream != null) {
            try {
                Bitmap thumbnail = BitmapFactory.decodeStream(inputStream);
                mIvThumbnail.setImageBitmap(thumbnail);
            }catch (Exception ex){
                Log.e("Profile Thumbnail","Excpetion"+ex);
            }
        }
    }

    Observer<ResponseBody> myProfileThumbNailObserver = new Observer<ResponseBody>() {

        @Override
        public void onCompleted() {
            mContactResultSubscription.unsubscribe();
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, ">>>> onError gets called : " + e.getMessage());
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            if(responseBody == null) {
                return;
            }

            InputStream inputStream = responseBody.byteStream();
            if(inputStream == null){
                return;
            }

            updateProfileThumbnail(inputStream);
        }
    };
}
