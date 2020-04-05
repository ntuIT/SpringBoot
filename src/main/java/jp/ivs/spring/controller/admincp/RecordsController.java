package jp.ivs.spring.controller.admincp;

import jp.ivs.spring.mapper.DepartMapper;
import jp.ivs.spring.mapper.EmployeeMapper;
import jp.ivs.spring.mapper.RecordMapper;
import jp.ivs.spring.model.RecordHelper;
import jp.ivs.spring.model.admin.Employee;
import jp.ivs.spring.model.admin.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.ivs.spring.model.RecordDetail;

@Controller
public class RecordsController
{
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    DepartMapper departMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @RequestMapping(value = "/admincp/allRecords")
    public String allRecords(ModelMap modelMap)
    {
        RecordHelper.groupAllRecords(modelMap, recordMapper, departMapper);
        return "admin/recordsForm";
    }

    @RequestMapping(value = "/admincp/records")
    public String filterRecordsByDept(ModelMap modelMap
    , @RequestParam("deptNo") int deptNo
    ) {
        RecordHelper.initModelMap(modelMap, departMapper);
        if(deptNo<0)
        {
            return allRecords(modelMap);
        }
        else {
            Map recordList = RecordDetail.castList2Map(recordMapper.getRecordsByDept(deptNo));
            modelMap.addAttribute("listRecords", recordList);
        }
        return "admin/recordsForm";
    }
    @RequestMapping(value = {"/admincp/records"} , method = RequestMethod.POST)
    public String initRecordsByStaff(ModelMap modelMap
    , @RequestParam("empNo") int staffNo
    )   {
        RecordHelper.initModelMap(modelMap, departMapper);
        modelMap.addAttribute("pr_StaffNo", staffNo); //lưu hành nội bộ: sử dụng key prefix "pr_"
        modelMap.addAttribute("pr_empMapper", employeeMapper);
        modelMap.addAttribute("pr_recordMapper", recordMapper);
        RecordHelper.groupByRecordMap(modelMap);
        return "admin/staffRecordsForm";
    }

    @RequestMapping(value = {"/admincp/newRecord"} )
    public String addForm(ModelMap modelMap
    , @RequestParam("mode") int mode
    )   {
        return "foward: /admincp/success";
    }
    @RequestMapping(value = {"/admincp/addRecord"}, method = RequestMethod.POST)
    public String addRecord(ModelMap modelMap
//    , @RequestParam("mode") int mode
    , @ModelAttribute Employee staff
    )   {
        modelMap.addAttribute("pr_staffNo", staff.getId());
        modelMap.addAttribute("pr_deptMapper", departMapper);
        modelMap.addAttribute("pr_empMapper", employeeMapper);
        RecordHelper.prepareToNewRecord(modelMap);
        modelMap.addAttribute("mode", 1); //đặt mode new = 1 tức là Thêm
        return "admin/recordDetail";
    }
    @RequestMapping(value = {"/admincp/editRecord"}, method = RequestMethod.POST)
    public String editRecord(ModelMap modelMap
    , @RequestParam("record") Record record
    )   {
        modelMap.addAttribute("pr_staffNo", record.getStaffId());
        modelMap.addAttribute("pr_deptMapper", departMapper);
        modelMap.addAttribute("pr_empMapper", employeeMapper);
        RecordHelper.prepareToNewRecord(modelMap);
        modelMap.addAttribute("mode", 0); //đặt mode new = 0 tức là Sửa
        return "";
    }
    @RequestMapping(value = {"/admincp/recordSuccess"}, method = RequestMethod.POST)
    public String updateSuccess(ModelMap modelMap
    , @ModelAttribute Record record
    , @RequestParam("mode") int mode
    )   {
        if (mode ==-1) {}
        if (mode == 0) {}
        if (mode == 1) {
            record.getDate();
//            recordMapper.addRecord(record);
        }

        return allRecords(modelMap);
    }
}
