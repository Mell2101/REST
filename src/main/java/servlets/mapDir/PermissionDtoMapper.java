package servlets.mapDir;

import model.Permission;
import servlets.dto.PermissionIncomingDto;
import servlets.dto.PermissionOutComingDto;

public interface PermissionDtoMapper {
    PermissionOutComingDto toPermissionOutComingDto(Permission permission);
    Permission toPermission(PermissionIncomingDto permissionIncomingDto);

}
