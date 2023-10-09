package com.example.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {
    CardView cardfdfamilyphysician,cardfddietician,cardfddentist,cardfdsurgeon,cardfdcardiologist,cardfdback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

        cardfdfamilyphysician=findViewById(R.id.cardfdfamilyphysician);
        cardfddietician=findViewById(R.id.cardfddietician);
        cardfddentist=findViewById(R.id.cardfddentist);
        cardfdsurgeon=findViewById(R.id.cardfdsurgeon);
        cardfdcardiologist=findViewById(R.id.cardfdcardiologist);
        cardfdback=findViewById(R.id.cardfdback);

        cardfdback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this,HomeActivity.class));
            }
        });

        cardfdfamilyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Family Physician");
                startActivity(it);
            }
        });

        cardfddietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Dietician");
                startActivity(it);
            }
        });

        cardfddentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Dentist");
                startActivity(it);
            }
        });

        cardfdsurgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Surgeon");
                startActivity(it);
            }
        });

        cardfdcardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(FindDoctorActivity.this,DoctorDetailsActivity.class);
                it.putExtra("title","Cardiologist");
                startActivity(it);
            }
        });
    }
}