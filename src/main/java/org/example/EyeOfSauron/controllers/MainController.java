package org.example.EyeOfSauron.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/failureTime")
    public String failureTime(Map<String, Object> model) {
        return "failureTime";
    }

    @GetMapping("/failureFamily")
    public String failureFamily(Map<String, Object> model) {
        return "failureFamily";
    }

    @GetMapping("/complete")
    public String complete(Map<String, Object> model) {
        return "complete";
    }

    @GetMapping("/divorceComplete")
    public String divorceComplete(Map<String, Object> model) {
        return "divorceComplete";
    }

    @GetMapping("/marryComplete")
    public String marryComplete(Map<String, Object> model) {
        return "marryComplete";
    }

    @GetMapping("/profile")
    public String profile(Map<String, Object> model) {
        return "redirect:/user/profile";
    }
}