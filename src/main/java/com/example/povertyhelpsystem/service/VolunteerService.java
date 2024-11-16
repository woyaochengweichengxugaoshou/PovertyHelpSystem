package com.example.povertyhelpsystem.service;

import com.example.povertyhelpsystem.mapper.VolunteerMapper;
import com.example.povertyhelpsystem.pojo.PageBean;
import com.example.povertyhelpsystem.pojo.Volunteer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerMapper volunteerMapper;

    public PageBean page(Integer page, Integer pagesize, String name, Integer age, Integer gender, String volunteerId, LocalDate begin, LocalDate end) {
        Integer count = volunteerMapper.count(name, age, gender, volunteerId, begin, end);
        Integer start = (page - 1) * pagesize;
        List<Volunteer> volunteerList = volunteerMapper.page(name, age, gender, volunteerId, begin, end, start, pagesize);
        return new PageBean(count, volunteerList);
    }

    public void addVolunteer(Volunteer volunteer) {
        volunteer.setComeDate(LocalDate.now());
        volunteer.setPovertyNumber(0);
        volunteerMapper.insertVolunteer(volunteer);
    }

    public void updateVolunteer(Volunteer volunteer) {
        volunteerMapper.updatePoverty_volunteerId_by_updateVolunteer(volunteer);
        volunteerMapper.updateVolunteer(volunteer);
    }

    public void deleteVolunteer(String volunteerId) {
        List<String> povertyIdList = volunteerMapper.select_povertyId_by_volunteerId(volunteerId);
        if(povertyIdList!=null){
            for(String povertyId:povertyIdList){
                volunteerMapper.updatePovertyVolunteerId_ToNull_by_deleteVolunteer(povertyId);
            }
        }
        volunteerMapper.deleteVolunteer(volunteerId);
    }
}