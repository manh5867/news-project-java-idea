package quanlytintuc.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public class TrangChuAdmin {
    @RequestMapping("/admin")
    public String admin(Model model)
    {


        return "TrangChuAdmin";
    }
    @RequestMapping("/demoAdmin")
    public String demoAdmin(Model model)
    {


        return "admin/collapsed-sidebar";
    }

}
