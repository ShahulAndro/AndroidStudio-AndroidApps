package com.shahul.android.sifu.validator;

import java.util.ArrayList;
import java.util.List;

import shahul.network.retrofit.entity.ContactsInfo;

/**
 * Created by shahulhameed on 13/03/2017.
 */

public class ContactInfoDataValidator {

    public ArrayList<ContactsInfo> getValidateMergedData(List<ContactsInfo> contactsInfoArrayList){

        ArrayList<ContactsInfo> mergerList = new ArrayList<>();

        for(int i = 0; i < contactsInfoArrayList.size(); i++){
            if(!isContactInfoValid(contactsInfoArrayList.get(i))){
                continue;
            }

            ContactsInfo mergerObject =  null;

            for (int j = 1; j < contactsInfoArrayList.size(); j++){
                if(mergerList != null && mergerList.size() > 0){

                }
                if(mergerObject == null) {
                    mergerObject = getMergeContactInfo(contactsInfoArrayList.get(i), contactsInfoArrayList.get(j));
                    continue;
                }

                mergerObject = getMergeContactInfo(mergerObject, contactsInfoArrayList.get(j));
            }

            if(mergerObject != null && !isAlreadyMerged(mergerList, mergerObject)){
                mergerList.add(mergerObject);
            }
        }

        return mergerList;
    }

    private boolean isAlreadyMerged(ArrayList<ContactsInfo> arrayList, ContactsInfo contactsInfo) {

        if (arrayList == null || arrayList.size() < 1) {
            return false;
        }

        for (ContactsInfo existingContactInfo : arrayList) {
            if ((existingContactInfo.getAccount() == null || existingContactInfo.getAccount().isEmpty())
                    || (contactsInfo.getAccount() == null || contactsInfo.getAccount().isEmpty())) {
                continue;
            }

            if (existingContactInfo.getAccount().equalsIgnoreCase(contactsInfo.getAccount())) {
                return true;
            }
        }

        return false;
    }

    private boolean isContactInfoValid(ContactsInfo contactsInfo){
        if(contactsInfo.getFirstName() == null || contactsInfo.getFirstName().isEmpty()){
            return false;
        }

        if(contactsInfo.getAccount() == null || contactsInfo.getAccount().isEmpty()){
            return false;
        }

        return true;
    }

    private ContactsInfo getMergeContactInfo(ContactsInfo mergerObject, ContactsInfo comparistionObject){

        if((comparistionObject.getFullName() != null && !comparistionObject.getAccount().isEmpty()) &&
                (mergerObject.getFullName() == null || mergerObject.getAccount().isEmpty()) ){
            mergerObject.setFullName(comparistionObject.getFullName());
        }

        if((comparistionObject.getPictureThumbnailUrl() != null && !comparistionObject.getPictureThumbnailUrl().isEmpty()) &&
                (mergerObject.getPictureThumbnailUrl() == null || mergerObject.getPictureThumbnailUrl().isEmpty()) ){
            mergerObject.setPictureThumbnailUrl(comparistionObject.getPictureThumbnailUrl());
        }

        if((comparistionObject.getNotes() != null && !comparistionObject.getNotes().isEmpty()) &&
                (mergerObject.getNotes() == null || mergerObject.getNotes().isEmpty()) ){
            mergerObject.setNotes(comparistionObject.getPictureThumbnailUrl());
        }

        if((comparistionObject.getBusinessEmail() != null && !comparistionObject.getBusinessEmail().isEmpty()) &&
                (mergerObject.getBusinessEmail() == null || mergerObject.getBusinessEmail().isEmpty()) ){
            mergerObject.setBusinessEmail(comparistionObject.getBusinessEmail());
        }

        if((comparistionObject.getBusinessMobile() != null && !comparistionObject.getBusinessMobile().isEmpty()) &&
                (mergerObject.getBusinessMobile() == null || mergerObject.getBusinessMobile().isEmpty()) ){
            mergerObject.setBusinessMobile(comparistionObject.getBusinessMobile());
        }

        if((comparistionObject.getBusinessPhone() != null && !comparistionObject.getBusinessPhone().isEmpty()) &&
                (mergerObject.getBusinessPhone() == null || mergerObject.getBusinessPhone().isEmpty()) ){
            mergerObject.setBusinessPhone(comparistionObject.getBusinessPhone());
        }

        if((comparistionObject.getEmail() != null && !comparistionObject.getEmail().isEmpty()) &&
                (mergerObject.getEmail() == null || mergerObject.getEmail().isEmpty()) ){
            mergerObject.setEmail(comparistionObject.getEmail());
        }

        if((comparistionObject.getGender() != null && !comparistionObject.getGender().isEmpty()) &&
                (mergerObject.getGender() == null || mergerObject.getGender().isEmpty()) ){
            mergerObject.setGender(comparistionObject.getGender());
        }

        if((comparistionObject.getId() != null && !comparistionObject.getId().isEmpty()) &&
                (mergerObject.getId() == null || mergerObject.getId().isEmpty()) ){
            mergerObject.setId(comparistionObject.getId());
        }

        return mergerObject;
    }
}
