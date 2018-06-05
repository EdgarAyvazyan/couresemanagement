package am.mainserver.coursemanagement.web;

import am.mainserver.coursemanagement.dto.UserCreationRequestDto;
import am.mainserver.coursemanagement.service.UserService;
import am.mainserver.coursemanagement.service.impl.EmailExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register")
    public String register(Model model){
        model.addAttribute("user", new UserCreationRequestDto());
        return "register";
    }

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String login(@Valid final UserCreationRequestDto userCreationRequestDto) throws EmailExistException {
        userService.register(userCreationRequestDto);
        return "login";
    }


    @RequestMapping(value = "/login")
    public String login(Model model){
//        model.addAttribute("user", new UserCreationRequestDto());
        return "login";
    }

}
