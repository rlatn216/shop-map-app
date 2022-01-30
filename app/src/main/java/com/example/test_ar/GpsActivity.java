package com.example.test_ar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.PermissionChecker;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;

import java.util.Calendar;
import java.util.Date;

public class GpsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;


    private MapView mapView;
    private DrawerLayout drawerLayout;
    private View drawerView, drawerView_c;
    Date a;
    private static Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        //메뉴 레이아웃
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerView = (View)findViewById(R.id.drawer);
        drawerView_c = (View)findViewById(R.id.drawer_c);

        //지도 객체 생성
        mapView = findViewById(R.id.map);
        mapView.getMapAsync(this);
        locationSource =
                new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);


        //스와핑 막는거
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        // 메뉴 버튼
        ImageButton menubtn_open = (ImageButton)findViewById(R.id.menubtn_open);
        ImageButton menubtn_close = (ImageButton)findViewById(R.id.menubtn_close);



        menubtn_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        menubtn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });

        drawerLayout.setDrawerListener(listener);
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });



    }


    //메뉴 레이아웃 리스너
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View view, float v) {
        }

        @Override
        public void onDrawerOpened(@NonNull View view) {
        }

        @Override
        public void onDrawerClosed(@NonNull View view) {
        }

        @Override
        public void onDrawerStateChanged(int i) {
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,  @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(
                requestCode, permissions, grantResults)) {
            return;
        }
        super.onRequestPermissionsResult(
                requestCode, permissions, grantResults);
    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        //추적모드 및 위치 설정
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Face);

        LocationOverlay locationOverlay = naverMap.getLocationOverlay();
        locationOverlay.setVisible(true);

        //정보 버튼
        ImageButton back_btn = (ImageButton)findViewById(R.id.back_btn);


        Button select_btn1 = (Button)findViewById(R.id.select_1);
        Button select_btn2 = (Button)findViewById(R.id.select_2);
        Button select_btn3 = (Button)findViewById(R.id.select_3);
        Button select_btn4 = (Button)findViewById(R.id.select_4);
        Button select_btn5 = (Button)findViewById(R.id.select_5);

        Marker[] marker_f = new Marker[10];
        Marker[] marker_c = new Marker[10];
        Marker[] marker_s = new Marker[10];


        //음식점 마크 지정

        for(int i = 0; i < 6; i++) {
            marker_f[i] = new Marker();
        }

        marker_f[0].setPosition(new LatLng(35.896706, 128.849133));

        marker_f[1].setPosition(new LatLng(35.896663, 128.849870));

        marker_f[2].setPosition(new LatLng(35.896678, 128.850056));

        marker_f[3].setPosition(new LatLng(35.896772, 128.850336));

        marker_f[4].setPosition(new LatLng(35.896639, 128.850306));

        marker_f[5].setPosition(new LatLng(35.896556, 128.851377));


        for(int i = 0; marker_f[i] != null; i++) {
            marker_f[i].setIcon(MarkerIcons.BLACK);
            marker_f[i].setIconTintColor(Color.parseColor("#01DB77"));
            marker_f[i].setWidth(90);
            marker_f[i].setHeight(120);
            marker_f[i].setMap(naverMap);
        }


        // 카페

        for(int i = 0; i < 5; i++) {
            marker_c[i] = new Marker();
        }

        marker_c[0].setPosition(new LatLng(35.896780, 128.849226));

        marker_c[1].setPosition(new LatLng(35.896715, 128.849360));

        marker_c[2].setPosition(new LatLng(35.896790, 128.849583));

        marker_c[3].setPosition(new LatLng(35.896887, 128.850533));

        marker_c[4].setPosition(new LatLng(35.896590, 128.851202));

        for(int i = 0; marker_c[i] != null; i++) {
            marker_c[i].setIcon(MarkerIcons.BLACK);
            marker_c[i].setIconTintColor(Color.parseColor("#01DB77"));
            marker_c[i].setWidth(90);
            marker_c[i].setHeight(120);
            marker_c[i].setMap(naverMap);
        }


        //Bar

        Marker marker_b1 = new Marker();
        marker_b1.setPosition(new LatLng(35.896806, 128.849101));
        marker_b1.setIcon(MarkerIcons.BLACK);
        marker_b1.setIconTintColor(Color.parseColor("#01DB77"));
        marker_b1.setWidth(90);
        marker_b1.setHeight(120);
        marker_b1.setMap(naverMap);




        for(int i = 0; i < 3; i++) {
            marker_s[i] = new Marker();
        }


        marker_s[0].setPosition(new LatLng(35.896688, 128.850093));

        marker_s[1].setPosition(new LatLng(35.896847, 128.851087));

        marker_s[2].setPosition(new LatLng(35.896644, 128.850569));

        for(int i = 0; marker_s[i] != null; i++) {
            marker_s[i].setIcon(MarkerIcons.BLACK);
            marker_s[i].setIconTintColor(Color.parseColor("#01DB77"));
            marker_s[i].setWidth(90);
            marker_s[i].setHeight(120);
            marker_s[i].setMap(naverMap);
        }


        // 마크 map에 제거
        for(int i = 0;  marker_c[i] != null; i++) {
            marker_c[i].setVisible(false);
        }

        for(int i = 0;  marker_s[i] != null; i++) {
            marker_s[i].setVisible(false);
        }

        for(int i = 0;  marker_f[i] != null; i++) {
            marker_f[i].setVisible(false);
        }

        marker_b1.setVisible(false);


        // 마크 클릭시

        marker_c[1].setOnClickListener(o -> {
            drawerLayout.openDrawer(drawerView_c);
            return true;
        });


        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
            }
        });



        select_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;  marker_c[i] != null; i++) {
                    marker_c[i].setVisible(false);
                }

                for(int i = 0;  marker_s[i] != null; i++) {
                    marker_s[i].setVisible(false);
                }

                for(int i = 0; marker_f[i] != null; i++) {
                    marker_f[i].setVisible(true);
                }
                marker_b1.setVisible(false);
            }
        });

        select_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;  marker_c[i] != null; i++) {
                    marker_c[i].setVisible(true);
                }

                for(int i = 0;  marker_s[i] != null; i++) {
                    marker_s[i].setVisible(false);
                }

                for(int i = 0; marker_f[i] != null; i++) {
                    marker_f[i].setVisible(false);
                }

                marker_b1.setVisible(false);
            }
        });

        select_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0;  marker_s[i] != null; i++) {
                    marker_s[i].setVisible(true);
                }
                for(int i = 0;  marker_c[i] != null; i++) {
                    marker_c[i].setVisible(false);
                }
                for(int i = 0; marker_f[i] != null; i++) {
                    marker_f[i].setVisible(false);
                }
                marker_b1.setVisible(false);
            }
        });

        select_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                marker_b1.setVisible(true);

                for(int i = 0;  marker_c[i] != null; i++) {
                    marker_c[i].setVisible(false);
                }

                for(int i = 0; marker_f[i] != null; i++) {
                    marker_f[i].setVisible(false);
                }

                for(int i = 0;  marker_s[i] != null; i++) {
                    marker_s[i].setVisible(false);
                }

            }
        });

        select_btn5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                for(int i = 0;  marker_c[i] != null; i++) {
                    marker_c[i].setVisible(false);
                }

                for(int i = 0; marker_f[i] != null; i++) {
                    marker_f[i].setVisible(false);
                }

                for(int i = 0;  marker_s[i] != null; i++) {
                    marker_s[i].setVisible(false);
                }
                marker_b1.setVisible(false);
            }
        });


        //시간 타이머 설정
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Calendar cal = Calendar.getInstance() ;
                a = (Date)cal.getTime();

                //영업 시간별 마크 표시
                if(a.getHours()>10 && a.getHours()<23) {
                    marker_c[0].setIcon(MarkerIcons.BLACK);
                    marker_c[0].setIconTintColor(Color.parseColor("#01DB77"));
                    marker_c[2].setIcon(MarkerIcons.BLACK);
                    marker_c[2].setIconTintColor(Color.parseColor("#01DB77"));
                }
                else {
                    marker_c[0].setIcon(MarkerIcons.BLACK);
                    marker_c[0].setIconTintColor(Color.RED);
                    marker_c[2].setIcon(MarkerIcons.BLACK);
                    marker_c[2].setIconTintColor(Color.RED);
                }

                if(a.getHours() > 8 && (a.getHours() < 22 || a.getMinutes() < 30)) {
                    marker_c[1].setIcon(MarkerIcons.BLACK);
                    marker_c[1].setIconTintColor(Color.parseColor("#01DB77"));
                    marker_c[3].setIcon(MarkerIcons.BLACK);
                    marker_c[3].setIconTintColor(Color.parseColor("#01DB77"));
                    marker_c[4].setIcon(MarkerIcons.BLACK);
                    marker_c[4].setIconTintColor(Color.parseColor("#01DB77"));
                }
                else {
                    marker_c[1].setIcon(MarkerIcons.BLACK);
                    marker_c[1].setIconTintColor(Color.RED);
                    marker_c[3].setIcon(MarkerIcons.BLACK);
                    marker_c[3].setIconTintColor(Color.RED);
                    marker_c[4].setIcon(MarkerIcons.BLACK);
                    marker_c[4].setIconTintColor(Color.RED);
                }

                if((a.getHours() > 17 && a.getHours() < 24) || (a.getHours() < 5 && a.getHours() >= 0)){
                    marker_b1.setIcon(MarkerIcons.BLACK);
                    marker_b1.setIconTintColor(Color.parseColor("#01DB77"));
                }
                else {
                    marker_b1.setIcon(MarkerIcons.BLACK);
                    marker_b1.setIconTintColor(Color.RED);
                    System.out.println(a.getHours());
                }

            }
        } ;

        class NewRunnable implements Runnable {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace() ;
                    }
                    mHandler.sendEmptyMessage(0) ;
                }
            }
        }
        NewRunnable nr = new NewRunnable() ;
        Thread t = new Thread(nr) ;
        t.start() ;



        //카메라 설정

        CameraPosition cameraPosition = new CameraPosition(
                new LatLng(locationOverlay.getPosition().latitude, locationOverlay.getPosition().longitude), // 대상 지점
                17
        );
        naverMap.setCameraPosition(cameraPosition);





        // 제스쳐

        UiSettings uiSettings = naverMap.getUiSettings();

        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        uiSettings.setStopGesturesEnabled(true);

        //컨트롤러

        uiSettings.setCompassEnabled(false);
        uiSettings.setZoomControlEnabled(true);
        uiSettings.setLocationButtonEnabled(false);





    }
}