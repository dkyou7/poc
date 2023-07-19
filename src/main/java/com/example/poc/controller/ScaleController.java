package com.example.poc.controller;

import com.example.poc.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("scale")
@RequiredArgsConstructor
public class ScaleController {

    private final MemberService memberService;

    @PostMapping("update")
    public void scaleUp(Principal principal, @RequestParam("scale") String scale){
        memberService.process_updateScale(principal.getName(),scale);
    }

    @PostMapping("load")
    public Map<String, String> scaleLoad(Principal principal){
        Map<String, String> map = new HashMap<>();
        if(principal!=null && StringUtils.hasText(principal.getName())){
            map.put("scale",  memberService.findScaleByEmail(principal.getName()));
        }else{
            map.put("scale",  "1");
        }
        return map;
    }
    @GetMapping("load")
    public String scaleLoad2(Principal principal){
        return "1";
    }
}
