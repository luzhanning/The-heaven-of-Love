package com.example.demo.controller.User;

import com.example.demo.UserMapper;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService Userservice;
    @Autowired
    UserMapper usermapper;



    @RequestMapping(value = "/delete/{id}")
    public List<User> deleteUsers(@PathVariable String id)
    {
       usermapper.delete(id);
       return usermapper.findAll();
    }
    @RequestMapping("/login")
        public String login(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
            String a = request.getParameter("username");
            String b = request.getParameter("password");
            if(Userservice.Login(a,b)){
                session.setAttribute(WebSecurityConfig.SESSION_KEY, a);
                model.addAttribute("id",a);
                String url = "/newhome";
                response.sendRedirect(url);
                return "登陆成功";
            }
            else{
                model.addAttribute("id",null);
                String url = "/home";
                response.sendRedirect(url);
                return "登录失败";
            }
    }
    @RequestMapping("/register")
    public String register(HttpServletRequest request,HttpSession session,Model model,HttpServletResponse response) throws IOException {

        String a = request.getParameter("username");
        String b = request.getParameter("password");
        String c = request.getParameter("password2");
        String f = request.getParameter("nickname");
        String d = request.getParameter("sex");
        String e = request.getParameter("sex2");
        if(a==null || b==null || c==null || d==null || e==null || f==null ){
            return "内容不足无法注册";
        }
        if(a.equals("")|| b.equals("" )|| c.equals("") || d.equals("") || e.equals("") || f.equals("")){
            return "内容不足无法注册";
        }
        if(Userservice.Register(a,b,c,f,d,e))
        {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, a);
            model.addAttribute("id",a);
            String url = "/newhome";
            response.sendRedirect(url);
            return "注册成功";
        }
        else{
            return "两次密码不一样";
        }

    }
    @RequestMapping("/all")
    public List<User>getAll(){
        return usermapper.findAll();
    }
    @RequestMapping("/session")
    public String session(HttpServletRequest request){
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute(WebSecurityConfig.SESSION_KEY));

    }

}
