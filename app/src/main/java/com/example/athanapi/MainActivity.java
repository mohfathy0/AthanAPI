package com.example.athanapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    TextView textView ,textViewTimeToPray;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textviewResponse);
        editText = findViewById(R.id.editTextAddress);
        textViewTimeToPray = findViewById(R.id.textViewTimeToPray);

    }

    private void GetAthanTimeByLocation(String s) {
        AdhanAPI client = ServiceGenerator.CreateService(AdhanAPI.class);
        Call<PrayerTime> call = client.getPrayerTime(s);
        call.enqueue(new Callback<PrayerTime>() {
            @Override
            public void onResponse(Call<PrayerTime> call, Response<PrayerTime> response) {
                SendLog(response.message() + " \n" + response.body().getData().getTimings().getFajr());
                StringBuilder sbf= new StringBuilder("");

                SimpleDateFormat current = new SimpleDateFormat("HH:mm");

                Date currentDate = new Date();

                ArrayList<String> prayerTimes = new ArrayList<>();
                prayerTimes.add(response.body().getData().getTimings().getFajr() );
                prayerTimes.add(response.body().getData().getTimings().getDhuhr() );
                prayerTimes.add(response.body().getData().getTimings().getAsr() );
                prayerTimes.add(response.body().getData().getTimings().getMaghrib() );
                prayerTimes.add(response.body().getData().getTimings().getIsha() );




                Date PrayerTime = new Date();
                DateFormat Prayer = new SimpleDateFormat("HH:mm");
               Prayer.setLenient(true);
                boolean prayertimefound=false;
               for (int x =0;x<prayerTimes.size()-1;x++){



                      if( CompareTime(prayerTimes,x)){
               //        if (Prayer.parse(prayerTimes.get(x)).compareTo(currentDate)>0 &&Prayer.parse(prayerTimes.get(x+1)).compareTo(currentDate)<0){
                      // if (currentDate.compareTo(Prayer.parse(prayerTimes.get(x)))>0 && currentDate.compareTo(Prayer.parse(prayerTimes.get(x+1)))<0 ){

                           switch (prayerTimes.indexOf(prayerTimes.get(x))){
                               case 0:
                                   textViewTimeToPray.setText("It is time to pray Fajer");

                                   prayertimefound=true;
                                   break;
                               case 1:
                                   textViewTimeToPray.setText("It is time to pray Duhr");
                                   prayertimefound=true;
                                   break;
                               case 2:
                                   textViewTimeToPray.setText("It is time to pray Asr");
                                   prayertimefound=true;
                                   break;
                               case 3:
                                   textViewTimeToPray.setText("It is time to pray Maghrib");
                                   prayertimefound=true;
                                   break;
                               case 4:
                                   textViewTimeToPray.setText("It is time to pray Ishaa");
                                   prayertimefound=true;
                                   break;
                           }

                          if (prayertimefound ){
                              break;
                          }
                       }


               }

                String str = "08:03";


                try {
                    PrayerTime = Prayer.parse(str);
                } catch (Exception e) {

                }


                String placeHolder =
                        "Fajr: " + response.body().getData().getTimings().getFajr() + "\n" +
                        "Dhuhr: " + response.body().getData().getTimings().getDhuhr() + "\n" +
                        "Asr: " + response.body().getData().getTimings().getAsr() + "\n" +
                        "Maghrib: " + response.body().getData().getTimings().getMaghrib() + "\n" +
                        "Isha: " + response.body().getData().getTimings().getIsha()+ "\n\n" +
                                "\nResponse message: " + response.message() + "\n" +
                                "Response Code: " + response.code() ;
                sbf.append(placeHolder);
                textView.setText(sbf);
            }

            @Override
            public void onFailure(Call<PrayerTime> call, Throwable t) {
                SendLog(t.getMessage());
                textView.setText(t.getMessage());
            }
        });

    }

    public void SendLog(String message) {
        Log.i("mylog_MainAcritivity", message);
    }

    public void onClick(View view) {
        textView.setText("");
        GetAthanTimeByLocation(editText.getText().toString());
    }


    public boolean CompareTime(ArrayList<String> prayerTime, int x){
        if (x<5) {
            Calendar Now = Calendar.getInstance();
            Now.setTime(new Date());
            Now.set(1970, Calendar.JANUARY, 1);

            Calendar prayertimeCalender = Calendar.getInstance();
            Calendar prayertimeCalenderNext = Calendar.getInstance();
            DateFormat Prayer = new SimpleDateFormat("HH:mm");
            Date d = null;
            Date d2 = null;
            try {
                d = Prayer.parse(prayerTime.get(x));
                d2 = Prayer.parse(prayerTime.get(x + 1));

            } catch (ParseException e) {
                e.printStackTrace();
            }
            prayertimeCalender.setTime(d);

            prayertimeCalenderNext.setTime(d2);

            // if (Prayer.parse(prayerTimes.get(x)).compareTo(currentDate)>0 &&Prayer.parse(prayerTimes.get(x+1)).compareTo(currentDate)<0){
            return  (Now.compareTo(prayertimeCalender) > 0 && Now.compareTo(prayertimeCalenderNext) < 0);


        }else{
            return true;
        }

    }
}
