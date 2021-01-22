package com.bvcm.baocaosuco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edituser, editpass;
    Button btndangnhap, btnthoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        ControlButton();
    }
    private void ControlButton(){
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog);
                builder.setTitle("Thông Báo");
                builder.setMessage("Bạn muốn thoát app?");
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
        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edituser.getText().length() !=0 && editpass.getText().length() !=0){
                    if (edituser.getText().toString().equals("k") && editpass.getText().toString().equals("k")){

                        Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this, Content_suco.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Sai tên hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Nhập tên và mật khẩu để đăng nhập",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void Anhxa(){
        edituser=(EditText)findViewById(R.id.edittextuser);
        editpass=(EditText)findViewById(R.id.edittextpass);
        btndangnhap=(Button)findViewById(R.id.btndangnhap);
        btnthoat=(Button)findViewById(R.id.btnthoat);
    }

}