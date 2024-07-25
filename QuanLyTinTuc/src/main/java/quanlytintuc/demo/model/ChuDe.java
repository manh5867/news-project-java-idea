package quanlytintuc.demo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Entity
public class ChuDe {
    @NotNull(message = "Bạn cần phải nhập mã chủ đề")
    @Length(min = 1, max = 10, message = "Bạn cần nhập mã chủ đề trong khoảng 1-10 kí tự")
    @Id
    @Column(name = "MaChuDe", unique = true, nullable = false, length = 10)
    private String maChuDe;
    @NotNull(message = "Bạn cần phải nhập tên chủ đề")
    @Column(name = "TenChuDe", nullable = false, length = 100)

    private String tenChuDe;
    @Column(name = "MoTa", length = 100)
    private String moTa;

    public String getMaChuDe() {
        return maChuDe;
    }

    public void setMaChuDe(String maChuDe) {
        this.maChuDe = maChuDe;
    }

    public String getTenChuDe() {
        return tenChuDe;
    }

    public void setTenChuDe(String tenChuDe) {
        this.tenChuDe = tenChuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuongBaiViet() {
        return soLuongBaiViet;
    }

    public void setSoLuongBaiViet(int soLuongBaiViet) {
        this.soLuongBaiViet = soLuongBaiViet;
    }

    @Column(name = "SoLuongBaiViet")
    private int soLuongBaiViet;
}
