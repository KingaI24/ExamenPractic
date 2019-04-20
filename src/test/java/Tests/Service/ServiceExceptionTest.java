package Service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceExceptionTest {

    @Test
    void exceptionServiceShouldReturnMessage(){
        ServiceException ex = new ServiceException("Test");
        assertEquals("Test", ex.getMessage());
    }
}
