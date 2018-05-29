package am.mainserver.coursemanagement.domain;


import lombok.*;

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
    @SequenceGenerator(name = "course_generator", sequenceName = "course_sequence")
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
        if (this == o) {
            return true;
        }
        if (!(o instanceof Course)) {
            return false;
        }

        Course course = (Course) o;

        return name.equals(course.name);

    }

    @Override
    public int hashCode() {

        int result;
        long temp;
        result = name.hashCode();
        result = 31 * result + description.hashCode();
        temp = Double.doubleToLongBits(duration);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;

    }
}
