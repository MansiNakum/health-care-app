package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    TextView txt,txtltbtitle;
    EditText edtfullname,edtaddress,edtpincode,edtcontact;
    Button btnbook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        txt=findViewById(R.id.txt);
        txtltbtitle=findViewById(R.id.txtltbtitle);
        edtfullname=findViewById(R.id.edtfullname);
        edtaddress=findViewById(R.id.edtaddress);
        edtpincode=findViewById(R.id.edtpincode);
        edtcontact=findViewById(R.id.edtcontact);
        btnbook=findViewById(R.id.btnbook);


        Intent intent=getIntent();
        String[] price=intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");


        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                db.addOrder(username,edtfullname.getText().toString(),edtaddress.getText().toString(),edtcontact.getText().toString(),Integer.parseInt(edtpincode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removecart(username,"lab");

                Toast.makeText(getApplicationContext(),"Your Booking Is Done Successfully",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LabTestBookActivity.this,HomeActivity.class));
            }
        });

    }
}