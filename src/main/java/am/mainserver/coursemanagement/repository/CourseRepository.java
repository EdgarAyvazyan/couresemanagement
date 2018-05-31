package am.mainserver.coursemanagement.repository;

import am.mainserver.coursemanagement.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course,Long> {
}
