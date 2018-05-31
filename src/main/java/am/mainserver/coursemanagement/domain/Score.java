package am.mainserver.coursemanagement.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "score")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Score {

    @Id
    @SequenceGenerator(name = "score_generator", sequenceName = "score_sequence")
    @GeneratedValue(generator = "score_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "attendance")
    private Integer attendances;

    @Column(name = "knowledge")
    private Integer knowledge;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id",referencedColumnName = "id")
    private Course course;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Score)) return false;

        Score score = (Score) o;

        return new EqualsBuilder()
                .append(getId(), score.getId())
                .append(getAttendances(), score.getAttendances())
                .append(getKnowledge(), score.getKnowledge())
                .append(getUser(), score.getUser())
                .append(getCourse(), score.getCourse())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getAttendances())
                .append(getKnowledge())
                .append(getUser())
                .append(getCourse())
                .toHashCode();
    }
}
