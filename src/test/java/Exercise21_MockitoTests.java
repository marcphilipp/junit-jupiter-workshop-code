import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Exercise21_MockitoTests {

    @Mock Path mock;

    @Test
    void testUsingMocks() {
        URI uri = URI.create("file:///root");
        when(mock.toUri()).thenReturn(uri);

        assertEquals(uri, mock.toUri());
    }
}
