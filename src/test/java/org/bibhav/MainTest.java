package org.bibhav;

import org.bibhav.exception.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for Main.
 * <p>
 * This class contains unit tests for the Main class, ensuring that it correctly handles invalid input scenarios.
 * It uses JUnit 5 for testing and includes assertions to verify expected outcomes.
 * </p>
 *
 * @author BibhavKumar
 */
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