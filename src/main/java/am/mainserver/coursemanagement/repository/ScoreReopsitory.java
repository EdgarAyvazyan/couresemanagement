package am.mainserver.coursemanagement.repository;

import am.mainserver.coursemanagement.domain.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreReopsitory extends CrudRepository<Score,Long> {
}
