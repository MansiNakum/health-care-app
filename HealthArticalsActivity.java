package com.example.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticalsActivity extends AppCompatActivity {

    TextView txttitle,txtlogo3;
    ListView listartical;
    Button btnback;

    private String[][] health_deatails=
            {
                    {"Walking Daily","","","","Click For More Details"},
                    {"Home Care of COVID-19","","","","Click For More Details"},
                    {"Stop Smoking","","","","Click For More Details"},
                    {"Menstrual Cramps","","","","Click For More Details"},
                    {"Healthy Gut","","","","Click For More Details"},

            };

    private int[] images={
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,

    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_articals);

        txttitle=findViewById(R.id.txttitle);
        txtlogo3=findViewById(R.id.txtlogo3);
        listartical=findViewById(R.id.listartical);
        btnback=findViewById(R.id.btnback);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticalsActivity.this,HomeActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<health_deatails.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",health_deatails[i][0]);
            item.put("line2",health_deatails[i][1]);
            item.put("line3",health_deatails[i][2]);
            item.put("line4",health_deatails[i][3]);
            item.put("line4",health_deatails[i][4]);
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}

        );
        listartical.setAdapter(sa);


        listartical.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(HealthArticalsActivity.this,HealthArticlesDetailActivity.class);
                it.putExtra("text1",health_deatails[i][0]);
                it.putExtra("text2",images[i]);

                startActivity(it);
            }
        });



    }


}