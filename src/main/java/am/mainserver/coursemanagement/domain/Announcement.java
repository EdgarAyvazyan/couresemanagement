package am.mainserver.coursemanagement.domain;

import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "announcement")
@NoArgsConstructor
@Getter
@Setter

public class Announcement {
    @Id
    @SequenceGenerator(name = "announcement_generator" , sequenceName = "announcement_sequence",allocationSize = 1)
    @GeneratedValue(generator = "announcement_generator",strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Announcement)) return false;

        Announcement that = (Announcement) o;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("description", description)
                .append("title", title)
                .toString();
    }
}


