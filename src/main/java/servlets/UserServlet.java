package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import model.User;
import service.UserService;
import service.UserServiceImpl;
import servlets.dto.UserIncomingDto;
import servlets.dto.UserOutComingDto;
import servlets.mapDir.UserDtoMapper;
import servlets.mapDir.UserDtoMapperImpl;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
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
        resp.setContentType("application/json");

        PrintWriter printWriter = resp.getWriter();

        String userIdParam = req.getParameter("user_id");

        List<User> users = userService.getAllUsers();


        if(userIdParam != null){
            int id = Integer.valueOf(userIdParam);

            User user = userService.findeUserById(id);
            UserOutComingDto userDto = userDtoMapper.outComingUserMap(user);

            if(user != null){
                String jsonUser = gson.toJson(userDto);

                PrintWriter out = resp.getWriter();
                out.println(jsonUser);
            }else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("User not found");
            }
        }else if(!users.isEmpty()){

            List<UserOutComingDto> userDto = users.stream()
                    .map(userDtoMapper::outComingUserMap)
                    .collect(Collectors.toList());

            JsonElement jsonElement = gson.toJsonTree(userDto);
            String userString = gson.toJson(jsonElement);

            printWriter.write(userString);

            printWriter.close();


        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing user ID");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter printWriter = resp.getWriter();

        String query = req.getQueryString();

        if (query == null || !query.equals("create=true")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.write("Invalid action");
            printWriter.close();
            return;
        }

        try {
            BufferedReader reader = req.getReader();
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            reader.close();

            UserIncomingDto userIncomingDto = gson.fromJson(requestBody.toString(), UserIncomingDto.class);


            User user = userDtoMapper.incomingUserMap(userIncomingDto);

            userService.createUser(user);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            printWriter.write("User created successfully");

        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.write("Error while processing request");
        }

        printWriter.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        PrintWriter printWriter = resp.getWriter();

        String query = req.getQueryString();
        if (query == null || !query.equals("update")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.write("Invalid action");
            printWriter.close();
            return;
        }

        try {
            BufferedReader reader = req.getReader();
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            reader.close();

            UserIncomingDto userIncomingDto = gson.fromJson(requestBody.toString(), UserIncomingDto.class);

            User user = userDtoMapper.incomingUserMap(userIncomingDto);

            String userId = req.getParameter("user_id");

            userService.updateUser(user, Integer.valueOf(userId));

            resp.setStatus(HttpServletResponse.SC_OK);
            printWriter.write("User updated successfully");
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.write("Error while processing request");
        }

        printWriter.close();

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        String query = req.getQueryString();
        if (query == null || !query.equals("delete")) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.write("Invalid action");
            printWriter.close();
            return;
        }


        String userId = req.getParameter("user_id");

        try {

            userService.deleteUser(Integer.valueOf(userId));

            resp.setStatus(HttpServletResponse.SC_OK);
            printWriter.write("User deleted successfully");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.write("Error while processing request");
        }

        printWriter.close();

    }


}
