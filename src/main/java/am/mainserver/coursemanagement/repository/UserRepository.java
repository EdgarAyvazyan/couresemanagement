package am.mainserver.coursemanagement.repository;

import am.mainserver.coursemanagement.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
