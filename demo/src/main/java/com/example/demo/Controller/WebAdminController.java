package com.example.demo.Controller;

import com.example.demo.entity.DisposalItem;
import com.example.demo.entity.DisposalRule;
import com.example.demo.entity.FineStandard;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class WebAdminController {

    @PostMapping("/update")
    public String updateRule(
            @RequestParam String category,
            @RequestParam String guideline,
            @RequestParam int fineAmount)
    {
        boolean updated = false;

        DisposalItem targetItem = null;
        for (DisposalItem item : WebDisposalController.itemDB.values()) {
            if (item.getCategory().equals(category)) {
                targetItem = item;
                break;
            }
        }

        if (targetItem != null) {
            DisposalRule rule = WebDisposalController.ruleDB.get(targetItem.getItemId());
            if (rule != null) {
                rule.setGuideline(guideline);
                updated = true;
            }
        }

        // 3. 과태료 업데이트
        FineStandard fine = WebDisposalController.fineDB.get(category);
        if (fine != null) {
            fine.setAmount(fineAmount);
            updated = true;
        }

        if (updated) {
            return "SUCCESS";
        } else {
            return "NOT_FOUND";
        }
    }

    @PostMapping("/auth")
    public String adminAuth(
            @RequestParam String id,
            @RequestParam String password)
    {
        var user = WebDisposalController.userDB.get(id);
        if (user != null && user.getPassword().equals(password) && user.isAdmin()) {
            return "SUCCESS";
        }
        return "FAIL";
    }
}