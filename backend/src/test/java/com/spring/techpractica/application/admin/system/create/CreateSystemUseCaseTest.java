package com.spring.techpractica.application.admin.system.create;

import com.spring.techpractica.core.shared.Exception.ResourcesDuplicateException;
import com.spring.techpractica.core.system.SystemFactory;
import com.spring.techpractica.core.system.SystemRepository;
import com.spring.techpractica.core.system.entity.System; // انتبه لتعارض الاسم
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateSystemUseCaseTest {

    @InjectMocks
    private CreateSystemUseCase underTest;

    @Mock
    private SystemRepository systemRepository;

    @Mock
    private SystemFactory systemFactory;

    @Test
    @DisplayName("should create system when name does not exist")
    void shouldCreateSystemWhenNameNotExists() {
        // Arrange
        String name = "system";
        CreateSystemCommand command = new CreateSystemCommand(name);

        when(systemRepository.existsByName(name)).thenReturn(false);

        System systemToSave = System.builder()
                .name(name)
                .build();

        when(systemFactory.create(name)).thenReturn(systemToSave);
        when(systemRepository.save(systemToSave)).thenReturn(systemToSave);

        // Act
        System result = underTest.execute(command);

        // Assert
        assertNotNull(result);
        assertEquals(name, result.getName());

        verify(systemRepository).existsByName(name);
        verify(systemFactory).create(name);
        verify(systemRepository).save(systemToSave);
        verifyNoMoreInteractions(systemRepository, systemFactory);
    }

    @Test
    @DisplayName("should throw if system with same name already exists")
    void shouldThrowWhenSystemAlreadyExists() {
        // Arrange
        String name = "system";
        CreateSystemCommand command = new CreateSystemCommand(name);

        when(systemRepository.existsByName(name)).thenReturn(true);

        // Act & Assert
        ResourcesDuplicateException ex = assertThrows(
                ResourcesDuplicateException.class,
                () -> underTest.execute(command)
        );
        assertTrue(ex.getMessage().toLowerCase().contains("system"));

        verify(systemRepository).existsByName(name);
        verifyNoInteractions(systemFactory);
        verifyNoMoreInteractions(systemRepository);
    }
}
