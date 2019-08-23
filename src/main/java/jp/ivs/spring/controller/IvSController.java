package jp.ivs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IvSController
{
    @GetMapping("/ivs/login")
    public String login(ModelMap model)
    {
        return "welcome";
    }
}
