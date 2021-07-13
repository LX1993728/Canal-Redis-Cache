package com.zoo.ninestar.controllers;

import com.alibaba.fastjson.JSONObject;
import com.zoo.ninestar.domains.entity.Gift;
import com.zoo.ninestar.domains.entity.NSPK;
import com.zoo.ninestar.domains.entity.NSPKSkill;
import com.zoo.ninestar.domains.vo.notify.EnumATest;
import com.zoo.ninestar.domains.vo.notify.NSNotifyType;
import com.zoo.ninestar.domains.vo.notify.NSNotifyVO;
import com.zoo.ninestar.services.NSService;
import com.zoo.ninestar.utils.MapObjUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping("/test")
@RestController
public class TestController {
    @Autowired
    private NSService nsService;

    @GetMapping("/skills")
    public Object getSkills(){
        final List<NSPKSkill> loadSkills = nsService.getLoadSkills(false);
        return loadSkills;
    }

    @GetMapping("/getSkillById")
    public Object getSkillById(@RequestParam(name = "skillId", defaultValue = "1")Long skillId,
                               @RequestParam(name = "isLoad", defaultValue = "false") Boolean isLoad){
        final NSPKSkill skill = nsService.getLoadSkill(skillId, isLoad);
        return skill;
    }
    @GetMapping("/testObjMap")
    public Object testObjMap(){
        final NSPK nspk = new NSPK();
        nspk.setId(1L);
        nspk.setStatus(2);
        nspk.setStartTime(new Date());

        final Map<String, Object> objectMap = MapObjUtils.obj2OBJMap(nspk);
        return objectMap;
    }


    @GetMapping("/getSkillStatus")
    public Object getSkillById(@RequestParam(name = "pkId", defaultValue = "1")Long pkId,
                               @RequestParam(name = "masterId", defaultValue = "100") Long masterId){
        final List<NSPKSkill> skillAndStatuses = nsService.getSkillAndStatuses(pkId, masterId);
        return skillAndStatuses;
    }

    @GetMapping("/getPKById")
    public Object getPkId(@RequestParam(name = "pkId", defaultValue = "1")Long pkId){
        return nsService.getLoadNSPK(pkId, false);
    }

    @GetMapping("/testEnum")
    public Object testEnum(){
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("enumATest", EnumATest.YELLOW);
        final NSNotifyVO<JSONObject> nsNotifyVO = new NSNotifyVO<>(220, NSNotifyType.BATTLE_REPORT, "战报通知",jsonObject);
        return nsNotifyVO;
    }


    @GetMapping("/getSkillGiftBySkillId")
    public Object getSkillGiftBySkillId(@RequestParam(name = "skillId", defaultValue = "1")Long skillId,
                                @RequestParam(name = "isLoad", defaultValue = "false")Boolean isLoad
                                ){
        final Gift gift = nsService.getLoadSkillGiftBySkillId(skillId, true, isLoad);
        return gift;
    }

    @GetMapping("/getSkillGiftByGiftId")
    public Object getSkillGiftByGiftId(@RequestParam(name = "giftId", defaultValue = "1")Long giftId,
                                @RequestParam(name = "isLoad", defaultValue = "false")Boolean isLoad
                                ){
        final Gift gift = nsService.getLoadSkillGiftByGiftId(giftId, true, isLoad);
        return gift;
    }

}
