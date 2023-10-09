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

public class Login_Activity extends AppCompatActivity {
    EditText edtloginusername,edtloginpassword;
    TextView txttitle,txtlogin,txtnewuser;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtloginpassword=findViewById(R.id.edtloginpassword);
        edtloginusername=findViewById(R.id.edtloginusername);
        txttitle=findViewById(R.id.txttitle);
        txtlogin=findViewById(R.id.txtlogin);
        txtnewuser=findViewById(R.id.txtnewuser);
        btnlogin=findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //
                // startActivity(new Intent(Login_Activity.this,HomeActivity.class));
                String username=edtloginusername.getText().toString();
                String password=edtloginpassword.getText().toString();
                Database db=new Database(getApplicationContext(),"healthcare",null,1);
                if(username.length()==0||password.length()==0){
                    Toast.makeText(getApplicationContext(),"please fill all details",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(db.login(username,password)==1){
                        Toast.makeText(getApplicationContext(),"login successfully",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedpreferences=getSharedPreferences("shared_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedpreferences.edit();
                        editor.putString("username",username);
                        //to save data with key and value
                        editor.apply();
                        startActivity(new Intent(Login_Activity.this,HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Invalid Username And Password",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        txtnewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this, RegisterActivity.class));
            }
        });
    }
}