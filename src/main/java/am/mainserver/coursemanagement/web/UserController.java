package am.mainserver.coursemanagement.web;

import am.mainserver.coursemanagement.dto.UserCreationRequestDto;
import am.mainserver.coursemanagement.service.AnnouncementService;
import am.mainserver.coursemanagement.service.UserService;
import am.mainserver.coursemanagement.service.impl.EmailExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AnnouncementService announcementService;

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new UserCreationRequestDto());
        return "register";
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String login(@Valid final UserCreationRequestDto userCreationRequestDto) throws EmailExistException {
        userService.register(userCreationRequestDto);
        return "login";
    }

    @RequestMapping(value = "/login" ,method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping(value = "/profile/" ,method = RequestMethod.GET)
    public String profile(Model model, Principal principal){
        if (principal == null){
            return "redirect:/login";
        }else{
            model.addAttribute("user",userService.getByEmail(principal.getName()));
            model.addAttribute("message","You are logged in as " + userService.getUserFullName(principal.getName()));
            model.addAttribute("userID","with userId " + userService.getUserId(principal.getName()));
            model.addAttribute("announcements",announcementService.getAnnouncements());
            return "/profileHome";
        }

    }
}
