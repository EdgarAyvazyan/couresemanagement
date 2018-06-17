package am.mainserver.coursemanagement.web;

import am.mainserver.coursemanagement.common.RoleType;
import am.mainserver.coursemanagement.domain.User;
import am.mainserver.coursemanagement.dto.CourseDto;
import am.mainserver.coursemanagement.dto.UserCreationRequestDto;
import am.mainserver.coursemanagement.dto.UserDto;
import am.mainserver.coursemanagement.service.AnnouncementService;
import am.mainserver.coursemanagement.service.CourseService;
import am.mainserver.coursemanagement.service.ImageService;
import am.mainserver.coursemanagement.service.UserService;
import am.mainserver.coursemanagement.service.exception.EmailExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private AuthenticationManager authenticationManager;


    /*change to post request*/
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("user", new UserCreationRequestDto());
        return "register";
    }

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public String login(@Valid final UserCreationRequestDto userCreationRequestDto, HttpServletRequest request, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws EmailExistException {
        if (!userService.isValidEmail(userCreationRequestDto.getEmail())){
            redirectAttributes.addFlashAttribute("ifEmailValid","ok");
            return "redirect:/register";
        }

        if(bindingResult.hasErrors()){
            return "/register";
        }
        userService.register(userCreationRequestDto);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userCreationRequestDto.getEmail(),userCreationRequestDto.getPasswordHash());
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        try {
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/profile/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


    @GetMapping(value = {"/profile/","/profile"})
    public String profile(Model model, Principal principal) {


            model.addAttribute("user", userService.getByEmail(principal.getName()));
            model.addAttribute("message", "You are logged in as " + userService.getUserFullName(principal.getName()));
            model.addAttribute("userID", "with userId " + userService.getUserId(principal.getName()));
            model.addAttribute("announcements", announcementService.getAnnouncements());
            model.addAttribute("courses",courseService.getCourses());
            String file_name;
            if (imageService.getImageByUserId(userService.getUserId(principal.getName())) != null) {
                String[] tokens = imageService.getImageByUserId(userService.getUserId(principal.getName())).getImageUrl().split("\\\\");
                file_name = tokens[4];
                model.addAttribute("image_file_name", file_name);
            }else {

                file_name = "noimage.jpg";
                model.addAttribute("image_file_name",file_name);
            }

            if (userService.getByEmail(principal.getName()).getRoleType().equals(RoleType.TUTOR)){
                model.addAttribute("create_course","CREATE COURSE");
                model.addAttribute("students",userService.getByEmail(principal.getName()).getCourses());
            }
            model.addAttribute("userDto",userService.convertToUserDto(userService.getByEmail(principal.getName())));
            model.addAttribute("course", new CourseDto());
            model.addAttribute("user_edit","ok");
            return "profileHome";

    }


    @RequestMapping(value = "/editUserInfo",method = RequestMethod.POST)
    public String editUserInfo(UserDto userDto, @RequestParam("id")Long id,Principal principal){
        userDto.setPasswordHash(BCrypt.hashpw(userDto.getPasswordHash(),BCrypt.gensalt(12)));
        userDto.setCourses(userService.convertToUserDto(userService.getByEmail(principal.getName())).getCourses());
        User convertToUser = userService.convertToUser(userDto);
        userService.update(id,convertToUser);
        return "redirect:/profile/";
    }

    @PostMapping(value = "/enroll")
    public String enroll(@RequestParam("id")Long id,Principal principal){
        UserDto userDto = userService.convertToUserDto(userService.getByEmail(principal.getName()));
        userDto.getCourses().add(courseService.convertToCourseDto(courseService.getCourseById(id)));
        userService.update(userDto.getId(),userService.convertToUser(userDto));

        return "redirect:/profile/";
    }
}

