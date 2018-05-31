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
public class AnnouncementDto {


    private Long id;

    private String description;

    private String title;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof AnnouncementDto)) return false;

        AnnouncementDto that = (AnnouncementDto) o;

        return new EqualsBuilder()
                .append(getId(), that.getId())
                .append(getDescription(), that.getDescription())
                .append(getTitle(), that.getTitle())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getDescription())
                .append(getTitle())
                .toHashCode();
    }
}
