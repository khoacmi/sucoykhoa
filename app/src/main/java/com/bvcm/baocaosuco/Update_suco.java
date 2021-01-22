package com.bvcm.baocaosuco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.bvcm.baocaosuco.model.Suco_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Update_suco extends AppCompatActivity {
    Button btn_udcapnhat, btn_huy;
    EditText edt_uddiadiem,edt_udthoigian,edt_udmota,edt_udxutri,edt_udketqua;
    FirebaseFirestore db;
    Suco_model suco_model;
    Calendar calendar;
    SimpleDateFormat dinhdangngay;
    String id_sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_suco);
        db = FirebaseFirestore.getInstance();
        edt_uddiadiem=findViewById(R.id.edt_update_diadiem);
        edt_udthoigian=findViewById(R.id.edt_update_thoigian);
        edt_udmota=findViewById(R.id.edt_update_mota);
        edt_udxutri=findViewById(R.id.edt_update_xutri);
        edt_udketqua=findViewById(R.id.edt_update_ketqua);
        btn_udcapnhat = (Button) findViewById(R.id.btn_update_suco);
        btn_huy = (Button) findViewById(R.id.btn_update_huy);
        calendar = Calendar.getInstance();
        dinhdangngay = new SimpleDateFormat("dd/MM/yyyy");

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                edt_udthoigian.setText(dinhdangngay.format(calendar.getTime()));
            }
        };
        id_sc=getIntent().getStringExtra("id_sc");
        edt_udthoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Update_suco.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        final String id_sc = getIntent().getStringExtra("id_sc");
        DocumentReference document = db.collection("User/pnkhoa/suco").document(id_sc);
        document.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        suco_model=document.toObject(Suco_model.class);
                        edt_uddiadiem.setText(suco_model.getDiadiem());
                        edt_udthoigian.setText(suco_model.getThoigian());
                        edt_udmota.setText(suco_model.getMota());
                        edt_udxutri.setText(suco_model.getXutri());
                        edt_udketqua.setText(suco_model.getKetqua());

                    } else {
                        Log.d("Cập nhật sự cố: ", "Không có sự cố");
                    }
                } else {
                    Log.d("Cập nhật sự cố", "lỗi đọc sự cố ", task.getException());
                }
            }
        });

        btn_udcapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("User/pnkhoa/suco").document(id_sc)
                        .update("diadiem",edt_uddiadiem.getText().toString(),
                                "thoigian",edt_udthoigian.getText().toString(),
                                "mota",edt_udmota.getText().toString(),
                                "xutri",edt_udxutri.getText().toString(),
                                "ketqua",edt_udketqua.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Đã cập nhật thông tin sự cố thành công",Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Đã xảy ra lỗi khi cập nhật: "+e.toString(),Toast.LENGTH_LONG).show();
                            }
                        });
                finish();
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}