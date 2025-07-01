package org.bibhav.util;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class to test functions of print utility.
 *
 * @author BibhavKumar
 */
class MessageFormatUtilityTest {


    @Test
    void printMapWithProperInformation_emptyMap() {
        assertEquals("No Manager(s) found with the given condition.", MessageFormatUtility.formMessage("Manager", Map.of(), "earns less than they should, and by"));
    }

    @Test
    void printMapWithProperInformation_nonEmptyMap() {
        assertEquals("Manager with id [123] earns less than they should, and by [100.0].", MessageFormatUtility.formMessage("Manager", Map.of(123L, 100.0D), "earns less than they should, and by"));
    }
}