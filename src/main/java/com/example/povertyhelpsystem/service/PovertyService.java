package com.example.povertyhelpsystem.service;

import com.example.povertyhelpsystem.mapper.PovertyMapper;
import com.example.povertyhelpsystem.pojo.PageBean;
import com.example.povertyhelpsystem.pojo.Poverty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PovertyService {

    @Autowired
    private PovertyMapper povertyMapper;

    public PageBean page(Integer page, Integer pagesize, String name, Integer age, Integer gender, String povertyId, LocalDate begin, LocalDate end) {
        Integer count = povertyMapper.count(name, age, gender, povertyId, begin, end);
        Integer start = (page - 1) * pagesize;
        List<Poverty> povertyList = povertyMapper.page(name, age, gender, povertyId, begin, end, start, pagesize);
        return new PageBean(count, povertyList);
    }

    public void addPoverty(Poverty poverty) {
        poverty.setComeDate(LocalDate.now());
        poverty.setVolunteerId(null);
        povertyMapper.insertPoverty(poverty);
    }

    public void updatePoverty(Poverty poverty) {
        povertyMapper.updateVolunteer_PovertyNumber_by_updatePoverty(poverty);
        povertyMapper.updatePoverty(poverty);

    }

    public void deletePoverty(String povertyId) {
        String volunteerId = povertyMapper.select_volunteerId_by_povertyId(povertyId);
        if(volunteerId!=null){
            povertyMapper.deleteVolunteer_PovertyNumber_by_deletePoverty(volunteerId);
        }
        povertyMapper.deletePoverty(povertyId);
    }
}
