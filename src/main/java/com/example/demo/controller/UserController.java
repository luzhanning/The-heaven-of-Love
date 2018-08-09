package com.example.demo.controller;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.*;
import com.example.demo.service.UserService;
import com.example.demo.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService Userservice;
    @Autowired
    UserMapper usermapper;


    @RequestMapping(value = "/delete/{id}")
    public List<User> deleteUsers(@PathVariable String id) {
        usermapper.delete(id);
        return usermapper.findAll();
    }

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {
        String a = request.getParameter("username");
        String b = request.getParameter("password");
        if (Userservice.Login(a, b)) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, a);
            model.addAttribute("id", a);
            String url = "/newhome";
            response.sendRedirect(url);
            return "登陆成功";
        } else {
            model.addAttribute("id", null);
            String url = "/home";
            response.sendRedirect(url);
            return "登录失败";
        }
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpSession session, Model model, HttpServletResponse response) throws IOException {

        String a = request.getParameter("username");
        String b = request.getParameter("password");
        String c = request.getParameter("password2");
        String f = request.getParameter("nickname");
        String d = request.getParameter("sex");
        String e = request.getParameter("sex2");
        if (a == null || b == null || c == null || d == null || e == null || f == null) {
            return "内容不足无法注册";
        }
        if (a.equals("") || b.equals("") || c.equals("") || d.equals("") || e.equals("") || f.equals("")) {
            return "内容不足无法注册";
        }
        if (Userservice.Register(a, b, c, f, d, e)) {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, a);
            model.addAttribute("id", a);
            String url = "/newhome";
            response.sendRedirect(url);
            return "注册成功";
        } else {
            return "两次密码不一样";
        }

    }

    @RequestMapping("/all")
    public List<User> getAll() {
        return usermapper.findAll();
    }

    @RequestMapping("/session")
    public String session(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return String.valueOf(session.getAttribute(WebSecurityConfig.SESSION_KEY));

    }

    @GetMapping("/highScore")
    public Integer getScore(Score score) {
        if (score.getNickname() == null || score.getType() == null) {
            return 0;
        }

        return Userservice.getScore(score);
    }

    @GetMapping("/getHit")
    public List<hit> getHit() {

        return Userservice.getHit();
    }

    @PostMapping("/newScore")
    public void newScore(Score score) {
        if(score.getNickname()==null){
            return;
        }
        if (Userservice.getScore(score) == null) {
            Userservice.insertScore(score);
        }
        if (score.getScore() > Userservice.getScore(score)) {
            Userservice.setScore(score);
        }
        Userservice.hitPlusOne(score.getType());
    }

    @GetMapping("/rank")
    public List<Score> getScore(@RequestParam("type") Integer type) {
        if (type == null || type <= 0) {
            return null;
        }

        return Userservice.getRankByType(type);
    }

    @PostMapping("/newCustom")
    public void newScore(Custom custom) {
        if(custom.getNickname()==null){
            return;
        }
            Custom custom1 = Userservice.getCustom(custom);
        if (custom1 == null) {
            Userservice.insertCustom(custom);
        }
        if (custom.getScore() > custom1.getScore()) {
            Userservice.setCustom(custom);
        }
    }
    @GetMapping("/custom")
    public List<Custom> getCustom() {


        return Userservice.getCustomRank();
    }


@GetMapping("/chuangguan")
public Chuangguan getChuang(@RequestParam("nickname") String nickname) {


    if(Userservice.getChuang(nickname)==null){
        Userservice.insertChuang(nickname);
}
    return Userservice.getChuang(nickname);

}
    @PostMapping("/chuang")
    public void newChuang(Chuangguan chuangguan) {
        if(chuangguan.getNickname()==null){
            return;
        }
        Userservice.Chuang(chuangguan);

    }

}
