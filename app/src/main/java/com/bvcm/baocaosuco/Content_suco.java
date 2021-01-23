package com.bvcm.baocaosuco;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.bvcm.baocaosuco.adapter.Suco_adapter;
import com.bvcm.baocaosuco.model.Suco_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Content_suco extends AppCompatActivity {
    ListView lvSuco;
    ArrayList<Suco_model> arraySuco= new ArrayList<>();
    Suco_adapter adapter;
    Button btn_add_sc;
    FirebaseFirestore db;
    Button btn_menuUpdate;
    Button btn_menuDelete;
    Button btn_menuExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_content_suco);
        btn_add_sc = (Button) findViewById(R.id.btn_add_sc);
        Load_Suco();
        adapter=new Suco_adapter(this,R.layout.item_suco,arraySuco);
        lvSuco.setAdapter(adapter);
        //Them
        btn_add_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Content_suco.this, "Thêm sự cố", Toast.LENGTH_SHORT).show();
                String id;
                if(arraySuco.size()==0){
                    id="1";
                }else {
                    id=String.valueOf(Integer.valueOf(arraySuco.get(arraySuco.size()-1).getId())+1);
                }
                Intent addsc = new Intent(Content_suco.this, add_suco.class);
                addsc.putExtra("id",id);
                startActivity(addsc);
            }
        });
        lvSuco.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long l) {
                final Integer id =position;
                final Dialog dialog = new Dialog(Content_suco.this);
                dialog.setContentView(R.layout.menu_suco);
                dialog.show();
                btn_menuUpdate = dialog.findViewById(R.id.btn_menusua);
                btn_menuDelete = dialog.findViewById(R.id.btn_menuxoa);
                btn_menuExit = dialog.findViewById(R.id.btn_menudong);
                //Lenh
                btn_menuExit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                btn_menuDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.collection("/User/pnkhoa/suco").document(arraySuco.get(id).getPath())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Content_suco.this,"Đã xoá sự cố thành công",Toast.LENGTH_LONG).show();
                                        Load_Suco();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Content_suco.this,"Lỗi khi xoá sự cố: " +e.toString(),Toast.LENGTH_LONG).show();
                                    }
                                });
                        dialog.cancel();
                    }
                });
                btn_menuUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent update_suco = new Intent(Content_suco.this, Update_suco.class);
                        update_suco.putExtra("id_sc",arraySuco.get(id).getPath());
                        startActivity(update_suco);
                        dialog.cancel();
                    }
                });
                return false;
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        Load_Suco();
    }
    public void Load_Suco(){
        lvSuco = (ListView) findViewById(R.id.listviewSuco);
        arraySuco.clear();
        db.collection("User/pnkhoa/suco")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Suco_model suco_model = document.toObject(Suco_model.class);
                                suco_model.setPath(document.getId().toString());
                                arraySuco.add(suco_model);
                            }
                            adapter.notifyDataSetChanged();
                        }else{
                            Log.e("","Loi",task.getException());

                        }
                    }
                });
    }

}