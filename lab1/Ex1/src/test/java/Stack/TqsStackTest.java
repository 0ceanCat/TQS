package Stack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author wy
 * @date 2021/3/16 9:53
 */
class TqsStackTest {
    private TqsStack<String> emptyStack;
    private TqsStack<String> stack3elems;
    private TqsStack<String> boundedStack;

    @BeforeEach
    void setUp() {
        emptyStack = new TqsStack<>();
        stack3elems = new TqsStack<>();
        boundedStack = new TqsStack<>(3);

        stack3elems.push("Aveiro");
        stack3elems.push("Braga");
        stack3elems.push("Lisboa");

        boundedStack.push("Aveiro");
        boundedStack.push("Braga");
        boundedStack.push("Lisboa");
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("For bounded stacks only," +
            " pushing onto a full stack does throw an IllegalStateException")
    @Test
    void pushWhenFull() {
        assertThrows(IllegalStateException.class, () -> boundedStack.push("new"));
    }

    @DisplayName("If one pushes x then pops, the value popped is x.")
    @Test
    void pop() {
        stack3elems.push("Porto");

        assertEquals("Porto", stack3elems.pop(), "popped value is not the last pushed");
    }

    @DisplayName("If the size is n, then after n pops," +
            " the stack is empty and has a size 0.")
    @Test
    void popUtilEmpty() {
        for (int i = 0; i < 3; i++) {
            stack3elems.pop();
        }

        assertEquals(0, stack3elems.size(), "After n (size of the stack) pops, the size is not zero");
        assertTrue(stack3elems.isEmpty(), "After n (size of the stack) pops, the size is zero, but the method isEmpty() returned false");
    }

    @DisplayName("Popping from an empty stack does throw a NoSuchElementException.")
    @Test
    void popWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> emptyStack.pop());
    }

    @DisplayName("Peeking into an empty stack does throw a NoSuchElementException.")
    @Test
    void peekWhenEmpty() {
        assertThrows(NoSuchElementException.class, () -> emptyStack.peek());
    }

    @DisplayName("If one pushes x then peeks, the value returned is x," +
            " but the size stays the same")
    @Test
    void peek() {
        String newElem = "UA";
        stack3elems.push(newElem);
        int size = stack3elems.size();

        assertEquals(newElem, stack3elems.peek());
        assertEquals(size, stack3elems.size());
    }

    @DisplayName("A stack has size 0 on construction.")
    @Test
    void size() {
        assertEquals(0, emptyStack.size());
    }

    @DisplayName("A stack is empty on construction.")
    @Test
    void isEmpty() {
        assertTrue(emptyStack.isEmpty());
    }

    @DisplayName("After n pushes to an empty stack, n > 0," +
            " the stack is not empty and its size is n")
    @Test
    void hasElements() {
        int n = 10;
        for (int i = 0; i < 10; i++) {
            emptyStack.push("" + i);
        }

        assertEquals(n, emptyStack.size());
    }
}