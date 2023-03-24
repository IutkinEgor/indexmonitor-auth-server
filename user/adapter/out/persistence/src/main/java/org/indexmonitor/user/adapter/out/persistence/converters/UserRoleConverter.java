//package org.indexmonitor.user.adapter.out.persistence.converters;
//
//import org.indexmonitor.common.domain.valueObjects.BaseId;
//import org.indexmonitor.user.adapter.out.persistence.entities.RoleEntity;
//import org.indexmonitor.user.adapter.out.persistence.rolerepository.RoleRepository;
//import org.indexmonitor.user.adapter.out.persistence.services.role.RoleLoadPersistenceService;
//import org.indexmonitor.user.application.ports.out.role.RoleLoadPort;
//import org.indexmonitor.user.domain.valueObjects.UserRole;
//import jakarta.persistence.AttributeConverter;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.DependsOn;
//
//
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Component
//public class UserRoleConverter implements AttributeConverter<Set<UserRole>,Set<String>> {
//
//    @Override
//    public Set<String> convertToDatabaseColumn(Set<UserRole> roles) {
//        if (roles == null || roles.isEmpty()) {
//            return null;
//        }
//        return roles.stream().map(role -> role.getRoleId().getValueAsString()).collect(Collectors.toSet());
//    }
//
//    @Override
//    public Set<UserRole> convertToEntityAttribute(Set<String> roleIds) {
//        if (roleIds == null || roleIds.isEmpty()) {
//            return null;
//        }
////        return roleRepository.findAllByIdIn(roleIds.stream().map(roleId -> RoleEntity.convertId(BaseId.map(roleId))).collect(Collectors.toSet()))
////                .stream().map(roleProjection -> new UserRole(BaseId.map(roleProjection.getId()),roleProjection.getName())).collect(Collectors.toSet());
//        return roleIds.stream().map(role-> new UserRole(BaseId.map(role),role + "name")).collect(Collectors.toSet());
//    }
//
////    @Autowired
////    public RoleRepository getRoleRepository(RoleRepository roleRepository){
////        return roleRepository;
////    }
//}
//
//
//
//
//
