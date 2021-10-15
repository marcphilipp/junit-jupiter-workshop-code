import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.ClearSystemProperty;
import org.junitpioneer.jupiter.SetSystemProperty;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise20_SystemPropertiesTests {

    static final String KEY = "exercise-20";

    @Test
    @SetSystemProperty(key = KEY, value = "value")
    void canGetPreviouslySetProperty() {
        assertEquals("value", System.getProperty(KEY));
    }

    @Test
    @ClearSystemProperty(key = KEY)
    void usesDefaultValueIfPropertyIsNotSet() {
        assertEquals("defaultValue", System.getProperty(KEY, "defaultValue"));
    }
}
