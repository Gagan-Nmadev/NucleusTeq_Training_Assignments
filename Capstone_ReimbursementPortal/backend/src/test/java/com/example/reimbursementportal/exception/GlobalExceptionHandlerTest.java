package com.example.reimbursementportal.exception;

import com.example.reimbursementportal.dto.ErrorResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for GlobalExceptionHandler.
 *
 * We test centralized exception handling:
 * - ResourceNotFoundException
 * - BusinessException
 * - Validation exception
 *
 * This ensures APIs return proper status code and error response body.
 */
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    /**
     * Test response when requested resource is not found.
     */
    @Test
    void handleResourceNotFound_shouldReturnNotFoundStatus() {
        ResourceNotFoundException exception =
                new ResourceNotFoundException("User not found");

        ResponseEntity<ErrorResponseDto> response =
                exceptionHandler.handleResourceNotFound(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(404);
        assertThat(response.getBody().getMessage()).isEqualTo("User not found");
    }

    /**
     * Test response when business rule validation fails.
     */
    @Test
    void handleBusinessException_shouldReturnBadRequestStatus() {
        BusinessException exception =
                new BusinessException("Email already exists");

        ResponseEntity<ErrorResponseDto> response =
                exceptionHandler.handleBusinessException(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Email already exists");
    }

    /**
     * Test response when @Valid validation fails in request DTO.
     */
    @Test
    void handleValidationException_shouldReturnBadRequestStatus() {
        MethodArgumentNotValidException exception =
                mock(MethodArgumentNotValidException.class);

        BindingResult bindingResult = mock(BindingResult.class);

        FieldError fieldError = new FieldError(
                "userRequestDto",
                "email",
                "Enter a valid email"
        );

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        ResponseEntity<ErrorResponseDto> response =
                exceptionHandler.handleValidationException(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getMessage()).isEqualTo("Enter a valid email");
    }
}