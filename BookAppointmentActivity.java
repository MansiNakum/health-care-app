package com.example.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText edtappfees,edtappfullname,edtappcontactnumber,edtappaddress;
    Button btnappdate,btnbookappointment,btnappback,btnapptime;
    TextView txtappdate,txtapp,txtheading,txtapptime;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        edtappaddress=findViewById(R.id.edtappaddress);
        edtappcontactnumber=findViewById(R.id.edtappcontactnumber);
        edtappfullname=findViewById(R.id.edtappfullname);
        edtappfees=findViewById(R.id.edtappfees);
        txtapptime=findViewById(R.id.txtapptime);
        txtheading=findViewById(R.id.txtheading);
        txtapp=findViewById(R.id.txtapp);
        txtappdate=findViewById(R.id.txtappdate);
        btnappback=findViewById(R.id.btnappback);
        btnbookappointment=findViewById(R.id.btnbookappointment);
        btnappdate=findViewById(R.id.btnappdate);
        btnapptime=findViewById(R.id.btnapptime);

        edtappaddress.setKeyListener(null);
        edtappcontactnumber.setKeyListener(null);
        edtappfullname.setKeyListener(null);
        edtappfees.setKeyListener(null);

        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");

        txtapp.setText(title);
        edtappfullname.setText(fullname);
        edtappaddress.setText(address);
        edtappcontactnumber.setText(contact);
        edtappfees.setText("Cons Fees : "+fees+"/-");

        //datepicker
        initDatePicker();
        btnappdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //timepicker
        initTimePicker();
        btnapptime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        btnappback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointmentActivity.this,FindDoctorActivity.class));
            }
        });

        btnbookappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Database db=new Database(getApplicationContext(),"healthcare",null,1);
                    SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    String username=sharedPreferences.getString("username","").toString();

                    if(db.checkAppointmentExists(username,title+"=>"+fullname,address,contact,btnappdate.getText().toString(),btnapptime.getText().toString())==1){
                        Toast.makeText(getApplicationContext(),"Appointment already booked",Toast.LENGTH_LONG).show();
                    }else{
                        db.addOrder(username,title+"=>"+fullname,address,contact, 0,btnappdate.getText().toString(),btnapptime.getText().toString(),Float.parseFloat(fees),"appointment");
                            Toast.makeText(getApplicationContext(),"Appointment is done successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(BookAppointmentActivity.this,HomeActivity.class));
                    }
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                 btnappdate.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.BUTTON_NEUTRAL;    //THEME_HOLO_DARK
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);

    }
        private void initTimePicker(){
            TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    btnapptime.setText(i+":"+i1);
                }
            };
            Calendar cal = Calendar.getInstance();
            int hrs=cal.get(Calendar.HOUR);
            int mins=cal.get(Calendar.MINUTE);


           int style = AlertDialog.BUTTON_NEUTRAL;
            timePickerDialog=new TimePickerDialog(this,style,timeSetListener,hrs,mins,true);
    }
}