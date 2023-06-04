package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/home")
  public String home() {
    return "home";
  }

  @GetMapping("/general")
  public String general() {
    return "general";
  }

  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }
}
