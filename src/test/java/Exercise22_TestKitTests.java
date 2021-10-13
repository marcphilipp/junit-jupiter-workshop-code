import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static org.junit.jupiter.api.Assertions.fail;

public class Exercise22_TestKitTests {

    @Test
    void extensionInjectsTheAnswerToTheUltimateQuestion() {
        // TODO use EngineTestKit to write me
    }

    static class TestCase {
        @Test
        @ExtendWith(DeepThought.class)
        void test(int theAnswer) {
            fail("The answer is " + theAnswer);
        }
    }

    static class DeepThought implements ParameterResolver {
        @Override
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return parameterContext.getParameter().getType().equals(int.class);
        }

        @Override
        public Integer resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
            return 42;
        }
    }
}
