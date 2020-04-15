package spring.controller.admincp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.mapper.DepartMapper;
import spring.model.CommonDbHelper;
import spring.model.admin.Department;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Controller
public class DepartController {

    @Autowired
    DepartMapper departMapper;
    @RequestMapping(value = {"/admincp/viewGlobalDepart"})
    public String departGeneralStatistic(ModelMap modelMap)
    {
        modelMap.addAttribute("pr_deptMapper", departMapper);
        DepartHelper.prepareListDept(modelMap);
        return "admin/departStatistic";
    }

    public static class DepartHelper    {

        public static void prepareListDept(ModelMap modelMap)
        {
            DepartMapper deptMapper =(DepartMapper) modelMap.get("pr_deptMapper");
            List deptList = deptMapper.getAllDept();
            try {
                Connection connection = CommonDbHelper.getMySQLConnection();
                connection.prepareStatement("select");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            modelMap.addAttribute("departments", deptList);
        }

    }
    //end class
}
