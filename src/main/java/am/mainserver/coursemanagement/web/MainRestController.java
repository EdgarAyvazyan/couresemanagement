package am.mainserver.coursemanagement.web;


import am.mainserver.coursemanagement.service.AnnouncementService;
import am.mainserver.coursemanagement.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.security.Principal;


@Controller
public class MainRestController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping(value = "/")
    public String getHome(Model model, Principal principal) {
        if (principal != null) {
            return "redirect:/profile/";
        }
        model.addAttribute("announcements", announcementService.getAnnouncements());
        return "home";
    }

    @GetMapping(value = {"/contact", "/profile/contact"})
    public String contact() {
        return "contact";
    }

    @GetMapping(value = {"/about",
            "/profile/about"})
    public String about() {
        return "about";
    }

}
