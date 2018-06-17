package am.mainserver.coursemanagement.web;


import am.mainserver.coursemanagement.common.RoleType;
import am.mainserver.coursemanagement.domain.Course;
import am.mainserver.coursemanagement.domain.User;
import am.mainserver.coursemanagement.dto.CourseDto;
import am.mainserver.coursemanagement.service.CourseService;
import am.mainserver.coursemanagement.service.UserService;
import am.mainserver.coursemanagement.service.exception.CourseExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;


    @PostMapping("/create_course")
    public String createCourse(Model model,Principal principal, @Validated final CourseDto courseDto,
                               BindingResult bindingResult, RedirectAttributes redirectAttributes) throws CourseExistException {
        courseDto.getUsers().add(userService.convertToUserDto(userService.getByEmail(principal.getName())));
        courseDto.setTutor(userService.getUserFullName(principal.getName()));
        User updatedUser = userService.getByEmail(principal.getName());
        updatedUser.getCourses().add(courseService.convertToCourse(courseDto));
        userService.update(userService.getUserId(principal.getName()),updatedUser);


        if (bindingResult.hasErrors()){
            return "redirect:/profile/";
        }

        redirectAttributes.addFlashAttribute("action_successfull","ok");
        return "redirect:/profile/";
    }

    @GetMapping("/course")
    public String getCourse(@RequestParam("id") Long id, Model model,Principal principal){

        Course course = courseService.getCourseById(id);
        String currentCourseName = course.getName().toLowerCase();
        model.addAttribute("course",course);

        if (principal == null){
            model.addAttribute("course_register","ok");
        }

        if (principal != null && userService.getByEmail(principal.getName()).getRoleType().equals(RoleType.TUTOR)) {
            model.addAttribute("tutor_register","ok");
            userService.getByEmail(principal.getName()).getCourses().forEach(course1 -> {
                if (course1.getId() == id) {
                    model.addAttribute("editable", "true");
                    model.addAttribute("tutor_edit","ok");
                }
            });
        }


        if (currentCourseName.startsWith("java") && currentCourseName.charAt(4) != 's'){
            currentCourseName = "java-logo.jpg";
            model.addAttribute("course_image_file_name",currentCourseName);
        }
        else if(currentCourseName.contains("c#") || currentCourseName.contains("c sharp")){
            currentCourseName = "c sharp.jpg";
            model.addAttribute("course_image_file_name",currentCourseName);
        }
        else if(currentCourseName.contains("javascript")){
            currentCourseName = "Javascript.jpg";
            model.addAttribute("course_image_file_name",currentCourseName);
        }
        else if(currentCourseName.contains("ios")){
            currentCourseName = "ios.jpg";
            model.addAttribute("course_image_file_name",currentCourseName);
        }
        else if(currentCourseName.contains("python")){
            currentCourseName = "python.jpg";
            model.addAttribute("course_image_file_name",currentCourseName);
        }
        else {
            currentCourseName = "default.jpg";
            model.addAttribute("course_image_file_name",currentCourseName);
        }
        return "course";
    }


    @RequestMapping(value = "/editCourseInfo",method = RequestMethod.POST)
        public String editCourseInfo(CourseDto courseDto,@RequestParam("id") Long id,Principal principal){
        final Course courseById = courseService.getCourseById(id);
        final String userFullName = userService.getUserFullName(principal.getName());
        courseById.setTutor(userFullName);
        courseService.update(id,courseDto);
        return "redirect:/profile/";
    }


    @GetMapping(value = "/students")
    public String GetStudents(@RequestParam("id")Long id,Principal principal,Model model){
        List<User> students = new ArrayList<>();
        courseService.getCourseById(id).getUsers().forEach(user -> {
            if (user.getId() != userService.getByEmail(principal.getName()).getId()){
                students.add(user);
            }
        });

        model.addAttribute("students",students);
        return "students";
    }
}


