package servlets.mapDir;

import model.User;
import servlets.dto.UserIncomingDto;
import servlets.dto.UserOutComingDto;

public interface UserDtoMapper {
    UserOutComingDto outComingUserMap(User user);

    User incomingUserMap(UserIncomingDto userIncomingDto);
}
