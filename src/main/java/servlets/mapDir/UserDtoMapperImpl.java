package servlets.mapDir;

import model.User;
import servlets.dto.UserIncomingDto;
import servlets.dto.UserOutcomingDto;

public class UserDtoMapperImpl implements UserDtoMapper{
    @Override
    public UserOutcomingDto outComingUserMap(User user) {

        UserOutcomingDto userOutcomingShortDto = new UserOutcomingDto();
        userOutcomingShortDto.setId(String.valueOf(user.getId()));
        userOutcomingShortDto.setName(user.getUserName());
        return userOutcomingShortDto;
    }

    @Override
    public User incomingUserMap(UserIncomingDto userIncomingDto) {
        return null;
    }
}
