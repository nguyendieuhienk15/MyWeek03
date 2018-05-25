package com.example.ndh.tracegps;

import com.google.android.gms.maps.model.LatLng;

public class MyGps {
    public MyGps(){

    }

    public LatLng getLocation(){



        return new LatLng(0, 0);
    }

    public LatLng onLocationChange(){
        return new LatLng(100, 100);
    }
}
