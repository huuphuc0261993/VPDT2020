package com.example.demo.controller.user;

import com.example.demo.model.ChiTiet;
import com.example.demo.model.NhanVien;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.security.Principal;
import java.time.LocalDate;

@Controller
//@RequestMapping("/user/nhanvien")
@RequestMapping("")
public class NhanVienUserController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping("/changPass")
    public ModelAndView getView(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        NhanVien nv = nhanVienService.findByUsername(username);

        ModelAndView modelAndView = new ModelAndView("user/DoiMatKhau");
        modelAndView.addObject("avatar",nv.getAvatar());
        return modelAndView;
    }

    @RequestMapping(value = "/changPass", method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<?> uploadFile(HttpServletRequest request, @RequestParam("customFile") MultipartFile uploadfile, @RequestParam String passOld,
                                        @RequestParam String passCurrent){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        NhanVien nv = nhanVienService.findByUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(passOld, nv.getPassword())) {
            return new ResponseEntity<>("Wrong old password", HttpStatus.NOT_FOUND);
        }
        nv.setPassword(encoder.encode(passCurrent));

        try {
            File uploadRootDir = new File("src/main/resources/static/upload/");
            // Tạo thư mục gốc upload nếu nó không tồn tại.
            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }

            System.out.println(uploadfile.getContentType());
                // Tên file gốc tại Client.
                String name = uploadfile.getOriginalFilename();
//            System.out.println("Client File Name = " + name);
                if (name != null && name.length() > 0) {
                    if (uploadfile.getContentType().equals("image/jpeg") ||
                            uploadfile.getContentType().equals("image/jpg") ||
                            uploadfile.getContentType().equals("image/png")){
                        try {
                            // Tạo file tại Server.
                            File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                            // Luồng ghi dữ liệu vào file trên Server.
                            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                            stream.write(uploadfile.getBytes());
                            stream.close();
                            nv.setAvatar(uploadRootDir.getName() + File.separator + name);
                        } catch (Exception e) {
                            System.out.println("Error Write file: " + name);
                        }
                    }
                }
            nhanVienService.save(nv);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
