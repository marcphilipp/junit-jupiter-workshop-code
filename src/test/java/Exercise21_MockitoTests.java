import org.junit.jupiter.api.Test;

import java.net.URI;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Exercise21_MockitoTests {

    @Test
    void testUsingMocks() {
        Path mock = mock(Path.class);

        URI uri = URI.create("file:///root");
        when(mock.toUri()).thenReturn(uri);

        assertEquals(uri, mock.toUri());
    }
}
