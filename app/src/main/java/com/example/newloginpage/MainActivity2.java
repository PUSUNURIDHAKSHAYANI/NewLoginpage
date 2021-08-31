package com.example.newloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1=findViewById(R.id.sname);
        t2=findViewById(R.id.semail);
        t3=findViewById(R.id.spass);
        t4=findViewById(R.id.sdob);
        t5=findViewById(R.id.sspin);
        t6=findViewById(R.id.gen);
        SharedPreferences sharedPreferences=getSharedPreferences("pref",MODE_PRIVATE);
        String ssname=sharedPreferences.getString("fullname","");
        t1.setText(ssname);
        String ssemail=sharedPreferences.getString("email","");
        t2.setText(ssemail);
        String sspassword=sharedPreferences.getString("password","");
        t3.setText(sspassword);
        String ssdob=sharedPreferences.getString("date_of_birth","");
        t4.setText(ssdob);
        String sspin=sharedPreferences.getString("states","");
        t5.setText(sspin);
        String ssgen=sharedPreferences.getString("states","");
        t5.setText(ssgen);



    }
}