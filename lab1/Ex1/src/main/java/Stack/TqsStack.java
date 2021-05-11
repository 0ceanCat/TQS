package Stack;

import java.util.LinkedList;

/**
 * @author wy
 * @date 2021/3/16 9:51
 */
public class TqsStack<T> {
    private final LinkedList<T> stack;
    private int bound;

    public TqsStack() {
        stack = new LinkedList<>();
        this.bound = -1;
    }

    public TqsStack(int bound) {
        this();
        this.bound = bound;
    }

    public void push(T elem) {
        if (bound != -1 && stack.size() == bound) {
            throw new IllegalStateException();
        }
        stack.addFirst(elem);
    }

    public T pop() {
        return stack.removeFirst();
    }

    public T peek() {
        return stack.getFirst();
    }

    public int size() {
        return stack.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
