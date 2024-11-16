package com.example.povertyhelpsystem.controller;


import com.example.povertyhelpsystem.pojo.PageBean;
import com.example.povertyhelpsystem.pojo.Poverty;
import com.example.povertyhelpsystem.pojo.Volunteer;
import com.example.povertyhelpsystem.service.VolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Slf4j
@RestController
public class VolunteerController {

    @Autowired
    private VolunteerService volunteerService;

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @GetMapping("/volunteer")
    public PageBean ListVolunteer(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pagesize,
                                String name, Integer age, Integer gender, String volunteerId,
                                @RequestParam(required = false) String begin,
                                @RequestParam(required = false) String end){

        LocalDate beginDate = begin != null ? LocalDate.parse(begin, DateTimeFormatter.ISO_DATE) : null;
        LocalDate endDate = end != null ? LocalDate.parse(end, DateTimeFormatter.ISO_DATE) : null;

        log.info("分页查询所有志愿者信息, 页码: {}, 每页显示条数: {}, 参数：{}，{}，{}，{}, {}, {}",
                page, pagesize, name, age, gender, volunteerId, begin, end);

        return volunteerService.page(page, pagesize,name, age, gender, volunteerId, beginDate, endDate);
    }

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @PostMapping("/volunteer")
    public void addVolunteer(@RequestBody  Volunteer volunteer) {
        log.info("新增志愿者：{}", volunteer);
        volunteerService.addVolunteer(volunteer);

    }

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @PutMapping("/volunteer")
    public void updateVolunteer(@RequestBody Volunteer volunteer) {
        log.info("修改志愿者：{}", volunteer);
        volunteerService.updateVolunteer(volunteer);
    }

    @CrossOrigin(origins = "http://localhost:7000")
    @DeleteMapping("/volunteer/{volunteerId}")
    public void deleteVolunteer(@PathVariable String volunteerId){
        log.info("删除志愿者：{}", volunteerId);
        volunteerService.deleteVolunteer(volunteerId);
    }
}