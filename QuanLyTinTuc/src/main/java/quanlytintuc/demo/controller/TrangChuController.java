package quanlytintuc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quanlytintuc.demo.model.ChuDe;
import quanlytintuc.demo.model.TinTuc;
import quanlytintuc.demo.service.ChuDeService;
import quanlytintuc.demo.service.TinTucService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller
public class TrangChuController {

    @Autowired
    TinTucService tinTucService;
    @Autowired
    ChuDeService chuDeService;
    @RequestMapping("/main")
    public String trangChu(Model model)
    {
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        model.addAttribute("lstChuDe", lstChuDe);

        List<TinTuc> lstTinTuc = tinTucService.layDanhSach();

        model.addAttribute("lstTinTuc", lstTinTuc);

        int lastpoint = lstTinTuc.size();
        List<TinTuc> firstHalf = lstTinTuc.subList(lastpoint-3, lstTinTuc.size());

        // Tạo danh sách con thứ hai
        List<TinTuc> secondHalf = lstTinTuc.subList(lastpoint-5, lastpoint-3);

        // Đặt danh sách con vào model để sử dụng trong Thymeleaf template
        model.addAttribute("firstHalf", firstHalf);
        model.addAttribute("secondHalf", secondHalf);

        List<TinTuc> lstTinTucRanDom= lstTinTuc;
        // Trộn ngẫu nhiên danh sách tin tức
        Collections.shuffle(lstTinTucRanDom);


        // Chọn 5 tin tức đầu tiên từ danh sách đã trộn
        List<TinTuc> fiveRandomTinTuc = lstTinTucRanDom.subList(0, 5);

        model.addAttribute("fiveRandomTinTuc", fiveRandomTinTuc);
        return "main";
    }
    @RequestMapping("/admin")
    public String admin(Model model)
    {


        return "TrangChuAdmin";
    }

    @RequestMapping("/TrangHienThiTheoChuDe/{maCD}")
    public String timKiemTinTuc(@PathVariable("maCD")String maCD, Model model)
    {



//Lấy danh sách sách
        String tuKhoa="";

        List<TinTuc> lstTinTuc = tinTucService.timKiemTinTuc(tuKhoa, maCD);
        int midpoint = lstTinTuc.size() / 2;

        // Tạo danh sách con thứ nhất
        List<TinTuc> firstHalf = lstTinTuc.subList(midpoint, lstTinTuc.size());

        // Tạo danh sách con thứ hai
        List<TinTuc> secondHalf = lstTinTuc.subList(0, midpoint);

        // Đặt danh sách con vào model để sử dụng trong Thymeleaf template
        model.addAttribute("firstHalf", firstHalf);
        model.addAttribute("secondHalf", secondHalf);


        model.addAttribute("lstTinTuc", lstTinTuc);
        model.addAttribute("maCD", maCD);
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();
        model.addAttribute("lstChuDe", lstChuDe);

        return "TrangHienThiTheoChuDe";
    }
    @RequestMapping("/TrangChiTietTinTuc/{ma}")
    public String chiTietTinTuc(@PathVariable("ma")String ma, Model model)
    {



//Lấy danh sách sách


        TinTuc tinTuc = tinTucService.layChiTiet(ma);


        // Tạo danh sách con thứ nh

        // Đặt danh sách con vào model để sử dụng trong Thymeleaf template



        model.addAttribute("TinTuc", tinTuc);

        List<ChuDe> lstChuDe = chuDeService.layDanhSach();
        model.addAttribute("lstChuDe", lstChuDe);

        return "TrangChiTietTinTuc";
    }
    @RequestMapping("/demo")
    public String demo(Model model)
    {


        return "demo";
    }
    @RequestMapping("/demoAdmin")
    public String demoAdmin(Model model)
    {


        return "admin/collapsed-sidebar";
    }
}
