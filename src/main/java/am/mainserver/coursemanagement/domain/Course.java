package am.mainserver.coursemanagement.domain;


import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.TemporalType.DATE;


@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @SequenceGenerator(name = "course_generator", sequenceName = "course_sequence",allocationSize = 1)
    @GeneratedValue(generator = "course_generator", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "description")
        private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "start_date")
    private Date startDate ;

    @Column(name = "end_date")
    private Date endDate ;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Score> scores = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        return new EqualsBuilder()
                .append(getId(), course.getId())
                .append(getName(), course.getName())
                .append(getDuration(), course.getDuration())
                .append(getDescription(), course.getDescription())
                .append(getPrice(), course.getPrice())
                .append(getUsers(), course.getUsers())
                .append(getScores(), course.getScores())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getDuration())
                .append(getDescription())
                .append(getPrice())
                .append(getScores())
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("duration", duration)
                .append("description", description)
                .append("price", price)
                .append("startDate", startDate)
                .append("endDate", endDate)
                .append("users", users)
                .append("scores", scores)
                .toString();
    }
}
