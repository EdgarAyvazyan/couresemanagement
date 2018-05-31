package am.mainserver.coursemanagement.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ScoreDto {

    private Long id;

    private Integer attendances;

    private Integer knowledge;

    private UserDto user;

    private CourseDto course;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ScoreDto)) return false;

        ScoreDto scoreDto = (ScoreDto) o;

        return new EqualsBuilder()
                .append(getId(), scoreDto.getId())
                .append(getAttendances(), scoreDto.getAttendances())
                .append(getKnowledge(), scoreDto.getKnowledge())
                .append(getUser(), scoreDto.getUser())
                .append(getCourse(), scoreDto.getCourse())
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
