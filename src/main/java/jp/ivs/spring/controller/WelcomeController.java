package jp.ivs.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    private String message = "Hello World";

    @GetMapping("/")
    public String welcome(ModelMap model)
    {

        model.addAttribute("message", message);
        return "welcome";
    }

}