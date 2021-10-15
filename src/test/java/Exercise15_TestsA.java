import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Exercise15_TestsA {

    @RegisterExtension
    Extension extension = new ExecutorServiceExtension(Executors::newSingleThreadExecutor);

    @Test
    void runsOnDifferentThread(ExecutorService executorService) throws Exception {
        Future<Thread> future = executorService.submit(Thread::currentThread);

        assertNotEquals(Thread.currentThread(), future.get());
    }
}
