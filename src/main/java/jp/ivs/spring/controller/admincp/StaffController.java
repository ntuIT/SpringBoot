package jp.ivs.spring.controller.admincp;

import jp.ivs.spring.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admincp")
public class StaffController
{
    @Autowired
    EmployeeMapper empMapper;
    @RequestMapping(value = {"/dropStaff"})
    public String dropForm(ModelMap model)
    {
        return "/admin/staffs";
    }
}
