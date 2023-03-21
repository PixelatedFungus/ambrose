package lusaris;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;

public class AionTests {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void runAllTests() {
        assert(true);
    }

    @Test
    @DisplayName("Basic Tests")
    public void basicTest() {
        String[] args = new String[1];
        args[0] = "schedule";
        Main.main(args);
    }

    @BeforeEach
    public void beforeAll() {
//        System.setOut();
    }
}
