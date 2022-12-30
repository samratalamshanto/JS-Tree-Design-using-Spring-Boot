package com.example.demojstreeproject.controller;

import com.example.demojstreeproject.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CommonController extends HttpServlet {
    @Autowired
    DemoRepository demoRepository;
    @Autowired
    DemoController demoController;



    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap modelMap,HttpServletRequest req, HttpServletResponse resp) {
        System.out.println(req);
        System.out.println(resp);

        modelMap.put("user", "Samrat Alam");
        modelMap.put("userList", demoController.welcome());
        return "home";
    }
}
