package com.example.povertyhelpsystem.service;

import com.example.povertyhelpsystem.mapper.HelpToNeedMapper;
import com.example.povertyhelpsystem.pojo.PTV;
import com.example.povertyhelpsystem.pojo.PageBean;
import com.example.povertyhelpsystem.pojo.Poverty;
import com.example.povertyhelpsystem.pojo.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelpToNeedService {

    @Autowired
    private HelpToNeedMapper help_to_need_mapper;

    public PageBean page(Integer page, Integer pagesize, String povertyName, String povertyId) {
        Integer count = help_to_need_mapper.count(povertyName, povertyId);
        Integer start = (page - 1) * pagesize;
        List<PTV> PTVList = help_to_need_mapper.page(povertyName, povertyId, start, pagesize);
        System.out.println(PTVList);
        return new PageBean(count, PTVList);
    }

    public void assign_volunteer_to_poverty() {
        List<Poverty> povertyList = help_to_need_mapper.select_name_id_need();

        // 分配志愿者
        for(Poverty p:povertyList){
            List<Volunteer> volunteerList = help_to_need_mapper.select_name_id_help();

            Volunteer bestMatch = null;
            int maxMatchCount = 0;
            boolean flag = false;

            // 遍历志愿者以找到最佳匹配
            for (Volunteer v : volunteerList) {
                int matchCount = countMatchingChars(p.getNeed(), v.getHelp());
                if(matchCount==p.getNeed().length()){
                    //找到
                    help_to_need_mapper.update_poverty_volunteerId(p.getPovertyId(), v.getVolunteerId());
                    help_to_need_mapper.update_volunteer_povertyNumber(v.getVolunteerId());
                    flag = true;
                    break;
                }
                if (matchCount > maxMatchCount) {
                    maxMatchCount = matchCount;
                    bestMatch = v;
                }
            }
            if(!flag){
                if (bestMatch != null) {
                    help_to_need_mapper.update_poverty_volunteerId(p.getPovertyId(), bestMatch.getVolunteerId());
                }
                if (bestMatch != null) {
                    help_to_need_mapper.update_volunteer_povertyNumber(bestMatch.getVolunteerId());
                }
            }
        }

    }

    // 辅助方法：计算两个字符串中相同字符的个数
    private int countMatchingChars(String need, String help) {
        int count = 0;
        for (char c : help.toCharArray()) {
            if (need.indexOf(c) >= 0) {
                count++;
            }
        }
        return count;
    }

    public void clear(){
        help_to_need_mapper.update_clear_poverty_volunteerId();
    }
}
