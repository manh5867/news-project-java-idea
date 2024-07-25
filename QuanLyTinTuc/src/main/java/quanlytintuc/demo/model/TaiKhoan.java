package quanlytintuc.demo.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Set;

@Entity
public class TaiKhoan {

    @Length(min = 1, max = 10)
    @Id
    @Column(name = "TaiKhoan", unique = true, nullable = false, length = 10)
    private String taiKhoan;
    @NotNull(message = "Bạn cần phải nhập mật khẩu ")
    @Column(name = "MatKhau", nullable = false, length = 100)
    private String matKhau;



    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQuyenTruyCap() {
        return quyenTruyCap;
    }

    public void setQuyenTruyCap(String quyenTruyCap) {
        this.quyenTruyCap = quyenTruyCap;
    }



    @Column(name = "HoTen", nullable = false, length = 100)
    private String hoTen;
    @NotNull(message = "Bạn cần chọn quyền truy cập ")
    @NotBlank(message = "Bạn cần chọn quyền truy cập ")
    @Column(name = "quyenTruyCap", length = 100)
    private String quyenTruyCap;

}
