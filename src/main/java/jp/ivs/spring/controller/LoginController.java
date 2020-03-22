package jp.ivs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController
{
    @RequestMapping(value = {"/index","/"})
    public String index()
    {
        return "welcome";
    }
    @RequestMapping("/loginPage")
    public String loginPage()
    {
        return "login";
    }
    @RequestMapping({"/login"})
    public String login( @RequestParam("email") String email
         , @RequestParam("password") String pass
         , ModelMap model)
    {
        return "mainLayout";
    }
}