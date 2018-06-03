package com.ngocdiem.map.Map.HienThiCacDIaDiemXUngQuanhTrenBAnDo;


import com.ngocdiem.map.Map.Route;

import java.util.ArrayList;

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(ArrayList<Route> item);
}
