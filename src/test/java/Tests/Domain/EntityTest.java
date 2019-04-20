package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntityTest {

    @Test
    void getShouldReturnCorrectId() {
        Entity e = new Entity(1);
        assertEquals(1, e.getId(), String.format("Returned %d, expected=%d", e.getId(),1));
        Entity e1 = new Entity(2);
        assertEquals(2, e1.getId(), String.format("Returned %d, expected=%d", e1.getId(),2));
    }

    void getSetCorrectId() {
        Entity e = new Entity(1);
        e.setId(2);
        assertEquals(2, e.getId(), String.format("Returned %d, expected=%d", e.getId(),2));
    }

    @Test
    void toStringShouldReturnALongEnoughString() {
        Entity e = new Entity(1);
        assertTrue(e.toString().length() > 5);
    }

}