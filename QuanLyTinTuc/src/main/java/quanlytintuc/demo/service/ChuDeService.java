package quanlytintuc.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanlytintuc.demo.model.ChuDe;
import quanlytintuc.demo.model.ChuDeRepository;

import java.util.List;

@Service
public class ChuDeService {
    @Autowired
    ChuDeRepository chuDeRepository;

    //Ham lay danh sach
    public List<ChuDe> layDanhSach()
    {
        return (List<ChuDe>)chuDeRepository.findAll();
    }

    //Ham lay chi tiet
    public  ChuDe layChiTiet(String maCD)
    {
        ChuDe objCD = null;

        try {
            objCD = (ChuDe) chuDeRepository.findById(maCD).get();
        }
        catch (Exception ex)
        {
            objCD = null;
        }
        return objCD;
    }

    public boolean themMoi(ChuDe obj) {

        ChuDe objChuDe = chuDeRepository.save(obj);
        if(objChuDe != null)
        {
            return true;
        }
        return false;
    }


    public boolean capNhat(ChuDe obj) {

        ChuDe objChuDe = chuDeRepository.findById(obj.getMaChuDe()).get();

        if(objChuDe != null)
        {
            objChuDe.setTenChuDe(obj.getTenChuDe());
            objChuDe.setMoTa(obj.getMoTa());

            chuDeRepository.save(objChuDe);

            return true;
        }
        return false;
    }


    public boolean xoa(String id) {

        ChuDe objChuDe = layChiTiet(id);

        if(objChuDe != null)
        {
            chuDeRepository.delete(objChuDe);

            return true;
        }

        return false;
    }

}
