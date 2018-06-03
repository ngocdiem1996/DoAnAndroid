package com.ngocdiem.map.Sign_In.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.ngocdiem.map.Map.layTenCumDiaDiem.MainActivity;
import com.ngocdiem.map.R;
import com.ngocdiem.map.Sign_In.ChuyenTabLogin_TabSignUp.ChuyenTabLogin_SignUp;
import com.ngocdiem.map.Sign_In.PassActivitytoFragment.BusStation;
import com.ngocdiem.map.Sign_In.ThongBaoThongTinDangNhapCu.UserLoginBefore;
import com.ngocdiem.map.Sign_In.TruyenDuLieuDangKi.TransactionSignUp;
import com.ngocdiem.map.Sign_In.TruyenDuLieuDangNhap.TransactionLogin;
import com.ngocdiem.map.Sign_In.UserDatabase.DatabaseHandler;
import com.ngocdiem.map.Sign_In.UserDatabase.UserInfo;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainMainActivity extends FragmentActivity implements TransactionLogin, TransactionSignUp,ChuyenTabLogin_SignUp {
    private android.support.v4.app.FragmentManager fm;
    private Login_fm login_fm;
    private SignUp_fm signup_fm;
    private DatabaseHandler databaseHandler;
    private Realm realm;
    private int flag = 0;

    //0 la lan dau tien
    //1 la lan dang xuat va xoa du lieu cu, vao luu lai luc dang nhap

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        databaseHandler = new DatabaseHandler(this);

        Realm.init(this);
        realm= Realm.getDefaultInstance();
        realm.beginTransaction();
//        realm.deleteAll(); //xoa toan bo data trong realm database
        realm.commitTransaction();

        if(databaseHandler.getNumUser()!=0){
                RealmResults<UserLoginBefore> userLoginBefore = realm.where(UserLoginBefore.class).findAll();
                if(userLoginBefore.size()!=0){
                    UserInfo user = databaseHandler.getUser(userLoginBefore.get(userLoginBefore.size()-1).getNameUser());
//                    Intent myListSearch = new Intent(MainMainActivity.this, ListSearch.class);
//                    myListSearch.putExtra("userinfo",user);
//                    startActivity(myListSearch);
                Intent myListSearch = new Intent(MainMainActivity.this, MainActivity.class);
                startActivity(myListSearch);
                }
        }

        fm = getSupportFragmentManager();
        login_fm = new Login_fm();
        signup_fm = new SignUp_fm();

        //chay fragment 1
        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.add(R.id.framelayout_main,login_fm);
        ft_add.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //chay fragment 1
        FragmentTransaction ft_add = fm.beginTransaction();
        ft_add.replace(R.id.framelayout_main,login_fm);
        ft_add.commit();

    }

    @Override
    public void duLieuDangKi( UserInfo userInfoSignUp ) {
        if(!databaseHandler.checkUserExisted(userInfoSignUp)){
            databaseHandler.addUser(userInfoSignUp);
            BusStation.getBus().post("signup_thanhcong");
        }else{
            BusStation.getBus().post("signup_thatbai");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public void duLieuDangNhap( final String usernameLogin, String passwordLogin ) {
            if(databaseHandler.checkUserLogin(usernameLogin,passwordLogin)){
                Toast.makeText(getBaseContext(),"Sign-in successfully", Toast.LENGTH_SHORT).show();
                UserInfo user = databaseHandler.getUser(usernameLogin);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        UserLoginBefore userBefore = realm.createObject(UserLoginBefore.class);
                        userBefore.setNameUser(usernameLogin);
                    }
                });

//                Intent myListSearch = new Intent(MainMainActivity.this, ListSearch.class);
//                myListSearch.putExtra("userinfo",user);
//                startActivity(myListSearch);
                Intent myListSearch = new Intent(MainMainActivity.this, MainActivity.class);
                startActivity(myListSearch);

            }else{
                BusStation.getBus().post("login_thatbai");
            }

    }

    @Override
    public void chuyebTabLogin_SignUP( Fragment fragment ) {
        if(fragment == login_fm){
            FragmentTransaction ft_add = fm.beginTransaction();
            ft_add.replace(R.id.framelayout_main,signup_fm);
            ft_add.commit();
        }
        if(fragment == signup_fm){
            FragmentTransaction ft_add = fm.beginTransaction();
            ft_add.replace(R.id.framelayout_main,login_fm);
            ft_add.commit();
        }
    }

}
