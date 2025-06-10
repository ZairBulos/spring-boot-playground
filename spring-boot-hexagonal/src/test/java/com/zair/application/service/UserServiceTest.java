package com.zair.application.service;

import com.zair.application.port.in.dto.CreateUserCommand;
import com.zair.application.port.in.dto.UserDto;
import com.zair.application.port.out.UserPersistencePort;
import com.zair.domain.exception.UserAlreadyExistsException;
import com.zair.domain.exception.UserNotFoundException;
import com.zair.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    UserPersistencePort persistence;

    @InjectMocks
    UserService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_shouldReturnListOfUserDtos() {
        // Arrange
        var user1 = new User(1L, "John Doe", "j.doe@example.com");
        var user2 = new User(2L, "Jane Doe", "jane.doe@example.com");
        List<User> domain = List.of(user1, user2);
        when(persistence.findAll()).thenReturn(domain);

        // Act
        List<UserDto> result = service.getAll();

        // Assert
        verify(persistence).findAll();
        verifyNoMoreInteractions(persistence);
        assertThat(result)
                .hasSize(2)
                .extracting("id", "name", "email")
                .containsExactlyInAnyOrder(
                    tuple(1L, "John Doe", "j.doe@example.com"),
                    tuple(2L, "Jane Doe", "jane.doe@example.com")
                );
    }

    @Test
    void getById_whenUserExists_shouldReturnUserDto() {
        // Arrange
        Long id = 1L;
        var domain = new User(id, "John Doe", "j.doe@example.com");
        when(persistence.findById(id)).thenReturn(Optional.of(domain));

        // Act
        var result = service.getById(1L);

        // Assert
        verify(persistence).findById(id);
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(id);
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.email()).isEqualTo("j.doe@example.com");
    }

    @Test
    void getById_whenUserDoesNotExist_shouldThrowUserNotFoundException() {
        // Arrange
        Long id = 999L;
        when(persistence.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.getById(id))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(String.valueOf(id));
        verify(persistence).findById(id);
        verifyNoMoreInteractions(persistence);
    }

    @Test
    void create_shouldSaveUserAndReturnUserDto() {
        // Arrange
        var command = new CreateUserCommand("John Doe", "j.doe@example.com");
        var domain = new User(1L, "John Doe", "j.doe@example.com");
        when(persistence.save(any(User.class))).thenReturn(domain);

        // Act
        var result = service.create(command);

        // Assert
        verify(persistence).save(argThat(u -> u.getName().equals("John Doe") && u.getEmail().equals("j.doe@example.com")));
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(1L);
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.email()).isEqualTo("j.doe@example.com");
    }

    @Test
    void create_whenEmailExists_shouldThrowUserAlreadyExistsException() {
        // Arrange
        var command = new CreateUserCommand("Jane Doe", "jane.doe@example.com");
        var existing = new User(2L, "Jane Doe", "jane.doe@example.com");
        when(persistence.findByEmail(command.email())).thenReturn(Optional.of(existing));

        // Act & Assert
        assertThatThrownBy(() -> service.create(command))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining(command.email());

        verify(persistence).findByEmail(command.email());
        verifyNoMoreInteractions(persistence);
    }

    @Test
    void update_whenUserExists_shouldUpdateAndReturnUserDto() {
        // Arrange
        Long id = 1L;
        var command = new CreateUserCommand("Updated Name", "updated@example.com");
        var existing = new User(id, "Old Name", "old@example.com");
        var updated = new User(id, "Updated Name", "updated@example.com");
        when(persistence.findById(id)).thenReturn(Optional.of(existing));
        when(persistence.save(any(User.class))).thenReturn(updated);

        // Act
        var result = service.update(id, command);

        // Assert
        verify(persistence).findById(id);
        verify(persistence).save(argThat(u -> u.getId().equals(id) && u.getName().equals("Updated Name") && u.getEmail().equals("updated@example.com")));
        verifyNoMoreInteractions(persistence);
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(id);
        assertThat(result.name()).isEqualTo("Updated Name");
        assertThat(result.email()).isEqualTo("updated@example.com");
    }

    @Test
    void update_whenUserDoesNotExist_shouldThrowUserNotFoundException() {
        // Arrange
        Long id = 999L;
        var command = new CreateUserCommand("Name", "email@example.com");
        when(persistence.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThatThrownBy(() -> service.update(id, command))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(String.valueOf(id));

        verify(persistence).findById(id);
        verifyNoMoreInteractions(persistence);
    }

    @Test
    void delete_shouldCallPersistenceDelete() {
        // Arrange
        Long id = 1L;

        // Act
        service.delete(id);

        // Assert
        verify(persistence).delete(id);
        verifyNoMoreInteractions(persistence);
    }
}
