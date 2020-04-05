package jp.ivs.spring.model;

import jp.ivs.spring.mapper.CommonMapper;
import jp.ivs.spring.mapper.DepartMapper;
import jp.ivs.spring.mapper.EmployeeMapper;
import jp.ivs.spring.mapper.RecordMapper;
import jp.ivs.spring.model.admin.Department;
import jp.ivs.spring.model.admin.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import java.util.Map;

public class RecordHelper {
    @Autowired
    CommonMapper commonMapper;

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
}
