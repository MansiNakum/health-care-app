package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button btnreg;
    TextView txt,txtreg,txtexistinguser;
    EditText edtcpass,edtuser,edtpass,edtemail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnreg=findViewById(R.id.btnreg);
        txt=findViewById(R.id.txt);
        txtreg=findViewById(R.id.txtreg);
        txtexistinguser=findViewById(R.id.txtexistinguser);
        edtcpass=findViewById(R.id.edtcpass);
        edtuser=findViewById(R.id.edtuser);
        edtpass=findViewById(R.id.edtpass);
        edtemail=findViewById(R.id.edtemail);

        txtexistinguser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, Login_Activity.class));
            }
        });

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtuser.getText().toString();
                String email=edtemail.getText().toString();
                String confirm=edtcpass.getText().toString();
                String password=edtpass.getText().toString();

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(username.length()==0||password.length()==0||confirm.length()==0||email.length()==0){
                    Toast.makeText(getApplicationContext(),"please fill all details",Toast.LENGTH_SHORT).show();
                }
                else{
                  if(password.compareTo(confirm)==0){
                      if(isvalid(password)){
                          db.register(username,email,password);
                          Toast.makeText(getApplicationContext(),"Record inserted",Toast.LENGTH_SHORT).show();
                          startActivity(new Intent(RegisterActivity.this,Login_Activity.class));
                      }else {
                          Toast.makeText(getApplicationContext(),"password must contain atleast 8 characters,having letter,digit and special symbolss",Toast.LENGTH_SHORT).show();
                      }
                  }else{
                      Toast.makeText(getApplicationContext(),"password and confirm password didn't match",Toast.LENGTH_SHORT).show();
                  }
                }
            }
        });
    }

    public static boolean isvalid(String passwordhere){
        int f1=0,f2=0,f3=0;
        if(passwordhere.length()<8){
            return false;
        }else {
            for(int p=0;p<passwordhere.length();p++){
                if(Character.isLetter(passwordhere.charAt(p))){
                    f1=1;
                }
            }
            for(int r=0;r<passwordhere.length();r++){
                if(Character.isDigit(passwordhere.charAt(r))){
                    f2=1;
                }
            }
            for(int s=0;s<passwordhere.length();s++){
                char c=passwordhere.charAt(s);
                if(c>=33 && c<=46 || c==64){
                    f3=1;
                }
            }
            if(f1==1 && f2==1 && f3==1)
                return true;
            return false;
        }
    }
}