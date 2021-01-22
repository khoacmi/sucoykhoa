package com.bvcm.baocaosuco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bvcm.baocaosuco.Content_suco;
import com.bvcm.baocaosuco.R;
import com.bvcm.baocaosuco.model.Suco_model;

import java.util.List;

public class Suco_adapter extends BaseAdapter {
    private Content_suco context;
    private int layout;
    private List<Suco_model> listsuco;

    public Suco_adapter(Content_suco context, int layout, List<Suco_model> listsuco) {
        this.context = context;
        this.layout = layout;
        this.listsuco = listsuco;
    }


    @Override
    public int getCount() {
        return listsuco.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtDD,txtTG,txtMT,txtXT,txtKQ;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            // ánh xạ view
            holder=new ViewHolder();

            holder.txtDD =(TextView) convertView.findViewById(R.id.txt_diadiem);
            holder.txtTG=(TextView) convertView.findViewById(R.id.txt_thoigian);
            holder.txtMT=(TextView) convertView.findViewById(R.id.txt_mota);
            holder.txtXT=(TextView) convertView.findViewById(R.id.txt_xutri);
            holder.txtKQ=(TextView) convertView.findViewById(R.id.txt_ketqua);

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        //gán giá trị
        Suco_model suco=listsuco.get(position);
        holder.txtDD.setText(suco.getDiadiem());
        holder.txtTG.setText(suco.getThoigian());
        holder.txtMT.setText(suco.getMota());
        holder.txtXT.setText(suco.getXutri());
        holder.txtKQ.setText(suco.getKetqua());

        return convertView;
    }
}
