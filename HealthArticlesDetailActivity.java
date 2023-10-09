package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticlesDetailActivity extends AppCompatActivity {

    TextView texttitle,texttitle2;
    ImageView imghealth;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articles_detail);

        texttitle=findViewById(R.id.texttitle);
        texttitle2=findViewById(R.id.texttitle2);
        imghealth=findViewById(R.id.imghealth);
        btnback=findViewById(R.id.btnback);

        Intent intent=getIntent();
        texttitle2.setText(intent.getStringExtra("text1"));

        Bundle bundle=getIntent().getExtras();
        if(bundle != null){
            int resId=bundle.getInt("text2");
            imghealth.setImageResource(resId);
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesDetailActivity.this,HealthArticalsActivity.class));
            }
        });

    }
}