package am.mainserver.coursemanagement.web;


import am.mainserver.coursemanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
public class MainRestController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public String  getHome() {
        return "home";
    }

    @GetMapping(value = "/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping(value = "/about")
    public String about(){
        return "about";
    }

}
