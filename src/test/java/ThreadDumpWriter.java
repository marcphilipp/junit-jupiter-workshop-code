import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

import java.io.BufferedWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeoutException;

public class ThreadDumpWriter implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable instanceof TimeoutException) {
            ThreadInfo[] threadInfos = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
            Path threadDump = Files.createTempFile(Paths.get("target"), "thread-dump-", ".txt");
            try (BufferedWriter out = Files.newBufferedWriter(threadDump)) {
                for (ThreadInfo threadInfo : threadInfos) {
                    out.write(threadInfo.toString());
                }
            }
            TimeoutException timeoutException = new TimeoutException("Thread dump written to " + threadDump);
            timeoutException.initCause(throwable);
            throw timeoutException;
        }
        throw throwable;
    }
}
