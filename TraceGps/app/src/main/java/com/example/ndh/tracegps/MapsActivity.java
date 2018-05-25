package com.example.ndh.tracegps;

import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.Callable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Disposable gpsSubscription;
    private MyGps gps = new MyGps();

    private Marker currentMarker;
    private LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        currentMarker = null;
        currentLocation = new LatLng(0, 0);

        mapFragment.getMapAsync(this);

        createObservable();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        Log.d("dummylog", "map ready");
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (gpsSubscription != null && !gpsSubscription.isDisposed()) {
            gpsSubscription.dispose();
        }
    }

    private void createObservable() {
        io.reactivex.Observable<LatLng> gpsObservable = io.reactivex.Observable.fromCallable(new Callable<LatLng>() {
            @Override
            public LatLng call() throws Exception {
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        Log.d("dummylog", "location changed");
                        LatLng newPos = new LatLng(location.getLatitude(), location.getLongitude());
                        if (newPos != currentLocation)
                            currentLocation = newPos;

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                };

                Log.d("dummylog", "current location: " + currentLocation.latitude + ", " + currentLocation.longitude);
                return currentLocation;
            }
        });

        gpsSubscription = gpsObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LatLng>() {
                    @Override
                    public void accept(LatLng latLng) throws Exception {
                        currentLocation = latLng;
                        drawMarker();
                    }
                });
    }

    void drawMarker(){
        if (currentMarker != null)
            currentMarker.remove();
        currentMarker = mMap.addMarker(new MarkerOptions().position(currentLocation));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
    }
}
