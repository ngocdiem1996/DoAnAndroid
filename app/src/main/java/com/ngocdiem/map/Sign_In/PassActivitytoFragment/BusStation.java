package com.ngocdiem.map.Sign_In.PassActivitytoFragment;

import com.squareup.otto.Bus;

public class BusStation {
    private static Bus bus = new Bus();
    public static Bus getBus() {
        return bus;
    }
}
