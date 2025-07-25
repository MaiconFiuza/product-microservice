package com.fiuza.fiap.product.core.dto;

import com.fiuza.fiap.product.core.dto.errors.InternalServerErrorDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InternalServerErrorDtoTest {
    @Test
    void testRecordFields() {
        String expectedMessage = "Erro interno no servidor";
        int expectedStatus = 500;

        InternalServerErrorDto dto = new InternalServerErrorDto(expectedMessage, expectedStatus);

        assertEquals(expectedMessage, dto.message());
        assertEquals(expectedStatus, dto.status());
    }
}
