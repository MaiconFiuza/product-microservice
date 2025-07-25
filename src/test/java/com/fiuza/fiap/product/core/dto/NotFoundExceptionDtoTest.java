package com.fiuza.fiap.product.core.dto;

import com.fiuza.fiap.product.core.dto.errors.NotFoundExceptionDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NotFoundExceptionDtoTest {
    @Test
    void testRecordFields() {
        String expectedMessage = "Recurso não encontrado";
        int expectedStatus = 404;

        NotFoundExceptionDto dto = new NotFoundExceptionDto(expectedMessage, expectedStatus);

        assertEquals(expectedMessage, dto.message());
        assertEquals(expectedStatus, dto.status());
    }
}
