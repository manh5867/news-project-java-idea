package quanlytintuc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import quanlytintuc.demo.model.ChuDe;
import quanlytintuc.demo.model.TinTuc;
import quanlytintuc.demo.service.TinTucService;
import quanlytintuc.demo.service.ChuDeService;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
@Controller
public class TinTucController {
    @Autowired
    TinTucService tinTucService;
    @Autowired
    ChuDeService chuDeService;



    @RequestMapping("/admin/TinTuc")
    public String hienThiDanhSach(Model model)
    {
        List<TinTuc> lstTinTuc = tinTucService.layDanhSach();

        List<ChuDe> lstChuDe = chuDeService.layDanhSach();
        model.addAttribute("lstChuDe", lstChuDe);
        model.addAttribute("lstTinTuc", lstTinTuc);

        return "TinTuc";
    }

    @RequestMapping(value="/admin/TinTuc-add")
    public String hienThiThemMoiSach(Model model)
    {
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        model.addAttribute("lstChuDe", lstChuDe);
        model.addAttribute("TinTuc", new TinTuc());
        return "TinTucAdd";
    }

    @RequestMapping(value="/admin/TinTuc-add", method = RequestMethod.POST)
    public String thucHienThemSach(@ModelAttribute("TinTuc") @Valid TinTuc objTinTuc, BindingResult result, @RequestParam("fUpload") MultipartFile fUpload, Model model)
    {
        //Nếu có lỗi xảy ra
        if(result.hasErrors())
        {
            model.addAttribute("message", "Bạn cần phải nhập đầy đủ thông tin");
        }
        else {

            boolean isInsert = true;
            String fileName = "";

            //Nếu đã sách đã có thì là TH sửa
            TinTuc objCDOld = tinTucService.layChiTiet(objTinTuc.getMaTinTuc());
            if (objCDOld != null) {
                isInsert = false;
                if(objCDOld != null)
                {
                    isInsert = false;
                    fileName = objCDOld.getAnhDaiDien();
                }
            }
            if(!fUpload.getOriginalFilename().isEmpty()) {
                try {
                    fileName = fUpload.getOriginalFilename();

                    String pathStr = "C:\\Users\\My HP\\IdeaProjects\\QuanLyTinTuc\\src\\main\\resources\\static\\img";
                    //Sử dụng khi publish appache
                    //pathStr = context.getRealPath("/images");
                    File file = new File(pathStr, fileName);
                    fUpload.transferTo(file);


                } catch (Exception e) {
                    e.printStackTrace();
                    model.addAttribute("message", "Upload file không thành công");
                }
            }

            objTinTuc.setAnhDaiDien(fileName);

            objTinTuc.setNgayDang(new Date());
            //Thực hiện thêm mới sách vào db
            boolean ketQua = false;
            if (isInsert) {

                ketQua = tinTucService.themMoi(objTinTuc);
            } else {
                ketQua = tinTucService.capNhat(objTinTuc);
            }

            if (ketQua) {
                return "redirect:/admin/TinTuc";
            }

        }

        return "TinTucAdd";
    }


    /**
     * Hiển thị chi tiết sách
     * @param ma
     * @param model
     * @return
     */
    @RequestMapping(value="/admin/TinTuc-sua/{ma}")
    public String suaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        System.out.println("Mã chủ đề là: " + ma);

        TinTuc objTinTuc = tinTucService.layChiTiet(ma);

        model.addAttribute("TinTuc", objTinTuc);
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        model.addAttribute("lstChuDe", lstChuDe);

        return "TinTucAdd";
    }

    @RequestMapping(value="/admin/TinTuc-xoa/{ma}")
    public String xoaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        if(ma!= null && !ma.isEmpty())
        {
            //Xóa thông tin chủ đề
            boolean ketQua = tinTucService.xoa(ma);

            if(ketQua)
            {
                return "redirect:/admin/TinTuc";
            }
        }

        return "TinTuc";
    }
    @RequestMapping(value="/admin/tim-TinTuc", method = RequestMethod.POST)
    public String timKiemTinTuc(@ModelAttribute("tuKhoa") String tuKhoa,@ModelAttribute("maCD") String maCD, Model model)
    {



//Lấy danh sách sách
        List<TinTuc> lstTinTuc = tinTucService.timKiemTinTuc(tuKhoa, maCD);

        model.addAttribute("lstTinTuc", lstTinTuc);
        model.addAttribute("tuKhoa", tuKhoa);
        model.addAttribute("maCD", maCD);
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();
        model.addAttribute("lstChuDe", lstChuDe);

        return "TinTuc";
    }
    @RequestMapping(value="/admin/TinTuc-chitiet/{ma}")
    public String xemThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        System.out.println("Mã chủ đề là: " + ma);

        TinTuc objTinTuc = tinTucService.layChiTiet(ma);

        model.addAttribute("TinTuc", objTinTuc);
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        model.addAttribute("lstChuDe", lstChuDe);

        return "TinTucXem";
    }
}
