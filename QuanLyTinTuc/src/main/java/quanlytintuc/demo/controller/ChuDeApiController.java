package quanlytintuc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quanlytintuc.demo.model.ChuDe;
import quanlytintuc.demo.model.Message;
import quanlytintuc.demo.service.ChuDeService;


import java.util.List;

@RestController
@RequestMapping("api")

public class ChuDeApiController {

    @Autowired
    ChuDeService chuDeService;

   @GetMapping("chude")
   public ResponseEntity<List<ChuDe>> layDanhSach()
   {
        List<ChuDe> lstChuDe = chuDeService.layDanhSach();

        return  new ResponseEntity<List<ChuDe>>(lstChuDe, HttpStatus.OK);
   }

   @GetMapping("chude/{id}")
   public  ResponseEntity<?> layChiTietTheoMa(@PathVariable("id") String id)
   {
       ChuDe objCD = chuDeService.layChiTiet(id);

       if(objCD == null)
       {
           Message msg = new Message("Không tìm thấy chủ đề có mã: " + id);
           return new ResponseEntity<Message>(msg, HttpStatus.NOT_FOUND);
       }
       else
       {
           return new ResponseEntity<ChuDe>(objCD, HttpStatus.OK);
       }
   }

   @PostMapping("chude/add")
   public ResponseEntity<?> themChuDe(@RequestBody ChuDe objCD)
   {
        boolean kq = chuDeService.themMoi(objCD);

        if(kq)
        {
            return new ResponseEntity<ChuDe>(objCD, HttpStatus.OK);
        }

        return  new ResponseEntity<Message>(new Message("Không thêm được chủ đề"), HttpStatus.NOT_FOUND);
   }

    @PutMapping("chude/{id}")
    public ResponseEntity<?> capNhatChuDe(@PathVariable("id") String id, @RequestBody ChuDe objCD)
    {
        ChuDe objCDOld = chuDeService.layChiTiet(id);

        if(objCDOld != null)
        {
            objCDOld.setTenChuDe(objCD.getTenChuDe());
            chuDeService.capNhat(objCDOld);

            return new ResponseEntity<ChuDe>(objCD, HttpStatus.OK);
        }
        boolean kq = chuDeService.themMoi(objCD);

        return  new ResponseEntity<Message>(new Message("Không thêm được chủ đề, mã: " + id), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("chude/{id}")
    public ResponseEntity<?> xoaChuDe(@PathVariable("id") String id)
    {
        ChuDe objCD = chuDeService.layChiTiet(id);

        if(objCD != null) {
          boolean kq =  chuDeService.xoa(id);
            if(kq)
            {
                return new ResponseEntity<ChuDe>(objCD, HttpStatus.OK);
            }
        }

        return  new ResponseEntity<Message>(new Message("Không thêm được chủ đề, mã: " + id), HttpStatus.NOT_FOUND);
    }
}
