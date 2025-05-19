package com.zair.api.dtos.request;

import com.zair.data.entities.UserEntity;
import com.zair.data.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;

    public void doDefault() {
        if (Objects.isNull(role)) {
            role = Role.LIBRARIAN;
        }
    }

    public UserEntity toEntity() {
        this.doDefault();

        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(this, user);
        user.setPassword(new BCryptPasswordEncoder().encode(this.password));
        user.setDeleted(false);
        return user;
    }
}
