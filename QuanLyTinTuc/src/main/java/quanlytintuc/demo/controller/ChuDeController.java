package quanlytintuc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quanlytintuc.demo.model.ChuDe;
import quanlytintuc.demo.service.ChuDeService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ChuDeController {

    @Autowired
    ChuDeService chuDeService;


    @RequestMapping("/admin/chude")
    public String hienThiDanhSach(Model model)
    {
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        model.addAttribute("lstChuDe", lstChuDe);

        return "ChuDe";
    }
    @RequestMapping(value="/admin/chude-add")
    public String hienThiThemMoiSach(Model model)
    {
        model.addAttribute("chuDe", new ChuDe());
        return "ChuDeAdd";
    }


    @RequestMapping(value="/admin/chude-add", method = RequestMethod.POST)
    public String thucHienThemSach(@ModelAttribute("chuDe") @Valid ChuDe objChuDe, BindingResult result, Model model)
    {
        //Nếu có lỗi xảy ra
        if(result.hasErrors())
        {
            model.addAttribute("message", "Bạn cần phải nhập đầy đủ thông tin");
        }
        else {


            boolean isInsert = true;

            //Nếu đã sách đã có thì là TH sửa
            ChuDe objCDOld = chuDeService.layChiTiet(objChuDe.getMaChuDe());
            if (objCDOld != null) {
                isInsert = false;
            }

            //Thực hiện thêm mới sách vào db
            boolean ketQua = false;
            if (isInsert) {

                ketQua = chuDeService.themMoi(objChuDe);
            } else {
                ketQua = chuDeService.capNhat(objChuDe);
            }

            if (ketQua) {
                return "redirect:/admin/chude";
            }
        }

        return "ChuDeAdd";
    }


    /**
     * Hiển thị chi tiết sách
     * @param ma
     * @param model
     * @return
     */
    @RequestMapping(value="/admin/chude-sua/{ma}")
    public String suaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        System.out.println("Mã chủ đề là: " + ma);

        ChuDe objChuDe = chuDeService.layChiTiet(ma);

        model.addAttribute("chuDe", objChuDe);

        return "ChuDeAdd";
    }

    @RequestMapping(value="/admin/chude-xoa/{ma}")
    public String xoaThongTinSach(@PathVariable("ma")String ma, Model model)
    {
        if(ma!= null && !ma.isEmpty())
        {
            //Xóa thông tin chủ đề
            boolean ketQua = chuDeService.xoa(ma);

            if(ketQua)
            {
                return "redirect:/admin/chude";
            }
        }

        return "ChuDe";
    }

}
