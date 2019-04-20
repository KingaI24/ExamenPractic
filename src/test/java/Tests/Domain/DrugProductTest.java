package Domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrugProductTest {

    @Test
    void getShouldReturnCorrectId() {
        DrugProduct drugProduct = new DrugProduct(1, "Faringosept", "Terapia", 12.24, false);
        assertEquals(1, drugProduct.getId(), String.format("Returned %d, expected=%d", drugProduct.getId(),1));
    }

    @Test
    void getShouldReturnCorrectName() {
        DrugProduct drugProduct = new DrugProduct(1, "Faringosept", "Terapia", 12.24, false);
        assertEquals("Faringosept", drugProduct.getName(), String.format("Returned %s, expected=%s", drugProduct.getName(), "Faringosept"));
        DrugProduct drugProduct1 = new DrugProduct(2, "test", "test", 12.42, false);
        assertEquals("test", drugProduct1.getName(), String.format("Returned %s, expected=%s", drugProduct1.getName(),"test"));
    }

    @Test
    void getShouldReturnCorrectManufacturer() {
        DrugProduct drugProduct = new DrugProduct(1, "Faringosept", "Terapia", 12.24, false);
        assertEquals("Terapia", drugProduct.getManufacturer(), String.format("Returned %s, expected=%s", drugProduct.getManufacturer(),"Terapia"));
        DrugProduct drugProduct1 = new DrugProduct(1, "test", "test", 12.42, false);
        assertEquals("test", drugProduct1.getManufacturer(), String.format("Returned %s, expected=%s", drugProduct1.getManufacturer(),"test"));
    }

    @Test
    void getShouldReturnCorrectPrice() {
        DrugProduct drugProduct = new DrugProduct(1, "Faringosept", "Terapia", 12.24, false);
        assertEquals(12.24, drugProduct.getPrice(), String.format("Returned %s, expected=%f", drugProduct.getPrice(),12.24));
        DrugProduct drugProduct1 = new DrugProduct(1, "test", "test", 12.42, false);
        assertEquals(12.42, drugProduct1.getPrice(), String.format("Returned %s, expected=%f", drugProduct1.getPrice(),12.42));
    }

    @Test
    void toStringShouldReturnALongEnoughString() {
        DrugProduct dp = new DrugProduct(1, "Faringosept", "Terapia", 12.24, false);
        assertTrue(dp.toString().length() > 10);
    }

    @org.junit.jupiter.api.Test
    void equalityShouldWorkCorrectly() {
        DrugProduct dp1 = new DrugProduct(1, "test", "test", 10, false);
        DrugProduct dp2 = new DrugProduct(1, "test", "test", 10, false);
        DrugProduct dp3 = new DrugProduct(3, "test", "test", 10, true);

        assertNotEquals(dp1, dp3);
        assertNotEquals(dp3, dp1);
        assertNotEquals(dp3, dp2);
        assertNotEquals(dp2, dp3);
        assertEquals(dp1, dp2);
        assertEquals(dp2, dp1);
    }

    @org.junit.jupiter.api.Test
    void settersShouldSetFieldsCorrectly() {
        DrugProduct dp = new DrugProduct(1, "test", "test", 10.20, false);

        dp.setId(1);
        dp.setName("test");
        dp.setManufacturer("test");
        dp.setPrice(10.20);
        dp.setNeedPresciption(false);


        assertEquals(1, dp.getId());
        assertEquals("test", dp.getName());
        assertEquals("test", dp.getManufacturer());
        assertEquals(10.20, dp.getPrice());
        assertFalse(dp.isNeedPresciption());
    }

    @org.junit.jupiter.api.Test
    void constructorShouldSetAllFieldsCorrectly() {
        DrugProduct dp = new DrugProduct(1, "test", "test", 10.20, false);
        assertEquals(1, dp.getId());
        assertEquals("test", dp.getName());
        assertEquals("test", dp.getManufacturer());
        assertEquals(10.20, dp.getPrice());
        assertFalse(dp.isNeedPresciption());
    }
}