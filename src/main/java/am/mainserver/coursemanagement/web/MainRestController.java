package am.mainserver.coursemanagement.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainRestController {

    @GetMapping(value = "/")
    public String home(){
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
