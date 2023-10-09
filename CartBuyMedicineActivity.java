package com.example.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {

    Button btncbmcheckout,btncbmback,btncbmappdate,btncbmapptime;
    ListView listviewcbm;
    TextView txtcbmname,txtcbmtitle2,txtcbmtitle,txtcbmtotalcost,txtcbmappdate,txtcbmapptime;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    private String[][] packages={};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        btncbmcheckout=findViewById(R.id.btncbmcheckout);
        btncbmback=findViewById(R.id.btncbmback);
        btncbmappdate=findViewById(R.id.btncbmappdate);
        listviewcbm=findViewById(R.id.listviewcbm);
        txtcbmname=findViewById(R.id.txtcbmname);
        txtcbmtitle2=findViewById(R.id.txtcbmtitle2);
        txtcbmtitle=findViewById(R.id.txtcbmtitle);
        txtcbmtotalcost=findViewById(R.id.txtcbmtotalcost);
        txtcbmappdate=findViewById(R.id.txtcbmappdate);


        btncbmback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartBuyMedicineActivity.this,BuyMedicineActivity.class));
            }
        });

        btncbmcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(CartBuyMedicineActivity.this,MedicineBookActivity.class);
                it.putExtra("price",txtcbmtotalcost.getText());
                it.putExtra("date",btncbmappdate.getText());
//                it.putExtra("time",btncbmapptime.getText());
                startActivity(it);
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username","").toString();

        Database db=new Database(getApplicationContext(),"healthcare",null,1);

        float totalAmount=0;
        ArrayList dbData=db.getCartData(username,"medicine");

        packages=new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i]=new String[5];
        }

        for(int i=0;i<dbData.size();i++){
            String arrData=dbData.get(i).toString();
            String[] strData=arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0]=strData[0];
            packages[i][4]="Cost : "+strData[1]+"/-";
            totalAmount=totalAmount + Float.parseFloat(strData[1]);
        }

        txtcbmtotalcost.setText("Total Cost : "+totalAmount);

        list=new ArrayList();
        for(int i=0;i<packages.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        listviewcbm.setAdapter(sa);

        initDatePicker();
        btncbmappdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });


    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                btncbmappdate.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.BUTTON_NEUTRAL;    //THEME_HOLO_DARK
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);

    }



}
