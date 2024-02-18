package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.entity.Class;
import com.example.service.ClassService;
import com.example.service.LoginUser;

@Controller
public class MenuController {

  private final ClassService classService;

  public MenuController(ClassService classService) {
	  this.classService = classService;
  }

@GetMapping("/menu")
    public String getHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {
	    Class classInfo = this.classService.findById(loginUser.getUser().getClassId());
	    model.addAttribute("classInfo", classInfo);
	    return "menu";
  }
}