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

public class LabTestActivity extends AppCompatActivity {


    private String[][] packages=
            {
                    {"Package 1 : Full Body Checkup","","","","999"},
                    {"Package 2 : Blood Glucose Fasting","","","","299"},
                    {"Package 3 : COVID-19 Antibody - IgG","","","","899"},
                    {"Package 4 : Thyroid Check","","","","499"},
                    {"Package 5 : Immunity Check","","","","699"},
            };


    private String[] package_detail=

            {
                    "Blood Glucose Fasting\n" +
                            "Complete Hemogram\n" +
                            "HbA1c\n" +
                            "Iron Studies\n" +
                            "kidney Function Test\n" +
                            "LDH Lactate Dehydrogenase,Serum\n" +
                            "Lipid Profile\n" +
                            "Liver Function Test",
                    "Blood Glucose Fasting",
                    "COVID-19 Antibody - IgG",
                    "Thyroid Profile-Total (T3, T4 & TSH Ultra-Sensitive)",
                    "Complete Hemogram\n" +
                            "CRP (C Reactive Protein) Quantitative, Serum\n" +
                            "Iron Studies\n" +
                            "Kidney Function Test\n" +
                            "Vitamin D Total-25 Hydroxy\n" +
                            "Liver Function Test\n" +
                            "Lipid Profile"
            };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    TextView txtltname,txtlttitle,txtlttitle2;
    Button btnltback,btnltcart;
    ListView listviewlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnltback=findViewById(R.id.btnltback);
        btnltcart=findViewById(R.id.btnltcart);
        txtltname=findViewById(R.id.txtltname);
        txtlttitle=findViewById(R.id.txtlttitle);
        txtlttitle2=findViewById(R.id.txtlttitle2);
        listviewlt=findViewById(R.id.listviewlt);

        btnltback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost : "+packages[i][4]+"/-");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );

        listviewlt.setAdapter(sa);



        listviewlt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",package_detail[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }
        });

        btnltcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });
    }
}