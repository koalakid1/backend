package com.naver.busposition;

import androidx.fragment.app.FragmentActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ///// (1) Bus Route ID 구하기 task1 로 저장 /////
        String serviceUrl = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList";
        String serviceKey = "qRJ5CTEZAk2DYXTwql9Oqqc%2FDuY9%2F3v2pIzkjKLFz%2BIkvs5gfpYWqIJy7u%2BGwn5ebw3HxNw0tmzobu1m96UDKg%3D%3D";
        String strSrch = "6623";
        Log.d("strSrch값",strSrch);
        String strUrl = serviceUrl+"?ServiceKey="+serviceKey+"&strSrch="+strSrch;

        DownloadWebpageTask1 task1 = new DownloadWebpageTask1();
        task1.execute(strUrl);
    }

    private class DownloadWebpageTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            try {
                return (String)downloadUrl((String)urls[0]);
            } catch (IOException e) {
                return "다운로드 실패";
            }
        }

        protected void onPostExecute(String result) {
            Log.d("버스ID",result);
            String headerCd = "";//헤더코드
            String busRouteId = "";//노선ID
            String busRouteNm = "";//노선이름

            boolean bSet_headerCd = false; //태그를 찾으면 true라고 바꾸기위해 설정
            boolean bSet_busRouteId = false;
            boolean bSet_busRouteNm = false;

            //뭘하고 있는거냐면 찾고 싶은게 3개이다. headerCd, busRouteId, busRouteNm
            //busRouteId 의 텍스트값 (100100301같은) 을 찾았으면
            //그 바로 앞의 태그값이 busRouteId인지 어쩐지를 확인하는데
            //그 태그가 있다면 boolean 값을 true라고 바꿔놓았다.

            ///// (1) Bus Route ID /////
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) { //태그가 문서의 끝이냐
                    if(eventType == XmlPullParser.START_DOCUMENT) { //문서의 시작이면
                        ; //아무것도 없음 우리가 찾으려고 하는게 문서의 시작이 아니라 태그를 찾으려고 하는거라서
                    } else if(eventType == XmlPullParser.START_TAG) {   //시작태그이면
                        String tag_name = xpp.getName();    //태그의 이름을 구해옴
                        if (tag_name.equals("headerCd")) //태그의 이름이 headerCd 이면
                            bSet_headerCd = true; // boolean 값을 true 라고 함
                        if (tag_name.equals("busRouteId"))
                            bSet_busRouteId = true;
                        if (tag_name.equals("busRouteNm"))
                            bSet_busRouteNm = true;
                    } else if(eventType == XmlPullParser.TEXT) { //그게 아니라 텍스트이면
                        if (bSet_headerCd) {
                            headerCd = xpp.getText();
                            bSet_headerCd = false;
                        }

                        if (headerCd.equals("0")) {
                            if (bSet_busRouteId) {
                                busRouteId = xpp.getText();
                                bSet_busRouteId = false;
                            }
                            if (bSet_busRouteNm) {
                                busRouteNm = xpp.getText();
                                bSet_busRouteNm = false;
                            }
                        }
                    } else if(eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
            }

            ///// (2) RouteID 로 Bus Position 얻어내기 task2 로 저장 /////
            String serviceUrl = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid";
            String serviceKey = "qRJ5CTEZAk2DYXTwql9Oqqc%2FDuY9%2F3v2pIzkjKLFz%2BIkvs5gfpYWqIJy7u%2BGwn5ebw3HxNw0tmzobu1m96UDKg%3D%3D";
            String strUrl = serviceUrl+"?ServiceKey="+serviceKey+"&busRouteId="+busRouteId;

            DownloadWebpageTask2 task2 = new DownloadWebpageTask2();
            task2.execute(strUrl);
        }

        private String downloadUrl(String myurl) throws IOException {

            HttpURLConnection conn = null;
            try {
                URL url = new URL(myurl);
                conn = (HttpURLConnection) url.openConnection();
                BufferedInputStream buf = new BufferedInputStream(conn.getInputStream());
                BufferedReader bufreader = new BufferedReader(new InputStreamReader(buf, "utf-8"));
                String line = null;
                String page = "";
                while((line = bufreader.readLine()) != null) {
                    page += line;
                }

                return page;
            } finally {
                conn.disconnect();
            }
        }
    }
    private class DownloadWebpageTask2 extends DownloadWebpageTask1 {

        protected void onPostExecute(String result) {
            Log.d("xml데이터",result);
            String headerCd = "";
            String plainNo = "";
            String gpsX = "";
            String gpsY = "";

            boolean bSet_headerCd = false;
            boolean bSet_gpsX = false;
            boolean bSet_gpsY = false;
            boolean bSet_plainNo = false;

            ///// (2) Bus Positions
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();

                xpp.setInput(new StringReader(result));
                int eventType = xpp.getEventType();

                int count = 0;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if(eventType == XmlPullParser.START_DOCUMENT) {
                        ;
                    } else if(eventType == XmlPullParser.START_TAG) {
                        String tag_name = xpp.getName();
                        if (tag_name.equals("headerCd"))
                            bSet_headerCd = true;
                        if (tag_name.equals("gpsX"))
                            bSet_gpsX = true;
                        if (tag_name.equals("gpsY"))
                            bSet_gpsY = true;
                        if (tag_name.equals("plainNo"))
                            bSet_plainNo = true;
                    } else if(eventType == XmlPullParser.TEXT) {
                        if (bSet_headerCd) {
                            headerCd = xpp.getText();
                            bSet_headerCd = false;
                        }

                        if (headerCd.equals("0")) {
                            if (bSet_gpsX) {
                                count++;

                                gpsX = xpp.getText();
                                bSet_gpsX = false;
                            }
                            if (bSet_gpsY) {
                                gpsY = xpp.getText();
                                bSet_gpsY = false;
                            }
                            if (bSet_plainNo) {
                                plainNo = xpp.getText();
                                bSet_plainNo = false;

                                //구글맵에 출력
                                displayBus(gpsX, gpsY, plainNo);
                            }
                        }
                    } else if(eventType == XmlPullParser.END_TAG) {
                        ;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
            }
        }

        // 버스 위치 표시 marker 로 표시하고 지도에 표시하는 메소드
        // 버스가 여러대니까 버스마다 위치를 찍을 메소드를 만들어서 처리함
        private void displayBus(String gpsX, String gpsY, String plainNo) {

            double latitude;
            double longitude;
            LatLng LOC;

            latitude = Double.parseDouble(gpsY);
            longitude = Double.parseDouble(gpsX);
            LOC = new LatLng(latitude, longitude);
            Marker mk1 = mMap.addMarker(new MarkerOptions()
                    .position(LOC)
                    .title(plainNo)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bus)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(LOC));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(14));

        }
    }

}
