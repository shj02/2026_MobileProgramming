package com.inhatc.android_firebase3;

import java.util.HashMap;
import java.util.Map;

public class CustomerInfo {
    public String strName;
    public String strPhone_No;

    public CustomerInfo() {

    }

    public CustomerInfo(String Name, String Phone_No) {
        this.strName = Name;
        this.strPhone_No = Phone_No;
    }
    public void mSet_CInfo (String Name, String Phone_No){
        this.strName = Name;
        this.strPhone_No = Phone_No;
    }
    public String mGet_CName() { return strName; }
    public String mGet_CPhone_No() { return strPhone_No; }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("Name", strName);
        result.put("Phone_No", strPhone_No);
        return result;
    }
}
