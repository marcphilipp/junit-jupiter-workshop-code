import org.immutables.value.Value.Immutable;
import org.immutables.value.Value.Style;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.MessageFormat;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Exercise08_MathTests {

    @ParameterizedTest(name = "sqrt({0}) = {1}")
    @CsvSource({
            " 1, 1.00",
            " 2, 1.41",
            " 4, 2.00",
            "16, 4.00",
    })
    void sqrt(double input, double expectedOutput) {
        assertEquals(expectedOutput, Math.sqrt(input), 0.01);
    }

    @ParameterizedTest(name = "floor({0}) = {1}")
    @CsvSource({
            " 1.00, 1",
            " 1.99, 1"
    })
    void floorWithCsvSource(double input, int expectedOutput) {
        assertEquals(expectedOutput, Math.floor(input), 0.01);
    }

    @ParameterizedTest(name = "floor({0}) = {1}")
    @MethodSource("floorTuples")
    void floorWithMethodSource(double input, int expectedOutput) {
        assertEquals(expectedOutput, Math.floor(input), 0.01);
    }

    public static Stream<Arguments> floorTuples() {
        return Stream.of(
                arguments(1.00, 1),
                arguments(1.99, 1),
                arguments(-0.5, -1)
        );
    }

    // --------------------------------------------------------------------------------------------------------------

    @ParameterizedTest
    @MethodSource("floorArgs")
    void floorWithMethodSourceWithFloorArgs(FloorArgs args) {
        assertEquals(args.expectedOutput, Math.floor(args.input), 0.01);
    }

    public static Stream<FloorArgs> floorArgs() {
        return Stream.of(
                new FloorArgs(1.00, 1),
                new FloorArgs(1.99, 1),
                new FloorArgs(-0.5, -1)
        );
    }

    record FloorArgs(double input, int expectedOutput) {}

    // --------------------------------------------------------------------------------------------------------------

    @ParameterizedTest
    @MethodSource("immutableFloorArgs")
    void floorWithMethodSourceWithImmutableFloorArgs(FloorArgs2 args) {
        assertEquals(args.expectedOutput(), Math.floor(args.input()), 0.01);
    }

    public static Stream<Object> immutableFloorArgs() {
        return Stream.of(
                ImmutableFloorArgs2.of(1.00, 1),
                ImmutableFloorArgs2.of(1.99, 1),
                ImmutableFloorArgs2.builder().input(-0.5).expectedOutput(-1).build()
        );
    }

    @Immutable
    @Style(allParameters = true)
    abstract static class FloorArgs2 {

        abstract double input();

        abstract int expectedOutput();

        @Override
        public String toString() {
            return MessageFormat.format("floor({0}) = {1}", input(), expectedOutput());
        }
    }
}
