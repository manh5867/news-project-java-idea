package quanlytintuc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quanlytintuc.demo.model.TaiKhoan;
import quanlytintuc.demo.service.TaiKhoanService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class TaiKhoanController {

    @Autowired
    TaiKhoanService TaiKhoanService;


    @RequestMapping("/admin/TaiKhoan")
    public String hienThiDanhSach(Model model)
    {
        List<TaiKhoan> lstTaiKhoan = TaiKhoanService.layDanhSach();

        model.addAttribute("lstTaiKhoan", lstTaiKhoan);

        return "TaiKhoan";
    }
    @RequestMapping(value="/admin/TaiKhoan-add")
    public String hienThiThemMoiSach(Model model)
    {
        model.addAttribute("TaiKhoan", new TaiKhoan());
        return "TaiKhoanAdd";
    }


    @RequestMapping(value="/admin/TaiKhoan-add", method = RequestMethod.POST)
    public String thucHienThemSach(@ModelAttribute("TaiKhoan") @Valid TaiKhoan objTaiKhoan, BindingResult result, Model model)
    {
        //Nếu có lỗi xảy ra
        if(result.hasErrors())
        {
            model.addAttribute("message", "Bạn cần phải nhập đầy đủ thông tin");
        }
        else {


            boolean isInsert = true;

            //Nếu đã sách đã có thì là TH sửa
            TaiKhoan objCDOld = TaiKhoanService.layChiTiet(objTaiKhoan.getTaiKhoan());
            if (objCDOld != null) {
                isInsert = false;
            }

            //Thực hiện thêm mới sách vào db
            boolean ketQua = false;
            if (isInsert) {

                ketQua = TaiKhoanService.themMoi(objTaiKhoan);
            } else {
                ketQua = TaiKhoanService.capNhat(objTaiKhoan);
            }

            if (ketQua) {
                return "redirect:/admin/TaiKhoan";
            }
        }

        return "TaiKhoanAdd";
    }


    /**
     * Hiển thị chi tiết sách
     * @param ma
     * @param model
     * @return
     */
    @RequestMapping(value="/admin/TaiKhoan-sua/{ma}")
    public String suaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        System.out.println("Mã chủ đề là: " + ma);

        TaiKhoan objTaiKhoan = TaiKhoanService.layChiTiet(ma);

        model.addAttribute("TaiKhoan", objTaiKhoan);

        model.addAttribute("isReadOnly", true);

        return "TaiKhoanAdd";
    }

    @RequestMapping(value="/admin/TaiKhoan-xoa/{ma}")
    public String xoaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        if(ma!= null && !ma.isEmpty())
        {
            //Xóa thông tin chủ đề
            boolean ketQua = TaiKhoanService.xoa(ma);

            if(ketQua)
            {
                return "redirect:/admin/TaiKhoan";
            }
        }

        return "TaiKhoan";
    }

}
