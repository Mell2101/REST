package servlets.mapDir;

import model.User;
import servlets.dto.UserIncomingDto;
import servlets.dto.UserOutcomingDto;

public interface UserDtoMapper {
    UserOutcomingDto outComingUserMap(User user);

    User incomingUserMap(UserIncomingDto userIncomingDto);
}
