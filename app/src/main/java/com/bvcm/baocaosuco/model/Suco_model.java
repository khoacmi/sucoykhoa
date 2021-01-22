package com.bvcm.baocaosuco.model;

public class Suco_model {
    String diadiem;
    String thoigian;
    String mota;
    String xutri;
    String ketqua;
    String id;
    String path;

    public Suco_model() {
    }

    public Suco_model(String diadiem, String thoigian, String mota, String xutri, String ketqua, String path) {
        this.diadiem = diadiem;
        this.thoigian = thoigian;
        this.mota = mota;
        this.xutri = xutri;
        this.ketqua = ketqua;
        this.path = path;
     }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getXutri() {
        return xutri;
    }

    public void setXutri(String xutri) {
        this.xutri = xutri;
    }

    public String getKetqua() {
        return ketqua;
    }

    public void setKetqua(String ketqua) {
        this.ketqua = ketqua;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
