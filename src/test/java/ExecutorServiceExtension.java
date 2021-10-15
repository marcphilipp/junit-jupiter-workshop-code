import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ExtensionContext.Store.CloseableResource;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.support.TypeBasedParameterResolver;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExecutorServiceExtension extends TypeBasedParameterResolver<ExecutorService> {

    private static final Namespace NAMESPACE = Namespace.create(ExecutorServiceExtension.class);

    private final Supplier<ExecutorService> factory;

    @SuppressWarnings("unused") // Used by JUnit via @ExtendWith
    public ExecutorServiceExtension() {
        this(Executors::newCachedThreadPool);
    }

    public ExecutorServiceExtension(Supplier<ExecutorService> factory) {
        this.factory = factory;
    }

    @Override
    public ExecutorService resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return extensionContext.getStore(NAMESPACE)
                .getOrComputeIfAbsent("executorService", __ -> new ExecutorServiceResource(factory.get()), ExecutorServiceResource.class)
                .executorService;
    }

    record ExecutorServiceResource(ExecutorService executorService) implements CloseableResource {
        @Override
        public void close() throws Throwable {
            executorService.shutdownNow();
            boolean terminated = executorService.awaitTermination(5, SECONDS);
            assertTrue(terminated, "ExecutorService did not shut down within timeout of 5 seconds");
        }
    }
}
