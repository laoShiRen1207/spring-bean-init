package com.laoshiren.app.controller;

import com.laoshiren.app.handleradapter.argument.Token;
import com.laoshiren.app.handleradapter.returnvalue.Yml;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author laoshiren
 * @Date 9:50 2023/2/6
 */
@Controller
@Slf4j
public class TestController {

    @GetMapping("/get")
    public ModelAndView get() {
        log.info("get");
        return null;
    }

    @PostMapping("/post")
    public ModelAndView post(@RequestParam("name") String name) {
        log.info("post {}", name);
        return null;
    }

    @PutMapping("/put")
    public ModelAndView put() {
        log.info("put");
        return null;
    }

    @RequestMapping("/request")
    public ModelAndView request() {
        log.info("request");
        return null;
    }

    @GetMapping("/token")
    public ModelAndView token(@Token String token) {
        log.info("token {}", token);
        return null;
    }

    @GetMapping("/yaml")
    @Yml
    public Map<String, Object> yaml() {
        log.info("yaml");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "laoshiren");
        return map;
    }
}
