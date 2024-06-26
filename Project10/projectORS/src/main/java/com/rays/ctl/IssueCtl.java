package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.IssueDTO;

import com.rays.form.IssueForm;
import com.rays.service.IssueServiceInt;

@RestController
@RequestMapping(value = "Issue")
public class IssueCtl extends BaseCtl<IssueForm, IssueDTO, IssueServiceInt> {
    
    @Autowired
    IssueServiceInt issueServiceInt;

    @GetMapping("/preload")
    public ORSResponse preload() {
        System.out.println("Inside preload for IssueCtl");
        ORSResponse res = new ORSResponse(true);
        IssueDTO dto = new IssueDTO();
        List<DropdownList> list = issueServiceInt.search(dto, userContext);
        res.addResult("IssueList", list);
        return res;
    }
}
