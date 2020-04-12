package spring.model;

import spring.mapper.CommonMapper;
import spring.mapper.DepartMapper;
import spring.mapper.EmployeeMapper;
import spring.mapper.RecordMapper;
import spring.model.admin.Department;
import spring.model.admin.Employee;
import spring.model.admin.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.sql.*;
import java.util.Map;

public class RecordHelper {
    @Autowired
    CommonMapper commonMapper;

    //các query dùng với JDBC
    public static final String _COUNT_ALL_FELI_ = " select count(*) as 'total' from recorddetails r left join staffs s on s.Id = r.StaffNo where not(r.Type is true) and s.Id = ? ";

    public static final String _COUNT_ALL_CRITI_ = " select count(*) as 'total' from recorddetails r left join staffs s on s.Id = r.StaffNo where r.Type is true and s.Id = ? ";

    public static void initModelMap(ModelMap modelMap, DepartMapper departMapper)
    {
        Map deptList = Department.castList2Map(departMapper.getAllDept());
        modelMap.addAttribute("departments", deptList);
    }
    public static void groupAllRecords(ModelMap modelMap, RecordMapper recordMapper, DepartMapper departMapper)
    {
        Map deptList = Department.castList2Map(departMapper.getAllDept());
        Map recordList = RecordDetail.castList2Map(recordMapper.getAllRecords());
        modelMap.addAttribute("departments", deptList);
        modelMap.addAttribute("listRecords", recordList);
    }
    public static void groupByRecordMap(ModelMap modelMap)
    {
        Integer staffNo = (Integer) modelMap.get("pr_StaffNo");
        RecordMapper recordMapper = (RecordMapper) modelMap.get("pr_recordMapper");
        Map recordList = RecordDetail.castList2Map( recordMapper.getRecordsByStaff(staffNo));
        modelMap.addAttribute("listRecords", recordList);
        EmployeeMapper employeeMapper = (EmployeeMapper) modelMap.get("pr_empMapper");
        modelMap.addAttribute("empDetail",  employeeMapper.get1StaffByNo(staffNo));

        try {
            Connection connection = CommonDbHelper.getMySQLConnection();
            PreparedStatement statement = connection.prepareStatement(_COUNT_ALL_FELI_, staffNo);
            ResultSet result1 = statement.executeQuery(); result1.next();
            int totalFeli = result1.getInt("total");
            modelMap.addAttribute("lauCount",totalFeli);
            result1.close(); statement.close();
            statement = connection.prepareStatement(_COUNT_ALL_CRITI_, staffNo);
            result1 = statement.executeQuery(); result1.next();
            int totalCriti = result1.getInt("total");
            modelMap.addAttribute("critiCount", totalCriti);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        modelMap.remove("pr_StaffNo"); modelMap.remove("pr_recordMapper"); modelMap.remove("pr_empMapper");
    }
    public static void prepareToNewRecord(ModelMap modelMap)
    {
        Integer staffNo = (Integer) modelMap.get("pr_staffNo");
        DepartMapper departMapper = (DepartMapper) modelMap.get("pr_deptMapper");
        EmployeeMapper empMapper = (EmployeeMapper) modelMap.get("pr_empMapper");
        Employee employee = empMapper.get1StaffByNo(staffNo);
        modelMap.addAttribute("empDetail", employee);
        Department department = departMapper.getDeptByNo(employee.getDepartId());
        modelMap.addAttribute("deptDetail", department);
        // bỏ các pack thừa
        modelMap.remove("pr_staffNo"); modelMap.remove("pr_deptMapper"); modelMap.remove("pr_empMapper");
    }
    public static void prepareToModifyRecord(ModelMap modelMap)
    {
        Record record = (Record) modelMap.get("recordLine");
        DepartMapper departMapper = (DepartMapper) modelMap.get("pr_deptMapper");
        EmployeeMapper empMapper = (EmployeeMapper) modelMap.get("pr_empMapper");
        Employee employee = empMapper.get1StaffByNo(record.getStaffId());
        modelMap.addAttribute("empDetail", employee);
        Department department = departMapper.getDeptByNo(employee.getDepartId());
        modelMap.addAttribute("deptDetail", department);
        //bỏ các pack thừa
        modelMap.remove("pr_deptMapper"); modelMap.remove("pr_empMapper");
    }
}
