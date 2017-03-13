package com.shahul.android.sifu.factory;

import android.content.Context;
import android.view.View;

import com.shahul.android.sifu.R;
import shahul.mylibrary.factory.IViewHolderFactory;
import com.shahul.android.sifu.listener.OnContactInfoUpdateListener;
import shahul.mylibrary.viewholder.BaseViewHolder;
import com.shahul.android.sifu.viewholder.ContactInfoViewHolder;

/**
 * Created by shahulhameed on 12/03/2017.
 */

public class ContactInfoFactory implements IViewHolderFactory {

    private OnContactInfoUpdateListener mListener;

    public ContactInfoFactory (OnContactInfoUpdateListener listener) {
        mListener = listener;
    }

    public BaseViewHolder create(Context context) {
        View view = View.inflate(context, R.layout.row_contacts, null);
        ContactInfoViewHolder contactInfoViewHolder = new ContactInfoViewHolder(view);
        contactInfoViewHolder.setContactsInfoItemUpdateClickListener(mListener);
        return contactInfoViewHolder;
    }
}
