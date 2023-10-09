package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    CardView cardlabtest,cardbuymedicine,cardfinddoctor,cardhealthdoctor,orderdetails,cardexit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences sharedpreferences=getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
        String username=sharedpreferences.getString("username"," ").toString();
        Toast.makeText(getApplicationContext(),"welcome  "+username,Toast.LENGTH_SHORT).show();

        cardlabtest=findViewById(R.id.cardlabtest);
        cardbuymedicine=findViewById(R.id.cardbuymedicine);
        cardfinddoctor=findViewById(R.id.cardfinddoctor);
        cardhealthdoctor=findViewById(R.id.cardhealthdoctor);
        orderdetails=findViewById(R.id.orderdetails);
        cardexit=findViewById(R.id.cardexit);

        cardexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=sharedpreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(new Intent(HomeActivity.this,Login_Activity.class));
            }
        });


        cardfinddoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,FindDoctorActivity.class));
            }
        });

        cardlabtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,LabTestActivity.class));
            }
        });

        orderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,OrderDetailsActivity.class));
            }
        });

        cardbuymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,BuyMedicineActivity.class));
            }
        });

        cardhealthdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,HealthArticalsActivity.class));
            }
        });
    }
}