package am.mainserver.coursemanagement.domain;

import am.mainserver.coursemanagement.common.RoleType;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "`user`")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {


    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(generator = "user_generator", strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(name = "title")
    private String title;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "role_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Column(name = "password_hash",nullable = false)
    private String passwordHash;

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER)
    private Image image;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_course",joinColumns = {@JoinColumn(name = "user_id")}
    ,inverseJoinColumns = {@JoinColumn(name = "course_id",unique = true)})
    private Set<Course> courses;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_course_score",
    joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "score_id"))
    @MapKeyJoinColumn(name = "course_id")
    private Map<Course,Score> courseScoreMap = new HashMap<>();


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Score> scores = new HashSet<>();


    //TODO/**usage of this part???**/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Lists.newArrayList(new SimpleGrantedAuthority("ROLE_" + roleType.name()));
    }

    @Override
    public String getPassword() {
        return getPasswordHash();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(getId(), user.getId())
                .append(getTitle(), user.getTitle())
                .append(getFirstName(), user.getFirstName())
                .append(getLastName(), user.getLastName())
                .append(getAge(), user.getAge())
                .append(getEmail(), user.getEmail())
                .append(getDescription(), user.getDescription())
                .append(getPhoneNumber(), user.getPhoneNumber())
                .append(getRoleType(), user.getRoleType())
                .append(getPasswordHash(), user.getPasswordHash())
                .append(getCourses(), user.getCourses())
                .append(getCourseScoreMap(), user.getCourseScoreMap())
                .append(getScores(), user.getScores())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getTitle())
                .append(getFirstName())
                .append(getLastName())
                .append(getAge())
                .append(getEmail())
                .append(getDescription())
                .append(getPhoneNumber())
                .append(getRoleType())
                .append(getPasswordHash())
                .append(getCourseScoreMap())
                .append(getScores())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("age", age)
                .append("email", email)
                .append("description", description)
                .append("phoneNumber", phoneNumber)
                .append("roleType", roleType)
                .append("passwordHash", passwordHash)
                .append("image", image)
                .append("courses", courses)
                .append("courseScoreMap", courseScoreMap)
                .append("scores", scores)
                .toString();
    }
}
