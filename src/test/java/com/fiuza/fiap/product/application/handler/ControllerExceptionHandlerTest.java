package com.fiuza.fiap.product.application.handler;

import com.fiuza.fiap.product.application.handlers.ControllerExceptionHandler;
import com.fiuza.fiap.product.core.dto.errors.InternalServerErrorDto;
import com.fiuza.fiap.product.core.dto.errors.NotFoundExceptionDto;
import com.fiuza.fiap.product.core.exceptions.InternalServerError;
import com.fiuza.fiap.product.core.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerExceptionHandlerTest {

    private ControllerExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new ControllerExceptionHandler();
    }

    @Test
    void shouldHandleNotFoundException() {
        String message = "Produto não encontrado";
        NotFoundException ex = new NotFoundException(message);

        ResponseEntity<NotFoundExceptionDto> response = handler.handlerNotFoundException(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals(message, response.getBody().message());
        assertEquals(404, response.getBody().status());
    }

    @Test
    void shouldHandleInternalServerError() {
        String message = "Erro interno ao remover";
        InternalServerError ex = new InternalServerError(message);

        ResponseEntity<InternalServerErrorDto> response = handler.handlerInternalServerErrorException(ex);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals(message, response.getBody().message());
        assertEquals(500, response.getBody().status());
    }

}
