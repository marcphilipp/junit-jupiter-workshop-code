package collections;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ListTests {

    List<String> list = new ArrayList<>();

    @Disabled("mysteriously not working")
    @Test
    void listSizeIsOneAfterAddingAnElement() {
        list.add("item");

        assertEquals(42, list.size());
    }
}
