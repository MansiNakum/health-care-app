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
import java.util.List;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1=
            {
                    {"Doctor Name : Ajit Saste","Hospital Address : Pimpri","Exp : 5yrs","Mobile No :9898989898","600"},
                    {"Doctor Name : Prasad Pawar","Hospital Address : Nigdi","Exp : 15yrs","Mobile No :7853627463","900"},
                    {"Doctor Name : Swapnil Kale","Hospital Address : pune","Exp : 8yrs","Mobile No :8964738564","300"},
                    {"Doctor Name : Deepak Deshmukh","Hospital Address : Chinchwad","Exp : 7yrs","Mobile No :9087356274","500"},
                    {"Doctor Name : Ashok Panda","Hospital Address : Katraj","Exp : 9yrs","Mobile No :9898989898","800"}
            };

    private String[][] doctor_details2=
            {
                    {"Doctor Name : Neelam Patil","Hospital Address : Pimpri","Exp : 6yrs","Mobile No :9547862036","600"},
                    {"Doctor Name : Swati Pawar","Hospital Address : Nigdi","Exp : 10yrs","Mobile No :9648576321","900"},
                    {"Doctor Name : Neeraja kale","Hospital Address : pune","Exp : 5yrs","Mobile No :8963214575","300"},
                    {"Doctor Name : Mayuri Deshmukh","Hospital Address : Chinchwad","Exp : 8yrs","Mobile No :7532698412","500"},
                    {"Doctor Name : Minakshi Panda","Hospital Address : Katraj","Exp : 9yrs","Mobile No :9758463215","800"}
            };

    private String[][] doctor_details3=
            {
                    {"Doctor Name : Seema Patil","Hospital Address : Pimpri","Exp : 7yrs","Mobile No :9566487521","600"},
                    {"Doctor Name : Pankaj Parab","Hospital Address : Nigdi","Exp : 13yrs","Mobile No :8563214859","900"},
                    {"Doctor Name : Monish Jain","Hospital Address : pune","Exp : 8yrs","Mobile No :9756852314","300"},
                    {"Doctor Name : Vishal Deshmukh","Hospital Address : Chinchwad","Exp : 6yrs","Mobile No :9624587412","500"},
                    {"Doctor Name : Shrikant Panda","Hospital Address : Katraj","Exp : 9yrs","Mobile No :9752416358","800"}
            };

    private String[][] doctor_details4=
            {
                    {"Doctor Name : Amol Gawade","Hospital Address : Pimpri","Exp : 5yrs","Mobile No :9584756932","600"},
                    {"Doctor Name : Prasad Pawar","Hospital Address : Nigdi","Exp : 15yrs","Mobile No :9548756321","900"},
                    {"Doctor Name : Nilesh Kale","Hospital Address : pune","Exp : 8yrs","Mobile No :7856321458","300"},
                    {"Doctor Name : Deepak Deshpande","Hospital Address : Chinchwad","Exp : 7yrs","Mobile No :8963245175","500"},
                    {"Doctor Name : Ashok Singh","Hospital Address : Katraj","Exp : 9yrs","Mobile No :8965742365","800"}
            };

    private String[][] doctor_details5=
            {
                    {"Doctor Name : Nilesh Borate","Hospital Address : Pimpri","Exp : 15yrs","Mobile No :8649571263","1600"},
                    {"Doctor Name : Pankaj Pawar","Hospital Address : Nigdi","Exp : 17yrs","Mobile No :9548763215","1900"},
                    {"Doctor Name : Swapnil Lele","Hospital Address : pune","Exp : 9yrs","Mobile No :9845632458","1300"},
                    {"Doctor Name : Deepak Kumar","Hospital Address : Chinchwad","Exp : 6yrs","Mobile No :7589632595","1500"},
                    {"Doctor Name : Ankul Panda","Hospital Address : Katraj","Exp : 9yrs","Mobile No :8549632145","1800"}
            };
    TextView txtddname,txtddtitle;
    Button btnddback;
    ListView listviewdd;

    HashMap<String,String> item;
    String[][] doctor_details={};

    ArrayList list;
    SimpleAdapter sa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        txtddname=findViewById(R.id.txtddname);
        txtddtitle=findViewById(R.id.txtddtitle);
        btnddback=findViewById(R.id.btnddback);
        listviewdd=findViewById(R.id.listviewdd);

        Intent it=getIntent();
        String title=it.getStringExtra("title");
        txtddtitle.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details=doctor_details1;
        else if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


            btnddback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("Line1",doctor_details[i][0]);
            item.put("Line2",doctor_details[i][1]);
            item.put("Line3",doctor_details[i][2]);
            item.put("Line4",doctor_details[i][3]);
            item.put("Line5","Cons Fees : "+doctor_details[i][4]+"/-");
            list.add(item);

            sa=new SimpleAdapter(this,list,
                    R.layout.multi_lines,
                    new String[]{"Line1","Line2","Line3","Line4","Line5"},
                    new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                    );

            ListView lst=findViewById(R.id.listviewdd);

            lst.setAdapter(sa);

            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it=new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                    it.putExtra("text1",title);
                    it.putExtra("text2",doctor_details[i][0]);
                    it.putExtra("text3",doctor_details[i][1]);
                    it.putExtra("text4",doctor_details[i][3]);
                    it.putExtra("text5",doctor_details[i][4]);
                    startActivity(it);
                }
            });
        }
    }
}