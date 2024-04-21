package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import model.User;
import service.UserService;
import service.UserServiceImpl;
import servlets.dto.UserIncomingDto;
import servlets.dto.UserOutcomingDto;
import servlets.mapDir.UserDtoMapper;
import servlets.mapDir.UserDtoMapperImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import java.util.stream.Collectors;

public class UserServlet extends HttpServlet {
    private UserService userService;
    private UserDtoMapper userDtoMapper;
    private final Gson gson;

    public UserServlet(){
        userService = new UserServiceImpl();
        userDtoMapper = new UserDtoMapperImpl();
        gson = new Gson();

    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUserDtoMapper(UserDtoMapper userDtoMapper) {
        this.userDtoMapper = userDtoMapper;
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String query = req.getQueryString();
        PrintWriter printWriter = resp.getWriter();

        if (query != null && req.getParameter("id") != null) {

            int id = Integer.valueOf(req.getParameter("id"));

            User userEntity = userService.findeUserById(id);
            UserOutcomingDto userOutcomingDto = userDtoMapper.outComingUserMap(userEntity);

            JsonElement jsonUser = gson.toJsonTree(userOutcomingDto);
            String userString = gson.toJson(jsonUser);

            printWriter.write(userString);

        } else if (query == null) {

            List<User> userEntities = userService.getAllUsers();
            List<UserOutcomingDto> users = userEntities.stream().map(userDtoMapper::outComingUserMap).toList();

            JsonElement jsonUsers = gson.toJsonTree(users);

            String usersString = gson.toJson(jsonUsers);
            printWriter.write(usersString);
        }

        printWriter.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String getUser = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        UserIncomingDto userIncomingDto = gson.fromJson(getUser, UserIncomingDto.class);
        User userEntity = userDtoMapper.incomingUserMap(userIncomingDto);
        User outComingUser = userService.createUser(userEntity);

        UserOutcomingDto userOutcomingDto = userDtoMapper.outComingUserMap(outComingUser);
        JsonElement jsonUser = gson.toJsonTree(userOutcomingDto);
        String userOutString = gson.toJson(jsonUser);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write(userOutString);
        printWriter.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String query = req.getQueryString();
        if (query != null && req.getParameter("id") != null) {

            String userString = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            int id = Integer.valueOf(req.getParameter("id"));

            UserIncomingDto userIncomingDto = gson.fromJson(userString, UserIncomingDto.class);
            User userEntity = userService.updateUser(userDtoMapper.incomingUserMap(userIncomingDto), id);

            UserOutcomingDto userOutcomingDto = userDtoMapper.outComingUserMap(userEntity);
            JsonElement jsonUser = gson.toJsonTree(userOutcomingDto);
            String userOutString = gson.toJson(jsonUser);

            PrintWriter printWriter = resp.getWriter();
            printWriter.write(userOutString);
            printWriter.close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String query = req.getQueryString();

        if (query != null && req.getParameter("id") != null) {
            int id = Integer.valueOf(req.getParameter("id"));
            userService.deleteUser(id);
        }
    }


}
