package org.bibhav.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to test functions of print utility.
 *
 * @author BibhavKumar
 */
class PrintUtilityTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void printMapWithProperInformation_emptyMap() {
        PrintUtility.printMapWithProperInformation("Manager", Map.of(), "earns less than they should, and by");
        assertEquals("No Manager(s) found with the given condition.", outputStreamCaptor.toString().trim());
    }

    @Test
    void printMapWithProperInformation_nonEmptyMap() {
        PrintUtility.printMapWithProperInformation("Manager", Map.of(123L, 100.0D), "earns less than they should, and by");
        assertEquals("Manager with id [123] earns less than they should, and by [100.0].", outputStreamCaptor.toString().trim());
    }
}