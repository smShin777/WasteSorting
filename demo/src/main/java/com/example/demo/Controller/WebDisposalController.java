package com.example.demo.Controller;

import com.example.demo.entity.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/disposal")
public class WebDisposalController {

    public static Map<String, User> userDB = new HashMap<>();
    public static Map<String, DisposalItem> itemDB = new HashMap<>();
    public static Map<String, DisposalRule> ruleDB = new HashMap<>();
    public static Map<String, FineStandard> fineDB = new HashMap<>();

    static
    {

        userDB.put("admin", new User("admin", "1234", true));

        itemDB.put("피자박스", new DisposalItem("ITEM1", "피자박스", "종이류"));
        ruleDB.put("ITEM1", new DisposalRule("RULE1", "ITEM1", "남은 음식물을 비우고 영수증 테이프를 분리하여 배출한다."));
        fineDB.put("종이류", new FineStandard("FINE1", "종이류", 100000));

        itemDB.put("페트병", new DisposalItem("ITEM2", "페트병", "플라스틱"));
        ruleDB.put("ITEM2", new DisposalRule("RULE2", "ITEM2", "내용물을 비우고 라벨을 제거한 후 압축하여 배출한다."));
        fineDB.put("플라스틱", new FineStandard("FINE2", "플라스틱", 50000));

        itemDB.put("우유팩", new DisposalItem("ITEM3", "우유팩", "종이팩"));
        ruleDB.put("ITEM3", new DisposalRule("RULE3", "ITEM3", "내용물을 비우고 물로 헹군 후 펼쳐서 배출한다."));
        fineDB.put("종이팩", new FineStandard("FINE3", "종이팩", 30000));
    }

    @PostMapping("/register")
    public String register(@RequestParam String id, @RequestParam String password)
    {
        if (id == null || id.trim().isEmpty() ||
                password == null || password.trim().isEmpty())
        {
            return "INVALID_INPUT";
        }

        if (userDB.containsKey(id))
        {
            return "ALREADY EXISTS";
        }

        userDB.put(id, new User(id, password, false));
        return "SUCCESS";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String password)
    {
        if (id == null || id.trim().isEmpty() ||
                password == null || password.trim().isEmpty())
        {
            return "FAIL";
        }

        User user = userDB.get(id);
        if (user != null && user.getPassword().equals(password))
        {
            return user.isAdmin() ? "ADMIN" : "USER";
        }

        return "FAIL";
    }

    @GetMapping("/search")
    public Map<String, Object> searchItem(@RequestParam String keyword)
    {
        Map<String, Object> response = new HashMap<>();

        if (keyword == null || keyword.trim().isEmpty())
        {
            response.put("status", "INVALID_INPUT");
            return response;
        }

        DisposalItem item = itemDB.get(keyword);

        if (item == null)
        {
            for (Map.Entry<String, DisposalItem> entry : itemDB.entrySet())
            {
                if (entry.getKey().contains(keyword) || keyword.contains(entry.getKey()))
                {
                    item = entry.getValue();
                    break;
                }
            }
        }

        if (item != null)
        {
            DisposalRule rule = ruleDB.get(item.getItemId());
            FineStandard fine = fineDB.get(item.getCategory());

            response.put("status", "SUCCESS");
            response.put("itemName", item.getItemName());
            response.put("category", item.getCategory());
            response.put("guideline", rule != null ? rule.getGuideline() : "지침 정보가 없습니다.");
            response.put("fineAmount", fine != null ? fine.getAmount() : 0);
        }
        else
        {
            response.put("status", "NOT_FOUND");
        }

        return response;
    }
}
