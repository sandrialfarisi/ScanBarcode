package id.devcamp.scanbarcode.apihelper;

public class UserData {
    String data;
    String id;
    String nim;
    String jadwal;
    String kode_matkul;
    String nama_matkul;
    String kode_dosen;
    String nama_dosen;
    String ruang;
    String hari;
    String jam_mulai;
    String jam_selesai;


    UserData(String data, String id, String nim, String jadwal, String kode_matkul, String nama_matkul, String kode_dosen, String nama_dosen,
                    String ruang, String hari, String jam_mulai, String jam_selesai){
        this.data = data;
        this.id = id;
        this.nim = nim;
        this.jadwal = jadwal;
        this.kode_matkul = kode_matkul;
        this.nama_matkul = nama_matkul;
        this.kode_dosen = kode_dosen;
        this.nama_dosen = nama_dosen;
        this.ruang = ruang;
        this.hari = hari;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public String getKode_matkul() {
        return kode_matkul;
    }

    public void setKode_matkul(String kode_matkul) {
        this.kode_matkul = kode_matkul;
    }

    public String getNama_matkul() {
        return nama_matkul;
    }

    public void setNama_matkul(String nama_matkul) {
        this.nama_matkul = nama_matkul;
    }

    public String getKode_dosen() {
        return kode_dosen;
    }

    public void setKode_dosen(String kode_dosen) {
        this.kode_dosen = kode_dosen;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }

    public String getRuang() {
        return ruang;
    }

    public void setRuang(String ruang) {
        this.ruang = ruang;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }




}
