package com.bvcm.baocaosuco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.bvcm.baocaosuco.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class add_suco extends AppCompatActivity {
    Button btn_capnhat, btn_thoat;
    EditText edt_diadiem,edt_thoigian,edt_mota,edt_xutri,edt_ketqua;
    FirebaseFirestore db;
    Calendar calendar;
    SimpleDateFormat dinhdangngay;
    String id_sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_suco);
        db = FirebaseFirestore.getInstance();
        edt_diadiem=findViewById(R.id.edt_diadiem);
        edt_thoigian=findViewById(R.id.edt_thoigian);
        edt_mota=findViewById(R.id.edt_mota);
        edt_xutri=findViewById(R.id.edt_xutri);
        edt_ketqua=findViewById(R.id.edt_ketqua);
        btn_capnhat = (Button) findViewById(R.id.btn_capnhat);
        btn_thoat = (Button) findViewById(R.id.btn_thoat);
        calendar = Calendar.getInstance();
        dinhdangngay = new SimpleDateFormat("dd/MM/yyyy");

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                edt_thoigian.setText(dinhdangngay.format(calendar.getTime()));
            }
        };
        id_sc=getIntent().getStringExtra("id");
        edt_thoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(add_suco.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        CtrlButton();
    }

    private void CtrlButton() {
        //Cập nhật
        btn_capnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> sc = new HashMap<>();
                sc.put("id",id_sc);
                sc.put("diadiem",edt_diadiem.getText().toString());
                sc.put("thoigian",edt_thoigian.getText().toString());
                sc.put("mota", edt_mota.getText().toString());
                sc.put("xutri", edt_xutri.getText().toString());
                sc.put("ketqua",edt_ketqua.getText().toString());
                db = FirebaseFirestore.getInstance();
                db.collection("/User/pnkhoa/suco").document().set(sc)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Đã thêm mới thành công",Toast.LENGTH_LONG).show();
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Đã xảy ra lỗi khi thêm mới",Toast.LENGTH_LONG).show();
                                // e.printStackTrace();
                            }
                        });
            }
        });
//Thoát
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(add_suco.this, android.R.style.Theme_DeviceDefault_Dialog);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn dừng cập nhật sự cố?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

}