package am.mainserver.coursemanagement.repository;

import am.mainserver.coursemanagement.domain.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {
    Course getByNameAndStartDateAndEndDate(String name, Date t1, Date t2);
    Course getCourseById(Long id);
}