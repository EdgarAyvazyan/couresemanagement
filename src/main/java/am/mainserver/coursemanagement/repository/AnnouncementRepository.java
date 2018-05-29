package am.mainserver.coursemanagement.repository;

import am.mainserver.coursemanagement.domain.Announcement;
import org.springframework.data.repository.CrudRepository;

public interface AnnouncementRepository extends CrudRepository<Announcement,Long> {
}
