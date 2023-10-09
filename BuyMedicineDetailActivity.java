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

public class BuyMedicineDetailActivity extends AppCompatActivity {
    TextView txtbmdname,txtbmdtitle,txtbmdtitle2,txttotalcost;
    Button btnbmdback,btnbmdcart;
    EditText edtbmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_detail);
        txtbmdname=findViewById(R.id.txtbmdname);
        txtbmdtitle=findViewById(R.id.txtbmdtitle);
        txtbmdtitle2=findViewById(R.id.txtbmdtitle2);
        btnbmdback=findViewById(R.id.btnbmdback);
        btnbmdcart=findViewById(R.id.btnbmdcart);
        edtbmd=findViewById(R.id.edtbmd);
        txttotalcost=findViewById(R.id.txttotalcost);

        edtbmd.setKeyListener(null);

        Intent intent=getIntent();
        txtbmdtitle2.setText(intent.getStringExtra("text1"));
        edtbmd.setText(intent.getStringExtra("text2"));
        txttotalcost.setText("Total Cost : "+intent.getStringExtra("text3")+"/-");

        btnbmdback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));
            }
        });

        btnbmdcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=txtbmdtitle2.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkcart(username,product)==1){
                    Toast.makeText(getApplicationContext(),"Product Already Added",Toast.LENGTH_SHORT).show();
                }else
                {
                    db.addcart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Record Inserted To Cart",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailActivity.this,BuyMedicineActivity.class));
                }


            }
        });

    }
}