package servlets;

import com.google.gson.Gson;
import model.Permission;
import service.PermissionService;
import service.PermissionServiceImpl;
import servlets.dto.PermissionIncomingDto;
import servlets.dto.PermissionOutComingDto;
import servlets.mapDir.PermissionDtoMapper;
import servlets.mapDir.PermissionDtoMapperImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/permission")
public class PermissionServlet extends HttpServlet {
    private PermissionDtoMapper permissionDtoMapper;
    private PermissionService permissionService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        permissionDtoMapper = new PermissionDtoMapperImpl();
        permissionService = new PermissionServiceImpl();
        this.gson = new Gson();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String permissionIdParam = req.getParameter("permission_id");

        if(permissionIdParam != null){
            int id = Integer.valueOf(permissionIdParam);

            Permission permission = permissionService.getPermissionById(id);
            PermissionOutComingDto permissionDto = permissionDtoMapper.toPermissionOutComingDto(permission);

            if(permissionDto != null){
                String jsonPermission = gson.toJson(permissionDto);

                PrintWriter out = resp.getWriter();
                out.println(jsonPermission);
            }else {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("Role not found");
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String permissionName = req.getParameter("permissionName");

        PermissionIncomingDto permissionIncomingDto = new PermissionIncomingDto(permissionName);

        Permission permission = permissionDtoMapper.toPermission(permissionIncomingDto);

        permissionService.createPermission(permission);

        resp.setStatus(HttpServletResponse.SC_CREATED);
    }
}
