import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@Execution(ExecutionMode.CONCURRENT)
public class Exercise11_ParallelTestExecutionTests {

    @Disabled // TODO remove me
    @TestFactory
    Stream<DynamicTest> lotsOfTests() {
        return IntStream.range(0, 100)
                .mapToObj(n -> dynamicTest("n = " + n, () -> {
                    Thread.sleep(1000);
                }));
    }

}
