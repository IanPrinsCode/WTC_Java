package za.co.wethinkcode.mastermind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CodeGeneratorTest {

    @Test
    void testCodeLength() {
        CodeGenerator generator = new CodeGenerator();

        assertEquals(4, generator.generateCode().length());
    }

    @Test
    void testCodeRange() {
        CodeGenerator generator = new CodeGenerator();
        String code = generator.generateCode();

        for (int i = 0; i < 4; i++) {
            int value = Character.getNumericValue(code.charAt(i));
            assertTrue( 0 < value && value < 9);
        }
    }
}