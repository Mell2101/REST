package servlets.mapDir;

import model.Role;
import servlets.dto.RoleOutComingDto;
import servlets.dto.RoleIncomingDto;

public interface RoleDtoMapper {
    RoleOutComingDto toRoleOutComingDto(Role role);
    Role toRole(RoleIncomingDto roleIncomingDto);

}
