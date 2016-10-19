package com.smistrydev.isitdark;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.smistrydev.isitdark.rest.SunRestClient;
import com.smistrydev.isitdark.rest.TimeZoneCalConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.message.BasicHeader;

public class MainActivity extends AppCompatActivity {


    private String adMobId = "pub-2402990675093377";

    private static final String TAG = "MainThread";
    LocationManager mLocationManager;
    int update = 0;
    TextView answer;
    TextView longAns;

    private final LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            Log.v("WEAVER_", "Location Change: lat:" + location.getLatitude() + "long:" + location.getLongitude() + " updates");

            update++;
            if (update < 3) {
                MainActivity.this.getSunTimes(location);
            } else {
                update = 10;
            }


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer = (TextView) findViewById(R.id.textViewAnswer);
        longAns = (TextView) findViewById(R.id.textViewLong);
        answer.setText("");
        longAns.setText("");


        getLocation();
    }

    private void getSunTimes(Location location) {

        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("Accept", "application/json"));

        String url = "http://api.sunrise-sunset.org/json?lat=" + location.getLatitude() + "1&lng=" + location.getLongitude();

        SunRestClient.get(
                MainActivity.this, url, headers.toArray(new Header[headers.size()]), null,
                new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        Log.d(TAG, response.toString());


                        String status = MainActivity.this.getValue(response, "status");

                        try {
                            JSONObject results = response.getJSONObject("results");

//                            String day_length = MainActivity.this.getValue(results, "day_length");
                            String sunset = MainActivity.this.getValue(results, "sunset");
                            //                         String nautical_twilight_begin = MainActivity.this.getValue(results, "nautical_twilight_begin");
                            //                        String solar_noon = MainActivity.this.getValue(results, "solar_noon");
                            //                      String astronomical_twilight_end = MainActivity.this.getValue(results, "astronomical_twilight_end");
                            //                    String civil_twilight_end = MainActivity.this.getValue(results, "civil_twilight_end");
                            //                  String astronomical_twilight_begin = MainActivity.this.getValue(results, "astronomical_twilight_begin");
                            //                String nautical_twilight_end = MainActivity.this.getValue(results, "nautical_twilight_end");
                            String sunrise = MainActivity.this.getValue(results, "sunrise");
                            //              String civil_twilight_begin = MainActivity.this.getValue(results, "civil_twilight_begin");

                            TimeZoneCalConverter converter = new TimeZoneCalConverter(TimeZone.getTimeZone("UTC"));

                            Calendar sunRise = converter.getConvertedCal(sunrise, "h:mm:ss aa");
                            Calendar sunSet = converter.getConvertedCal(sunset, "h:mm:ss aa");

                            Calendar now = Calendar.getInstance();
                            if (now.before(sunRise) || now.after(sunSet)) {
                                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
                                relativeLayout.setBackgroundColor(Color.BLACK);
                                answer.setTextColor(Color.WHITE);
                                longAns.setTextColor(Color.WHITE);
                                answer.setText("YES");
                                longAns.setText("It is Dark outside.");
                            } else {
                                RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
                                relativeLayout.setBackgroundColor(Color.WHITE);
                                answer.setTextColor(Color.BLACK);
                                longAns.setTextColor(Color.BLACK);
                                answer.setText("NO");
                                longAns.setText("It is Not Dark outside.");
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }
        );


    }

    protected void getLocation() {
        Log.v(TAG, "GetLocation");
        int LOCATION_REFRESH_TIME = 1000;
        int LOCATION_REFRESH_DISTANCE = 5;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.v("WEAVER_", "Does not have permission");
        } else {
            Log.v("WEAVER_", "Has permission");
            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_REFRESH_TIME,
                    LOCATION_REFRESH_DISTANCE, mLocationListener);
        }

    }

    private String getValue(JSONObject result, String key) {
        String ret = null;
        try {
            ret = result.getString(key);
            Log.d(TAG, "key: " + key + " value: " + ret);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            return ret;
        }
    }

}
