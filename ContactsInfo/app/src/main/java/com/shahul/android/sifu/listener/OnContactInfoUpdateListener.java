package com.shahul.android.sifu.listener;

import shahul.network.retrofit.entity.ContactsInfo;

/**
 * Created by shahulhameed on 12/03/2017.
 */

public interface OnContactInfoUpdateListener {
    void updateContactInfo(ContactsInfo contactsInfo, int position);
}
