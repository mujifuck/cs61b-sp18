import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    @Test
    public void testOffByN() {
        OffByN obn5 = new OffByN(5);
        assertTrue(obn5.equalChars('a','f'));
        assertTrue(obn5.equalChars('f','a'));
        assertFalse(obn5.equalChars('h','f'));
        assertFalse(obn5.equalChars('a','c'));
    }
}
