package com.zair.api.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zair.data.entities.UserEntity;
import com.zair.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDto {
    private String firstName;
    private String lastName;
    private String username;
    private Role role;

    public static UserResponseDto toResponse(UserEntity user) {
        UserResponseDto dto = new UserResponseDto();
        BeanUtils.copyProperties(user, dto);
        return dto;
    }
}
