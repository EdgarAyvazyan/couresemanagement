package am.mainserver.coursemanagement.service.impl;


import am.mainserver.coursemanagement.config.HibernateUtilConfig;
import am.mainserver.coursemanagement.domain.Course;
import am.mainserver.coursemanagement.domain.User;
import am.mainserver.coursemanagement.dto.CourseDto;
import am.mainserver.coursemanagement.dto.UserDto;
import am.mainserver.coursemanagement.repository.CourseRepository;
import am.mainserver.coursemanagement.service.CourseService;
import am.mainserver.coursemanagement.service.UserService;
import am.mainserver.coursemanagement.service.exception.CourseExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HibernateUtilConfig hibernateUtilConfig;

    @Override
    public void createCourse(final CourseDto courseDto) throws CourseExistException {

        if (getByNameAndStartDateAndEndDate(courseDto.getName(), courseDto.getStartDate(), courseDto.getEndDate()) != null) {
            throw new CourseExistException("Course already exists");
        }

        final Course currenCcourse = new Course();
        currenCcourse.setName(courseDto.getName());
        currenCcourse.setDuration(courseDto.getDuration());
        currenCcourse.setDescription(courseDto.getDescription());
        currenCcourse.setPrice(courseDto.getPrice());
        currenCcourse.setTutor(courseDto.getTutor());
        currenCcourse.setStartDate(courseDto.getStartDate());
        currenCcourse.setEndDate(courseDto.getEndDate());

        Set<User> userSet = new HashSet<>();

        for (UserDto userDto : courseDto.getUsers()) {
            userSet.add(userService.convertToUser(userDto));
        }
        currenCcourse.setUsers(userSet);
        courseRepository.save(currenCcourse);
    }

    @Override
    public Course getByNameAndStartDateAndEndDate(String name, Date t1, Date t2) {
        return null;
    }


    @Override
    public Course convertToCourse(CourseDto courseDto) {
        final Course course = new Course();
        course.setId(courseDto.getId());
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        course.setDuration(courseDto.getDuration());
        course.setPrice(courseDto.getPrice());
        course.setTutor(courseDto.getTutor());
        course.setStartDate(courseDto.getStartDate());
        course.setEndDate(courseDto.getEndDate());
        final Set<User> userSet = courseDto.getUsers().stream()
                .map(user -> {
                    final User user1 = new User();
                    user1.setId(user.getId());
                    user1.setFirstName(user.getFirstName());
                    user1.setLastName(user.getLastName());
                    user1.setDescription(user.getDescription());
                    user1.setTitle(user.getTitle());
                    user1.setAge(user.getAge());
                    user1.setEmail(user.getEmail());
                    user1.setRoleType(user.getRoleType());
                    user1.setPasswordHash(user.getPasswordHash());
                    user1.setPhoneNumber(user.getPhoneNumber());
                    return user1;
                }).collect(Collectors.toSet());
        course.setUsers(userSet);
        return course;
    }


    @Override
    public CourseDto convertToCourseDto(Course course) {
        final CourseDto courseDto = new CourseDto();
        courseDto.setId(course.getId());
        courseDto.setName(course.getName());
        courseDto.setDescription(course.getDescription());
        courseDto.setDuration(course.getDuration());
        courseDto.setPrice(course.getPrice());
        courseDto.setTutor(course.getTutor());
        courseDto.setStartDate(course.getStartDate());
        courseDto.setEndDate(course.getEndDate());
        final Set<UserDto> userDtoSet = courseDto.getUsers().stream()
                .map(userDto -> {
                    final UserDto userDto1 = new UserDto();
                    userDto1.setId(userDto.getId());
                    userDto1.setFirstName(userDto.getFirstName());
                    userDto1.setLastName(userDto.getLastName());
                    userDto1.setDescription(userDto.getDescription());
                    userDto1.setTitle(userDto.getTitle());
                    userDto1.setAge(userDto.getAge());
                    userDto1.setEmail(userDto.getEmail());
                    userDto1.setRoleType(userDto.getRoleType());
                    userDto1.setPasswordHash(userDto.getPasswordHash());
                    userDto1.setPhoneNumber(userDto.getPhoneNumber());
                    return userDto1;
                }).collect(Collectors.toSet());
        courseDto.setUsers(userDtoSet);
        return courseDto;
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            courses.add(course);
        }
        return courses;
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.getCourseById(id);
    }


    @Override
    public Course update(Long courseId, CourseDto updatedCourse) {
        Course course = courseRepository.getCourseById(courseId);
        course.setName(updatedCourse.getName());
        course.setDuration(updatedCourse.getDuration());
        course.setDescription(updatedCourse.getDescription());
        course.setPrice(updatedCourse.getPrice());
        course.setTutor(updatedCourse.getTutor());
        course.setStartDate(updatedCourse.getStartDate());
        course.setEndDate(updatedCourse.getEndDate());

        final Set<User> usersSet = updatedCourse.getUsers().stream()
                .map(user_element -> {
                    final User user = new User();
                    user.setId(user_element.getId());
                    user.setFirstName(user_element.getFirstName());
                    user.setLastName(user_element.getLastName());
                    user.setAge(user_element.getAge());
                    user.setEmail(user_element.getEmail());
                    user.setDescription(user_element.getDescription());
                    user.setPhoneNumber(user_element.getPhoneNumber());
                    return user;
                }).collect(Collectors.toSet());
        course.setUsers(usersSet);
        return courseRepository.save(course);
    }
}
