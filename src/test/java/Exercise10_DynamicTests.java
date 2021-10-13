import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class Exercise10_DynamicTests {

    @TestFactory
    Stream<DynamicNode> evenNumbersAreDivisibleByTwo() {
        return evenNumbers(0, 100)
                .mapToObj(n -> dynamicContainer(
                        "n = " + n,
                        evenNumbers(n, 10).mapToObj(m -> dynamicTest(
                                "(n + " + m + ") is even",
                                () -> assertEquals(0, n % 2)
                        ))
                ));
    }

    private IntStream evenNumbers(int seed, int limit) {
        return IntStream.iterate(seed, n -> n + 2).limit(limit);
    }

}
