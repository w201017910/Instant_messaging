package com.example.instant_messaging.control;

import com.example.instant_messaging.bean.Message;
import com.example.instant_messaging.bean.User;
import com.example.instant_messaging.bean.friend;
import com.example.instant_messaging.bean.text;
import com.example.instant_messaging.dao.daoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
public class webControl {
    @Autowired
    daoUser dao;
    @RequestMapping("/chat.html")
    public ModelAndView loginPage(HttpServletRequest request,Model model) {
        ModelAndView mv = new ModelAndView();
        HttpSession session=request.getSession();
        int id=(int) session.getAttribute("id");
        ArrayList<friend> f=dao.selectFriend(id);
        User[] users=new User[f.size()];
        for (int i=0;i<users.length;i++){
            users[i]=dao.selectById(f.get(i).getUserID2());
        }
        Message[]lastMessage=new Message[users.length];
        for (int i=0;i<lastMessage.length;i++){
            ArrayList<Message>m=dao.selectMessage(id,users[i].getUserID());
            if (m.size()==0){
                lastMessage[i]=new Message();
                lastMessage[i].message="";
                lastMessage[i].date=null;
                continue;}
            lastMessage[i]=m.get(m.size()-1);
        }
        User user=dao.selectById(id);

        mv.addObject("lastMessage",lastMessage);
        mv.addObject("users",users);
        mv.addObject("user",user);
        mv.setViewName("chat");//  /emplates/hello.html
        return mv;
    }
    @RequestMapping("/addFriend")
    @ResponseBody
    public text addFriend(String username,HttpServletRequest request){
        HttpSession session=request.getSession();
        text tt=new text();
        User friendUser=dao.selectByName(username);
        int friendId=friendUser.getUserID();
        int userId= (int) session.getAttribute("id");
        if (dao.friendIsEmpty(userId,friendId)!=null){
            tt.setJudge(false);
            tt.setMessage("该好友已存在");
            return tt;
        }
        dao.insertFiend(userId,friendId);
        dao.insertFiend(friendId,userId);
        tt.user=friendUser;
        tt.setJudge(true);
        tt.setMessage("好友添加成功");
        return tt;
    }
    @RequestMapping("/deleteFriend")
    @ResponseBody
    public text deleteFriend(String username,HttpServletRequest request){
        HttpSession session=request.getSession();
        text tt=new text();
        int friendId=dao.selectByName(username).getUserID();
        int userId= (int) session.getAttribute("id");

        dao.deleteFriend(userId,friendId);
        dao.deleteFriend(friendId,userId);

        tt.setJudge(true);
        tt.setMessage("好友删除成功");
        return tt;
    }
    @RequestMapping("/userMessage")
    @ResponseBody
    public ArrayList<Message> userMessage(String username,HttpServletRequest request){
        HttpSession session=request.getSession();
        int friendId=dao.selectByName(username).getUserID();
        int userId= (int) session.getAttribute("id");
        ArrayList<Message> messages=dao.selectMessage(userId,friendId);
        return messages;
    }
    @RequestMapping("/queryUserId")
    @ResponseBody
    public Integer queryUserId(HttpServletRequest request){
        HttpSession session=request.getSession();
        int userId= (int) session.getAttribute("id");
        return userId;
    }
    @RequestMapping("/queryUsers")
    @ResponseBody
    public User[] queryUsers(HttpServletRequest request){
        HttpSession session=request.getSession();
        ArrayList<friend> f=dao.selectFriend((int) session.getAttribute("id"));
        User[] users=new User[f.size()];
        for (int i=0;i<users.length;i++){
            users[i]=dao.selectById(f.get(i).getUserID2());
        }
        return users;
    }
    @RequestMapping("/logout")
    @ResponseBody
    public boolean logout(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
        return true;
    }
}
