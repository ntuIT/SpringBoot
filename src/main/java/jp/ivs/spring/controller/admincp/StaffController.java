package jp.ivs.spring.controller.admincp;

import jp.ivs.spring.mapper.DepartMapper;
import jp.ivs.spring.mapper.EmployeeMapper;
import jp.ivs.spring.model.admin.Department;
import jp.ivs.spring.model.admin.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admincp")
public class StaffController
{
    @Autowired
    EmployeeMapper empMapper;
    @Autowired
    DepartMapper deptMapper;

    @RequestMapping(value = {"/dropStaff"} , method = RequestMethod.GET)
    public String dropForm(ModelMap model, @RequestParam("staffNo") int staffNo)
    {
        model.addAttribute("staffNo", staffNo);
        return "admin/dropStaff";
    }
    @RequestMapping(value = {"/dropStaff"} , method = RequestMethod.DELETE)
    public String dropStaff(ModelMap model
    , @RequestParam("staffNo") int staffNo
    , @RequestParam("userPass") String adminPass
    , RedirectAttributes redirect)
    {
        try {
            empMapper.drop1StaffByNo(staffNo);
        } catch (Exception ex) {}

        redirect.addFlashAttribute("successMessage", "Success !");
        return "redirect:/admincp/staff";
    }
    @RequestMapping(value = {"/editStaff"})
    public String editForm(ModelMap model)
    {
        return "admin/staffDetail";
    }

    @RequestMapping(value = {"/allStaff"})
    public String allStaffsPage(ModelMap model)
    {
        List<Employee> listStaffs = empMapper.getAllStaffs();
        List<Department> listDepts = deptMapper.getAllDept();
        model.addAttribute("listStaffs", listStaffs);
        model.addAttribute("listDepts", listDepts);
        return "admin/staffs";
    }

    @RequestMapping(value = {"/staff"} )
    public String staffSearch(ModelMap model
    , @RequestParam("empName") String empName
    , @RequestParam("deptNo") int deptId  )
    {
        List<Employee> listStaffs;
        if (deptId>=0)
        {
            if (!empName.equals(""))
                listStaffs = empMapper.searchStaffsList(deptId, empName);
            else listStaffs = empMapper.getStaffsListForDept(deptId);
        }
        else {
            listStaffs = empMapper.getAllStaffs();
        }
        List<Department> listDepts = deptMapper.getAllDept();
        model.addAttribute("listStaffs", listStaffs);
        model.addAttribute("listDepts", listDepts);
        return "admin/staffs";
    }

    @RequestMapping(value = {"/addStaff"} /*, method = RequestMethod.POST*/)
    public String newStaffForm(ModelMap model) // đi tới form tạo mới
    {
        List<Department> departments = deptMapper.getAllDept();
        model.addAttribute("deptList", departments);
        return "admin/staffDetail";
    }
    @RequestMapping(value = {"/staffDetail"} , method = RequestMethod.GET)
    public String editStaffForm(ModelMap model , @RequestParam("edit") boolean mode , @RequestParam("staffNo") int staffNo
        ) // đi tới form sửa
    {
        newStaffForm(model);
        model.addAttribute("mode", mode); //mode hiện tại là sừa thông tin
        model.addAttribute("staffNo", staffNo); //truyền mã nhân viên vào map
        return "admin/staffDetail";
    }
    @RequestMapping(value = {"/staffSuccess"} , method = RequestMethod.POST)
    public String success(ModelMap model
         , RedirectAttributes redirect)
    {
        redirect.addFlashAttribute("successMessage", "Success !");
        return "redirect:/admincp/staff";
    }
}
