package quanlytintuc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlytintuc.demo.model.TaiKhoan;
import quanlytintuc.demo.model.TaiKhoanRepository;

import java.util.List;

@Service
public class TaiKhoanService {
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    //Ham lay danh sach
    public List<TaiKhoan> layDanhSach()
    {
        return (List<TaiKhoan>)taiKhoanRepository.findAll();
    }

    //Ham lay chi tiet
    public  TaiKhoan layChiTiet(String maCD)
    {
        TaiKhoan objCD = null;

        try {
            objCD = (TaiKhoan) taiKhoanRepository.findById(maCD).get();
        }
        catch (Exception ex)
        {
            objCD = null;
        }
        return objCD;
    }

    public boolean themMoi(TaiKhoan obj) {

        TaiKhoan objTaiKhoan = taiKhoanRepository.save(obj);
        if(objTaiKhoan != null)
        {
            return true;
        }
        return false;
    }


    public boolean capNhat(TaiKhoan obj) {

        TaiKhoan objTaiKhoan = taiKhoanRepository.findById(obj.getTaiKhoan()).get();

        if(objTaiKhoan != null)
        {

            objTaiKhoan.setMatKhau(obj.getMatKhau());
            objTaiKhoan.setHoTen(obj.getHoTen());
            objTaiKhoan.setQuyenTruyCap(obj.getQuyenTruyCap());

            taiKhoanRepository.save(objTaiKhoan);

            return true;
        }
        return false;
    }


    public boolean xoa(String id) {

        TaiKhoan objTaiKhoan = layChiTiet(id);

        if(objTaiKhoan != null)
        {
            taiKhoanRepository.delete(objTaiKhoan);

            return true;
        }

        return false;
    }
}
