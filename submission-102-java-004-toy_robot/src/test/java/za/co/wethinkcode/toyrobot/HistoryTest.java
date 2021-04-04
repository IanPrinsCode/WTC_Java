package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HistoryTest {
    History history = new History();

    @BeforeEach
    void clearHistory() {
        History.clearHistory();
    }

    @Test
    void appendRight() {
        history.append("sprint 5");
        assertTrue(History.getHistory().size() > 0);
    }

    @Test
    void appendWrong() {
        history.append("jump");
        assertEquals(0, History.getHistory().size());
    }

    @Test
    void appendMultiple() {
        history.append("forward 20");
        history.append("back 10");
        history.append("right");
        history.append("help");
        history.append("sprint 7");
        history.append("sleep loud");
        assertEquals(4, History.getHistory().size());
    }
}
