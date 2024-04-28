package servlets.mapDir;

import model.Role;
import servlets.dto.RoleOutComingDto;
import servlets.dto.RoleIncomingDto;

public class RoleDtoMapperImpl implements RoleDtoMapper {
    @Override
    public RoleOutComingDto toRoleOutComingDto(Role role) {
        if (role == null) {
            return null;
        }

        RoleOutComingDto roleOutComingDto = new RoleOutComingDto();
        roleOutComingDto.setId(String.valueOf(role.getId()));
        roleOutComingDto.setName(role.getName());
        roleOutComingDto.setUserId(role.getUserId());

        return roleOutComingDto;
    }

    @Override
    public Role toRole(RoleIncomingDto roleIncomingDto) {
        if (roleIncomingDto == null) {
            return null;
        }

        Role role = new Role();
        role.setName(roleIncomingDto.getRoleName());
        role.setId(Integer.valueOf(roleIncomingDto.getUserId()));

        return role;
    }
}
