package com.example.athanapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textviewResponse);
        editText=findViewById(R.id.editTextAddress);


    }

    private void GetAthanTimeByLocation(String s) {
        AdhanAPI client = ServiceGenerator.CreateService(AdhanAPI.class);
        Call<PrayerTime> call = client.getPrayerTime(s);
        call.enqueue(new Callback<PrayerTime>() {
            @Override
            public void onResponse(Call<PrayerTime> call, Response<PrayerTime> response) {
                SendLog(response.message()+" \n"+response.body().getData().getTimings().getFajr());
                textView.setText("Response message: "+ response.message()+"\n"+
                        "Response Code: "+ response.code()+"\n"+"\n"+
                        "Fajr: "+ response.body().getData().getTimings().getFajr()+"\n"+
                        "Dhuhr: "+ response.body().getData().getTimings().getDhuhr()+"\n"+
                        "Asr: "+ response.body().getData().getTimings().getAsr()+"\n"+
                        "Maghrib: "+ response.body().getData().getTimings().getMaghrib()+"\n"+
                        "Isha: "+ response.body().getData().getTimings().getIsha()


                );
            }

            @Override
            public void onFailure(Call<PrayerTime> call, Throwable t) {
                SendLog(t.getMessage());
            textView.setText(t.getMessage());
            }
        });

    }

    public void SendLog(String message){
        Log.i("mylog_MainAcritivity",message);
    }

    public void onClick(View view){
        textView.setText("");
        GetAthanTimeByLocation(editText.getText().toString());
    }
}
