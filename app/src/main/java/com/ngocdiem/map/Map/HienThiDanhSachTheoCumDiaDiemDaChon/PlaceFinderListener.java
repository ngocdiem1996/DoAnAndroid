package com.ngocdiem.map.Map.HienThiDanhSachTheoCumDiaDiemDaChon;


import com.ngocdiem.map.Map.LocationItem;

import java.util.ArrayList;

public interface PlaceFinderListener {
    void onPlaceFinderStart();
    void onPlaceFinderSuccess(ArrayList<LocationItem> item);
    void addMarkerNearMyLocation(ArrayList<LocationItem> item);
}
