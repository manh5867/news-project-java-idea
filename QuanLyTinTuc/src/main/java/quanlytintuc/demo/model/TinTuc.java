package quanlytintuc.demo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
public class TinTuc {
    @NotNull(message = "Bạn cần phải nhập mã tin tức ")
    @Length(min = 1, max = 10, message = "Bạn cần nhập mã tin tức  trong khoảng 1-10 kí tự")
    @Id
    @Column(name = "MaTinTuc", unique = true, nullable = false, length = 10)
    private String maTinTuc;
    @NotNull(message = "Bạn cần phải nhập tiêu đề")
    @Column(name = "TieuDe", nullable = false, length = 100)
    private String tieuDe;
    @NotNull(message = "Bạn cần phải nhập nội dung")
    @Column(name = "NoiDung", nullable = false, length = 100)
    private String noiDung;
    @Column(name = "NgayDang", nullable = false, length = 100)
    private Date ngayDang;
    @NotNull(message = "Bạn cần phải nhập tác giả")
    @Column(name = "TacGia", nullable = false, length = 100)
    private String tacGia;

    public String getMaTinTuc() {
        return maTinTuc;
    }

    public void setMaTinTuc(String maTinTuc) {
        this.maTinTuc = maTinTuc;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(Date ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getAnhDaiDien() {
        return anhDaiDien;
    }

    public void setAnhDaiDien(String anhDaiDien) {
        this.anhDaiDien = anhDaiDien;
    }





    public String getSoBinhLuan() {
        return soBinhLuan;
    }

    public void setSoBinhLuan(String soBinhLuan) {
        this.soBinhLuan = soBinhLuan;
    }

    @Column(name = "AnhDaiDien", nullable = false, length = 100)
    private String anhDaiDien;
    @NotNull(message = "Bạn cần phải chọn chủ đề")
    @Column(name = "maChuDe", nullable = false, length = 100)
    private String maChuDe;

    public String getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }


    @Column(name = "SoBinhLuan", nullable = false, length = 100)
    private String soBinhLuan;

}
