package com.ngocdiem.map.Sign_In.ThongBaoThongTinDangNhapCu;

import io.realm.RealmObject;

public class UserLoginBefore extends RealmObject {
    private String nameUser;

    public UserLoginBefore(){

    }
    public UserLoginBefore( String nameUser ) {
        this.nameUser = nameUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser( String nameUser ) {
        this.nameUser = nameUser;
    }
}
