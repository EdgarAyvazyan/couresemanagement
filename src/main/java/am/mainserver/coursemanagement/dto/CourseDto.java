package am.mainserver.coursemanagement.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class CourseDto {

    private Long id;

    private String name;

    private Integer duration;

    private String description;

    private Double price;

    private Date startDate;

    private Date endDate;

    private Set<UserDto> users = new HashSet<>();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof CourseDto)) return false;

        CourseDto courseDto = (CourseDto) o;

        return new EqualsBuilder()
                .append(getId(), courseDto.getId())
                .append(getName(), courseDto.getName())
                .append(getDuration(), courseDto.getDuration())
                .append(getDescription(), courseDto.getDescription())
                .append(getPrice(), courseDto.getPrice())
                .append(getUsers(), courseDto.getUsers())
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
                .toHashCode();
    }
}
