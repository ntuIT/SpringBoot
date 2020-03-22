package jp.ivs.spring.controller.admincp;

import jp.ivs.spring.mapper.DepartMapper;
import jp.ivs.spring.mapper.EmployeeMapper;
import jp.ivs.spring.model.admin.Department;
import jp.ivs.spring.model.admin.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jp.ivs.spring.mapper.UserMapper;
import jp.ivs.spring.model.admin.UserAdmin;

@Controller
@RequestMapping("/admincp")
public class AdminController
{
    @Autowired
    UserMapper userMapper;
    @Autowired
    DepartMapper departMapper;
    @Autowired
    EmployeeMapper empMapper;

    @RequestMapping(value = "/")
    public String goToAdmin(ModelMap model)
    {
        List<Department> list = departMapper.getAllDept();
        model.addAttribute("listDeparts", list);
        return "admin/welcome";
    }

    @RequestMapping(value = {"/loginPage"})
    public String goToLogin(ModelMap model)
    {
        return "admin/login";
    }

    @RequestMapping(value = {"/login"}, method=RequestMethod.POST)
    public String login(ModelMap model
    , @RequestParam("email") String user
    , @RequestParam("password") String pass)
    {
        List<UserAdmin> list = userMapper.getAllUser();
        for (UserAdmin admin : list)
        {
            if (admin.getUsername().equals(user) && admin.getPassword().equals(pass))
            {
                model.addAttribute("adminName", admin.getFullName());
                return goToAdmin(model);
            }
        }
        return goToLogin(model);
    }

    @RequestMapping(value = {"/frag"})
    public String frag(ModelMap modelMap)
    {
        return "admin/adminLayout";
    }
}
