package quanlytintuc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlytintuc.demo.model.TinTuc;
import quanlytintuc.demo.model.TinTucRepository;


import java.util.List;

@Service
public class TinTucService {
    @Autowired
    TinTucRepository tinTucRepository;

    //Ham lay danh sach
    public List<TinTuc> layDanhSach()
    {
        return (List<TinTuc>)tinTucRepository.findAll();
    }

    //Ham lay chi tiet
    public  TinTuc layChiTiet(String maCD)
    {
        TinTuc objCD = null;

        try {
            objCD = (TinTuc) tinTucRepository.findById(maCD).get();
        }
        catch (Exception ex)
        {
            objCD = null;
        }
        return objCD;
    }

    public boolean themMoi(TinTuc obj) {

        TinTuc objTinTuc = tinTucRepository.save(obj);
        if(objTinTuc != null)
        {
            return true;
        }
        return false;
    }


    public boolean capNhat(TinTuc obj) {

        TinTuc objTinTuc = tinTucRepository.findById(obj.getMaTinTuc()).get();

        if(objTinTuc != null)
        {
            objTinTuc.setTieuDe(obj.getTieuDe());
            objTinTuc.setNoiDung(obj.getNoiDung());
            objTinTuc.setMaChuDe(obj.getMaChuDe());
            objTinTuc.setAnhDaiDien(obj.getAnhDaiDien());

            objTinTuc.setTacGia(obj.getTacGia());





            tinTucRepository.save(objTinTuc);

            return true;
        }
        return false;
    }


    public boolean xoa(String id) {

        TinTuc objTinTuc = layChiTiet(id);

        if(objTinTuc != null)
        {
            tinTucRepository.delete(objTinTuc);

            return true;
        }

        return false;
    }
    public List<TinTuc> timKiemTinTuc(String tuKhoa, String maCD) {
        return tinTucRepository.timKiemTinTuc(tuKhoa,maCD);

    }
}
