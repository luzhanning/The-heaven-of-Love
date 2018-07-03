package com.example.demo.controller;


import com.example.demo.UserMapper;
import com.example.demo.controller.User.WebSecurityConfig;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;


@Controller

public class IndexController {

    @Autowired
    UserMapper usermapper;
    @RequestMapping("/")
    public String home1(){

        return "home";
    }
    @RequestMapping("/newhome")
    public String home(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model){
        if(account == null){
            return "home";
        }
        User user = new User(account,"1","1","1","1");
        model.addAttribute("nickname",usermapper.getNickname(account));
        model.addAttribute("id",account);
        return "newhome";
    }

    @RequestMapping("/home")
    public String home2(){

        return "home";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/home";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/leyuan")
    public String leyuan(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model){

        model.addAttribute("id",account);
        model.addAttribute("nickname",usermapper.getNickname(account));
        model.addAttribute("sex", usermapper.getSex(account));
        model.addAttribute("sex1",usermapper.getSex1(account));


        return "leyuan";
    }


  


}
