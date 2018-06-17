package am.mainserver.coursemanagement.service;

import am.mainserver.coursemanagement.domain.User;
import am.mainserver.coursemanagement.dto.UserCreationRequestDto;
import am.mainserver.coursemanagement.dto.UserDto;
import am.mainserver.coursemanagement.service.exception.EmailExistException;

public interface UserService {
    User getByEmail(String email);

    void register(UserCreationRequestDto creationRequestDto) throws EmailExistException;

    String getUserFullName(String email);

    Long getUserId(String email);

    UserDto convertToUserDto(User user);

    User convertToUser(UserDto userDto);

    User update(Long userId,User updatedUser);

    Boolean isValidEmail(String email);
}
