package com.example.instant_messaging.control;

import com.example.instant_messaging.dao.daoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Controller
@RequestMapping("/person")
public class uploadControl {
    @Autowired
    daoUser dao;
    @RequestMapping("/info.html")
    public String hello(Model model) {
        return "info";
    }
    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(MultipartFile file, String username, String password, HttpServletRequest request){
        HttpSession session=request.getSession();
        int id=(int) session.getAttribute("id");

        if(file.isEmpty()){
            return "false";
        }
        Random random=new Random();

        String fileName = random.nextInt()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        System.out.println(fileName);
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\upload" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            dao.updateUser(id,username,password,"/upload/"+fileName);
            session.setAttribute("username",username);
            session.setAttribute("password",password);
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }

}
