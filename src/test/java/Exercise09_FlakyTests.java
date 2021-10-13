import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise09_FlakyTests {

    @Disabled // TODO remove me
    @RepeatedTest(100)
    void flaky() {
        assertEquals(0.0, Math.random(), 0.9);
    }

}
