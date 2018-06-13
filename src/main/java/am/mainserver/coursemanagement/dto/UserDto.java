package am.mainserver.coursemanagement.dto;

import am.mainserver.coursemanagement.common.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {

    private Long id;

    private String title;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String description;

    private String phoneNumber;

    private RoleType roleType;

    private String passwordHash;

    private Set<CourseDto> courses = new HashSet<>();

    private Map<CourseDto,ScoreDto> courseScoreMap = new HashMap<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UserDto)) return false;

        UserDto userDto = (UserDto) o;

        return new EqualsBuilder()
                .append(getId(), userDto.getId())
                .append(getTitle(), userDto.getTitle())
                .append(getFirstName(), userDto.getFirstName())
                .append(getLastName(), userDto.getLastName())
                .append(getAge(), userDto.getAge())
                .append(getEmail(), userDto.getEmail())
                .append(getDescription(), userDto.getDescription())
                .append(getPhoneNumber(), userDto.getPhoneNumber())
                .append(getRoleType(), userDto.getRoleType())
                .append(getCourses(), userDto.getCourses())
                .append(getCourseScoreMap(), userDto.getCourseScoreMap())
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
                .append(getCourses())
                .append(getCourseScoreMap())
                .toHashCode();
    }
}
