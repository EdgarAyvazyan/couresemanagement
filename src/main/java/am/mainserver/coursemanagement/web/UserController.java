package am.mainserver.coursemanagement.web;

import am.mainserver.coursemanagement.common.RoleType;
import am.mainserver.coursemanagement.dto.CourseDto;
import am.mainserver.coursemanagement.dto.UserCreationRequestDto;
import am.mainserver.coursemanagement.service.AnnouncementService;
import am.mainserver.coursemanagement.service.CourseService;
import am.mainserver.coursemanagement.service.ImageService;
import am.mainserver.coursemanagement.service.UserService;
import am.mainserver.coursemanagement.service.exception.EmailExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.validation.Valid;


import java.security.Principal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private CourseService courseService;


    /*change to post request*/
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new UserCreationRequestDto());
        return "register";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String login(@Valid final UserCreationRequestDto userCreationRequestDto) throws EmailExistException {
        userService.register(userCreationRequestDto);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @GetMapping(value = "/profile/")
    public String profile(Model model, Principal principal) {

        model.addAttribute("user", userService.getByEmail(principal.getName()));
        model.addAttribute("message", "You are logged in as " + userService.getUserFullName(principal.getName()));
        model.addAttribute("userID", "with userId " + userService.getUserId(principal.getName()));
        model.addAttribute("announcements", announcementService.getAnnouncements());
        model.addAttribute("courses",courseService.getCourses());

        if (imageService.getImageByUserId(userService.getUserId(principal.getName())) != null) {
            String[] tokens = imageService.getImageByUserId(userService.getUserId(principal.getName())).getImageUrl().split("\\\\");
            String file_name = tokens[4];
            model.addAttribute("image_file_name", file_name);
        }

        if (userService.getByEmail(principal.getName()).getRoleType().equals(RoleType.TUTOR)){
            model.addAttribute("create_course","CREATE COURSE");
        }

        model.addAttribute("course", new CourseDto());
        return "profileHome";
    }
}

