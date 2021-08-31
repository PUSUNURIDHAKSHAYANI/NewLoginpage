package com.example.newloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText e1,e2,e3,e4;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button button;
    Spinner spinner;
    String states[]={"select any state","Telegana","Andra Pradesh"};

    String password_pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,30}$";
    String email_pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
    Matcher emailmatcher,passwordmatcher;
    Pattern emailpattern,passwordpattern;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.name);
        e2 = findViewById(R.id.email);
        e3 = findViewById(R.id.password);
        e4 = findViewById(R.id.dob);
        radioGroup = findViewById(R.id.radiog);
        Spinner spinner = findViewById(R.id.spin);
        button = findViewById(R.id.sb);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, states);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };


        e4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ename = e1.getText().toString();
                String eemail = e2.getText().toString();
                String epassword = e3.getText().toString();
                String edob = e4.getText().toString();
                emailpattern = Pattern.compile(email_pattern);
                emailmatcher = emailpattern.matcher(eemail);
                passwordpattern = Pattern.compile(password_pattern);
                passwordmatcher = passwordpattern.matcher(epassword);

                if (isEmpty(ename)) {
                    e1.setError("name should  not be empty");
                    if (isEmpty(eemail)) {
                        e2.setError("email should not be empty");
                    } else if (!emailmatcher.matches()) {
                        e2.setError("Enter valid email");
                    } else if (isEmpty(epassword)) {
                        e3.setError("pasword should not be empty");
                    } else if (!passwordmatcher.matches()) {
                        e3.setError("enter valid password");
                    } else if (isEmpty(edob)) {
                        e4.setError("dob should not be empty");
                    } else {
                        // Toast.makeText(getApplicationContext(),"Submitted"+radioButton.getText().toString(),Toast.LENGTH_SHORT).show();
                        Intent j = new Intent(getApplicationContext(), MainActivity2.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("fullname", ename);
                        editor.putString("email", eemail);
                        editor.putString("password", epassword);
                        editor.putString("states", String.valueOf(spinner));
                        editor.putString("date_of_birth", edob);
                        editor.putString("gender", radioButton.getText().toString());
                        editor.apply();
                        startActivity(j);
                    }



            }


            });






            private void updateLabel() {
                String myFormat = "MM/dd/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                e4.setText(sdf.format(myCalendar.getTime()));
            }


        });
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
        }
