package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import model.Role;
import service.RoleService;
import service.RoleServiceImpl;
import servlets.dto.RoleIncomingDto;
import servlets.dto.RoleOutComingDto;
import servlets.mapDir.RoleDtoMapper;
import servlets.mapDir.RoleDtoMapperImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/role")
public class RoleServlet extends HttpServlet {
    private RoleDtoMapper roleDtoMapper;
    private RoleService roleService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        this.roleDtoMapper = new RoleDtoMapperImpl();
        this.roleService = new RoleServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        String roleIdParam = req.getParameter("role_id");

        List<Role> roles = roleService.getAllRoles();

        if(roleIdParam != null){
            int id = Integer.valueOf(roleIdParam);

            Role role = roleService.findRoleById(id);
            RoleOutComingDto roleDto = roleDtoMapper.toRoleOutComingDto(role);

            if(role != null){
                String jsonRole = gson.toJson(roleDto);

                PrintWriter out = resp.getWriter();
                out.println(jsonRole);
            }else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Role not found");
            }
        } else if (!roles.isEmpty()) {
            List<RoleOutComingDto> roleDtos = roles.stream()
                    .map(roleDtoMapper::toRoleOutComingDto)
                    .collect(Collectors.toList());

            JsonElement jsonRoles = gson.toJsonTree(roleDtos);
            String rolesString = gson.toJson(jsonRoles);

            printWriter.write(rolesString);
            printWriter.close();
        }else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing role ID");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        try {
            // Получаем данные о новой роли из тела запроса
            BufferedReader reader = req.getReader();
            StringBuilder requestBody = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            reader.close();

            RoleIncomingDto roleDto = gson.fromJson(requestBody.toString(), RoleIncomingDto.class);

            Role role = roleDtoMapper.toRole(roleDto);

            roleService.createRole(role);

            resp.setStatus(HttpServletResponse.SC_CREATED);
            printWriter.write("Role created successfully");
        } catch (IOException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            printWriter.write("Invalid request body");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.write("Error while processing request");
        }

        printWriter.close();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
        PrintWriter printWriter = resp.getWriter();

        try {

            String roleId = req.getParameter("role_id");
            if (roleId == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                printWriter.write("Role ID is required");
                printWriter.close();
                return;
            }


            roleService.deleteRole(Integer.valueOf(roleId));

            resp.setStatus(HttpServletResponse.SC_OK);
            printWriter.write("Role deleted successfully");
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            printWriter.write("Error while processing request");
        }

        printWriter.close();
    }
}
