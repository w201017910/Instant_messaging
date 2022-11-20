package com.example.instant_messaging.control;

import com.example.instant_messaging.bean.User;
import com.example.instant_messaging.bean.text;
import com.example.instant_messaging.dao.daoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class loginControl {
    @Autowired
    daoUser dao;
    @RequestMapping("/login.html")
    public String registerPage(Model model) {
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public text login(String username, String password, HttpServletRequest request, Model model) {
        User user=dao.selectByName(username);
        text tt=new text();
        if (user==null||!(user.getPassword().equals(password))){
            tt.setJudge(false);
            tt.setMessage("用户名或密码错误");
            return tt;
        }
        HttpSession session = request.getSession();
        System.out.println(user.getUserID());
        session.setAttribute("id",user.getUserID());
        session.setAttribute("username",username);
        session.setAttribute("password",password);
        tt.setJudge(true);
        tt.setMessage("登录成功");
        return tt;
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public text register(String username, String password, HttpServletRequest request, Model model) {
        User user=dao.selectByName(username);
        text tt=new text();
        if (user!=null){
            tt.setJudge(false);
            tt.setMessage("用户名已存在");
            return tt;
        }
        dao.insertUser(username,password,"/img/user.svg");
        HttpSession session = request.getSession();
        session.setAttribute("id",dao.selectByName(username).getUserID());
        session.setAttribute("username",username);
        session.setAttribute("password",password);
        tt.setJudge(true);
        tt.setMessage("注册成功");
        return tt;
    }
}
