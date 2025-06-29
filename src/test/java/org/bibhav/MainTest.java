package org.bibhav;

import org.bibhav.exception.ApplicationException;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void main_justTestAppInvocationWithOutAsserts() throws ApplicationException {
        String[] args = new String[]{"src/main/resources/more_data.csv"};
        Main.main(args);
    }
}