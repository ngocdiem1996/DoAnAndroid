package com.ngocdiem.map.Map.layTenCumDiaDiem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ngocdiem.map.Map.GPSTracker;
import com.ngocdiem.map.Map.LocationItem;
import com.ngocdiem.map.Map.HienThiDanhSachTheoCumDiaDiemDaChon.PlaceFinderListener;
import com.ngocdiem.map.R;
import com.ngocdiem.map.Sign_In.Login.MainMainActivity;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements PlayerListener, PlaceFinderListener {
    private ArrayList<NameLocation> mLocation;
    private ListView mListLocationMain;
    public static Double sStandingLatitude, sStandingLongitude;
    private GPSTracker mGps;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListLocationMain = (ListView) findViewById(R.id.lv_location);
        mLocation = new ArrayList<>();
        NameLocation location0 = new NameLocation("RESTAURANT");
        NameLocation location1 = new NameLocation("HOTEL");
        NameLocation location2 = new NameLocation("SCHOOL");
        NameLocation location3 = new NameLocation("HOSPITAL");
        NameLocation location4 = new NameLocation("ATM");
        NameLocation location5 = new NameLocation("BANK");
        NameLocation location6 = new NameLocation("CAFE");
        NameLocation location7 = new NameLocation("SUPERMARKET");
        mLocation.add(location0);
        mLocation.add(location1);
        mLocation.add(location2);
        mLocation.add(location3);
        mLocation.add(location4);
        mLocation.add(location5);
        mLocation.add(location6);
        mLocation.add(location7);

        mGps = new GPSTracker(MainActivity.this);
        if(mGps.canGetLocation()){
            sStandingLatitude = mGps.getLatitude();
            sStandingLongitude = mGps.getLongitude();
        } else {
            mGps.showSettingsAlert();
        }
        DataLocationMain dataLocationMain = new DataLocationMain(this, R.layout.location_main_item, mLocation, this, this);
        mListLocationMain.setAdapter(dataLocationMain);
    }

    @Override
    public void onClickPlayer(int pos, Intent intent) {
        startActivity(intent);
    }

    @Override
    public void onPlaceFinderStart() {

    }

    @Override
    public void onPlaceFinderSuccess(ArrayList<LocationItem> item) {

    }

    @Override
    public void addMarkerNearMyLocation(ArrayList<LocationItem> item) {
    }
    public void onClickLogout(View view){
        Realm.init(this);
        realm= Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        Toast.makeText(getApplicationContext(), "Successfull Logout", Toast.LENGTH_SHORT).show();
//        Intent intent = new Intent(this,LoginActivity.class);
        Intent intent = new Intent(this,MainMainActivity.class);
        startActivity(intent);
    }

}
