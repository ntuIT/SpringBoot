package jp.ivs.spring.controller.admincp;

import jp.ivs.spring.mapper.DepartMapper;
import jp.ivs.spring.mapper.RecordMapper;
import jp.ivs.spring.model.admin.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.ivs.spring.model.RecordDetail;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecordsController
{
    @Autowired
    RecordMapper recordMapper;
    @Autowired
    DepartMapper departMapper;

    @RequestMapping(value = "/admincp/allRecords")
    public String recordsForm(ModelMap model)
    {
        Map deptList = Department.castList2Map(departMapper.getAllDept());
        Map recordList = RecordDetail.castList2Map(recordMapper.getAllRecords());
        model.addAttribute("departments", deptList);
        model.addAttribute("listRecords", recordList);
        return "admin/recordsForm";
    }

    @RequestMapping(value = "/admincp/records")
    public String filterRecordsByDept(ModelMap modelMap
    , @RequestParam("mode") int mode
    , @RequestParam("deptNo") int deptNo
    ) {
        return recordsForm(modelMap);
    }
    @RequestMapping(value = "/admincp/records_bystaff"
        , params = "")
    public String showRecordByStaff(ModelMap model)
    {
        return "admin/recordsForm";
    }

//    @RequestMapping(value = "/admincp/records_byDepart")
//    @RequestMapping(value = "/admincp/records_by")
}
