package jp.ivs.spring.controller.admincp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admincp")
public class AdminController
{
    @RequestMapping("/home")
    public String homeAdmin(ModelMap model
            , @RequestParam("email") String mail
            , @RequestParam("password") String pass)
    {
        return "login";
    }
    @RequestMapping(value = "/home_admin")
    public String returnToAdmin()
    {
        return "admin/home";
    }

    @RequestMapping(value = {"/loginPage"})
    public String gotoLogin(ModelMap model)
    {
        return "admin/login";
    }
    @RequestMapping(value = {"/login"})
    public String login(ModelMap model
    , @RequestParam("email") String mail
    , @RequestParam("password") String pass)
    {
        return "homeAdmin";
    }
}
