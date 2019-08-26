package jp.ivs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController
{
    @RequestMapping(value = {"/index","/"})
    String index()
    {
        return "welcome";
    }
    @RequestMapping("/login")
    public String loginPage()
    {
        return "login";
    }
}