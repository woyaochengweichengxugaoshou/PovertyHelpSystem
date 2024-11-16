package com.example.povertyhelpsystem.controller;


import com.example.povertyhelpsystem.pojo.PageBean;
import com.example.povertyhelpsystem.pojo.Poverty;
import com.example.povertyhelpsystem.service.PovertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
public class PovertyController {

    @Autowired
    private PovertyService povertyService;

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @GetMapping("/poverty")
    public PageBean ListPoverty(@RequestParam(defaultValue = "1") Integer page,
                                @RequestParam(defaultValue = "10") Integer pagesize,
                                String name, Integer age, Integer gender, String povertyId,
                                @RequestParam(required = false) String begin,
                                @RequestParam(required = false) String end){

        log.info("接收到的日期：{}",begin);

        LocalDate beginDate = begin != null ? LocalDate.parse(begin, DateTimeFormatter.ISO_DATE) : null;
        LocalDate endDate = end != null ? LocalDate.parse(end, DateTimeFormatter.ISO_DATE) : null;

        log.info("分页查询所有贫困信息, 页码: {}, 每页显示条数: {}, 参数：{}，{}，{}，{}, {}, {}",
                page, pagesize, name, age, gender, povertyId, begin, end);

        return povertyService.page(page, pagesize,name, age, gender, povertyId, beginDate, endDate);
    }

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @PostMapping("/poverty")
    public void addPoverty(@RequestBody Poverty poverty) {
        log.info("新增贫困人员：{}", poverty);
        povertyService.addPoverty(poverty);
    }

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @PutMapping("/poverty")
    public void updatePoverty(@RequestBody Poverty poverty) {
        log.info("修改贫困人员：{}", poverty);
        povertyService.updatePoverty(poverty);
    }

    @CrossOrigin(origins = "http://localhost:7000")
    @DeleteMapping("/poverty/{povertyId}")
    public void deletePoverty(@PathVariable String povertyId) {
        log.info("删除贫困人员：{}", povertyId);
        povertyService.deletePoverty(povertyId);

    }
}