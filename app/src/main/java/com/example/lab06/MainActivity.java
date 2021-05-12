package com.example.lab06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ListStudentAdapter adt;
    ArrayList<Student> arrayList = new ArrayList<Student>();

    ListView lvPlace;
    ListPlaceAdapter adtPlace;
    ArrayList<Place> arrayListPlace = new ArrayList<Place>();

    private DatabaseHandler db= new DatabaseHandler(this);

    private DatabaseHandler2 db2= new DatabaseHandler2(this);

    private Button btnAdd ;
    private Button btnremove;
    private Button btnCancel;
    private EditText pltName;

    private Button btnAdd2 ;
    private Button btnCancel2;
    private EditText pltPlace;
    private ImageView imgUpdate;
    private ImageView imgRemove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        bai1();
        bai2();

    }

    public void bai2(){
        setContentView(R.layout.activity_main);
//        Place place=new Place(1,"Đà Lạt");
//        Place place1=new Place(2,"HCM");
//        Place place2=new Place(3,"HN");
//        Place place3=new Place(4,"Đà Lạt");
//        Place place4=new Place(5,"Đà Lạt");
//        Place place5=new Place(6,"Đà Lạt");
//        db2.adPlace(place1);
//        db2.adPlace(place2);
//        db2.adPlace(place3);
//        db2.adPlace(place);
//        db2.adPlace(place4);
//        db2.adPlace(place5);
        arrayListPlace = db2.getAllPlace();

        lvPlace = findViewById(R.id.listView2);
        adtPlace = new ListPlaceAdapter(this, R.layout.item_bai2, arrayListPlace);
        lvPlace.setAdapter(adtPlace);

        btnAdd2=findViewById(R.id.btnSave);
        btnCancel2=findViewById(R.id.btnCancel2);
        pltPlace= findViewById(R.id.tplPlace);

        btnAdd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pltPlace.getText()!=null){
                    Place pl= new Place(pltPlace.getText().toString().trim());
                    db2.adPlace(pl);
                }
                arrayListPlace = db2.getAllPlace();
                lvPlace = findViewById(R.id.listView2);
                adtPlace = new ListPlaceAdapter(v.getContext(), R.layout.item_bai2, arrayListPlace);
                lvPlace.setAdapter(adtPlace);
            }
        });

        btnCancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        lvPlace.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place= arrayListPlace.get(position);
                pltPlace.setText(place.getPlace().toString());
                imgUpdate=findViewById(R.id.imgEdit);
                imgRemove=findViewById(R.id.imgRemove);

                imgUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Place pltUpdate= new Place(place.getId(),pltPlace.getText().toString().trim());
                        db2.updatePlace(pltUpdate);
                        arrayListPlace = db2.getAllPlace();
                        lvPlace = findViewById(R.id.listView2);
                        adtPlace = new ListPlaceAdapter(v.getContext(), R.layout.item_bai2, arrayListPlace);
                        lvPlace.setAdapter(adtPlace);
                    }
                });

                imgRemove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        db2.deletePlace(place.getId());

                        arrayListPlace = db2.getAllPlace();
                        lvPlace = findViewById(R.id.listView2);
                        adtPlace = new ListPlaceAdapter(v.getContext(), R.layout.item_bai2, arrayListPlace);
                        lvPlace.setAdapter(adtPlace);
                    }
                });
            }
        });

    }

    public void bai1(){
        setContentView(R.layout.layout1);
        Student student= new Student(1,"Phuoc");
        Student student1= new Student(2,"Phuoc");
        Student student2= new Student(3,"Phuoc");
        Student student3= new Student(4,"Phuoc");
        db.addStudent(student);
        db.addStudent(student1);
        db.addStudent(student2);
        db.addStudent(student3);
        arrayList =db.getAllStudents();

        lv = findViewById(R.id.listview);
        adt = new ListStudentAdapter(this, R.layout.item, arrayList);
        lv.setAdapter(adt);

        btnAdd= findViewById(R.id.btnAdd);
        btnremove=findViewById(R.id.btnRemove);
        pltName=findViewById(R.id.tpName);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name= pltName.getText().toString().trim();
                int id= db.getAllStudents().size()+1;
                Student st= new Student(id,name);
                addStudent(st);
                arrayList =db.getAllStudents();

                lv = findViewById(R.id.listview);
                adt = new ListStudentAdapter(view.getContext(), R.layout.item, arrayList);
                lv.setAdapter(adt);

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student st = arrayList.get(i);
                btnremove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        removeStudent(st.getId());
                        arrayList =db.getAllStudents();

                        lv = findViewById(R.id.listview);
                        adt = new ListStudentAdapter(view.getContext(), R.layout.item, arrayList);
                        lv.setAdapter(adt);
                    }
                });
            }
        });
        btnCancel=findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pltName.setText("");

            }
        });
    }
    public  void addStudent(Student student){
        db.addStudent(student);
    }
    public void removeStudent(int id){
        db.deleteStudent(id);
    }

}