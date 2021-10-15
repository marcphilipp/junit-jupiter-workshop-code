import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.platform.testkit.engine.EngineTestKit;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.testkit.engine.EventConditions.event;
import static org.junit.platform.testkit.engine.EventConditions.finishedWithFailure;
import static org.junit.platform.testkit.engine.TestExecutionResultConditions.message;

public class Exercise22_TestKitTests {

    @Test
    void extensionInjectsTheAnswerToTheUltimateQuestion() {
        EngineTestKit
                .engine("junit-jupiter")
                .selectors(selectClass(TestCase.class))
                .execute()
                .testEvents()
                .assertThatEvents()
                .haveExactly(1, event(finishedWithFailure(message("The answer is 42"))));
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
