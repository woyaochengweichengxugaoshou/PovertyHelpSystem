package com.example.povertyhelpsystem.controller;


import com.example.povertyhelpsystem.pojo.PageBean;
import com.example.povertyhelpsystem.service.HelpToNeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class HelpToNeedController {

    @Autowired
    private HelpToNeedService help_to_need_service;

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @GetMapping("/help_to_need")
    public PageBean List_help_to_need(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pagesize,
                                      String povertyName, String povertyId){


        log.info("分页查询所有帮扶信息, 页码: {}, 每页显示条数: {}, 参数：{}， {}",
                page, pagesize, povertyName, povertyId);

        return help_to_need_service.page(page, pagesize,povertyName, povertyId);
    }

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @GetMapping("/help_to_need/assign")
    public PageBean assign_help_to_need(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "10") Integer pagesize,
                                      String povertyName, String povertyId){


        log.info("开始分配志愿者并分页查询所有帮扶信息, 页码: {}, 每页显示条数: {}, 参数：{}， {}",
                page, pagesize, povertyName, povertyId);

        help_to_need_service.assign_volunteer_to_poverty();

        return help_to_need_service.page(page, pagesize,povertyName, povertyId);
    }

    @CrossOrigin(origins = "http://localhost:7000")  // 允许该源请求
    @GetMapping("/help_to_need/clear")
    public PageBean clear_help_to_need(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer pagesize,
                                        String povertyName, String povertyId){


        log.info("开始分配志愿者并分页查询所有帮扶信息, 页码: {}, 每页显示条数: {}, 参数：{}， {}",
                page, pagesize, povertyName, povertyId);

        help_to_need_service.clear();

        return help_to_need_service.page(page, pagesize,povertyName, povertyId);
    }
}