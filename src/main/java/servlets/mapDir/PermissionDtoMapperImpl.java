package servlets.mapDir;

import model.Permission;
import servlets.dto.PermissionIncomingDto;
import servlets.dto.PermissionOutComingDto;

public class PermissionDtoMapperImpl implements PermissionDtoMapper{
    @Override
    public PermissionOutComingDto toPermissionOutComingDto(Permission permission) {
        if (permission == null) {
            return null;
        }

        PermissionOutComingDto permissionOutComingDto = new PermissionOutComingDto();
        permissionOutComingDto.setId(String.valueOf(permission.getId()));
        permissionOutComingDto.setPermissionName(permission.getName());

        return permissionOutComingDto;
    }

    @Override
    public Permission toPermission(PermissionIncomingDto permissionIncomingDto) {
        if (permissionIncomingDto == null) {
            return null;
        }

        Permission permission = new Permission();
        permission.setName(permissionIncomingDto.getPermissionName());

        return permission;
    }
}
