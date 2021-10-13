import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise20_SystemPropertiesTests {

    static final String KEY = "exercise-20";

    @BeforeEach
    @AfterEach
    void resetSystemProperty() {
        System.clearProperty(KEY);
    }

    @Test
    void usesDefaultValueIfPropertyIsNotSet() {
        assertEquals("defaultValue", System.getProperty(KEY, "defaultValue"));
    }

    @Test
    void canGetPreviouslySetProperty() {
        System.setProperty(KEY, "value");

        assertEquals("value", System.getProperty(KEY));
    }
}
