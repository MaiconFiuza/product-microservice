package com.fiuza.fiap.product.core.dto;

import com.fiuza.fiap.product.core.dto.errors.AlreadyExistExceptionDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlreadyExistExceptionDtoTest {

    @Test
    void testRecordFields() {
        String expectedMessage = "Produto já está cadastrado";
        int expectedStatus = 400;

        AlreadyExistExceptionDto dto = new AlreadyExistExceptionDto(expectedMessage, expectedStatus);

        assertEquals(expectedMessage, dto.message());
        assertEquals(expectedStatus, dto.status());
    }
}
