import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

//@TestMethodOrder(Random.class)
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(PER_CLASS)
@Tag("exercise-4")
@DisplayName("A list")
class Exercise01_ListTests {

    private List<String> list;

    @BeforeEach
    void createArrayList() {
        list = new ArrayList<>();
    }

    @Test
    @Order(1)
    void is_empty_after_creation() {
        assertTrue(list.isEmpty());
    }

    @Test
    @Order(2)
    void throws_exception_when_getting_missing_element() {
        assertThrows(Exception.class, () -> list.get(0));
    }

    @Test
    @Order(3)
    void has_size_of_one_after_adding_an_element() {
        list.add("item");

        assertSame("item", list.get(0));
        assertEquals(1, list.size());
    }

}
