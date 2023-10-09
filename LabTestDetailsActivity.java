package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView txtltdname,txtltdtitle,txtltdtitle2,txttotalcost;
    Button btnltdback,btnltdcart;
    EditText edtltd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        btnltdback=findViewById(R.id.btnltdback);
        btnltdcart=findViewById(R.id.btnltdcart);
        txtltdname=findViewById(R.id.txtltdname);
        txtltdtitle=findViewById(R.id.txtltdtitle);
        txtltdtitle2=findViewById(R.id.txtltdtitle2);
        edtltd=findViewById(R.id.edtltd);
        txttotalcost=findViewById(R.id.txttotalcost);

        edtltd.setKeyListener(null);

        Intent intent=getIntent();
        txtltdtitle2.setText(intent.getStringExtra("text1"));
        edtltd.setText(intent.getStringExtra("text2"));
        txttotalcost.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");


        btnltdback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        btnltdcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=txtltdtitle2.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }else
                {
                    db.addcart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(),"Record Inserted To Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }


            }
        });
    }
}