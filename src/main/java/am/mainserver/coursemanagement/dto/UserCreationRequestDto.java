package am.mainserver.coursemanagement.dto;


import am.mainserver.coursemanagement.common.RoleType;
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
public class UserCreationRequestDto {

    private String title;

    private String firstName;

    private String lastName;

    private Integer age;

    private String email;

    private String passwordHash;

    private String phoneNumber;

    private RoleType roleType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof UserCreationRequestDto)) return false;

        UserCreationRequestDto that = (UserCreationRequestDto) o;

        return new EqualsBuilder()
                .append(getTitle(), that.getTitle())
                .append(getFirstName(), that.getFirstName())
                .append(getLastName(), that.getLastName())
                .append(getAge(), that.getAge())
                .append(getEmail(), that.getEmail())
                .append(getPasswordHash(), that.getPasswordHash())
                .append(getPhoneNumber(), that.getPhoneNumber())
                .append(getRoleType(), that.getRoleType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTitle())
                .append(getFirstName())
                .append(getLastName())
                .append(getAge())
                .append(getEmail())
                .append(getPasswordHash())
                .append(getPhoneNumber())
                .append(getRoleType())
                .toHashCode();
    }
}
