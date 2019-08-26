package jp.ivs.spring.controller.admincp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import jp.ivs.spring.model.RecordDetail;

@Controller
public class RecordsController
{
    @RequestMapping(value = "/admincp/allrecords")
    public String recordsForm(ModelMap model)
    {
        List<RecordDetail> list = new ArrayList<>();
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
