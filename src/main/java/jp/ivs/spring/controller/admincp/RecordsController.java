package jp.ivs.spring.controller.admincp;

import jp.ivs.spring.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jp.ivs.spring.model.RecordDetail;

@Controller
public class RecordsController
{
    @Autowired
    RecordMapper recordMapper;

    @RequestMapping(value = "/admincp/allRecords")
    public String recordsForm(ModelMap model)
    {
        List<RecordDetail> list = new ArrayList<>();
        Map map = recordMapper.getAllRecords();
        model.addAttribute("listRecords", list);
        return "admin/recordsForm";
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
