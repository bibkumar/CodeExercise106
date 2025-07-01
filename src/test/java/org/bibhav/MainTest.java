package org.bibhav;

import org.bibhav.exception.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void testMainWithInValidInputEmptyArg() {
        String[] args = new String[]{""};
        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> Main.main(args));
        Assertions.assertEquals("Data file path is required as an argument.", badRequestException.getMessage());
    }

    @Test
    void testMainWithInValidInputNoArg() {
        String[] args = new String[]{};
        BadRequestException badRequestException = Assertions.assertThrows(BadRequestException.class, () -> Main.main(args));
        Assertions.assertEquals("Data file path is required as an argument.", badRequestException.getMessage());
    }
}