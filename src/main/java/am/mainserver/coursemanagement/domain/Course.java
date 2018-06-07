package am.mainserver.coursemanagement.domain;


import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "course")
@NoArgsConstructor
@Getter
@Setter
@ToString
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
                .append(getUsers())
                .append(getScores())
                .toHashCode();
    }

}
