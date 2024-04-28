package servlets.mapDir;

import model.User;
import servlets.dto.UserIncomingDto;
import servlets.dto.UserOutComingDto;

public class UserDtoMapperImpl implements UserDtoMapper{
    @Override
    public UserOutComingDto outComingUserMap(User user) {

        UserOutComingDto userOutComingDto = new UserOutComingDto();
        userOutComingDto.setId(String.valueOf(user.getId()));
        userOutComingDto.setName(user.getUserName());


        return userOutComingDto;
    }

    @Override
    public User incomingUserMap(UserIncomingDto userIncomingDto) {
        if(userIncomingDto == null){
            return null;
        }

        User user = new User();
        user.setUserName(userIncomingDto.getUserName());
        user.setId(userIncomingDto.getId());

        return user;
    }
}
