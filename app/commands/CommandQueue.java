package commands;

import java.util.ArrayDeque;
import java.util.Queue;

public class CommandQueue {

    private final Queue<Runnable> queue = new ArrayDeque<>();
    private boolean processing = false;

    public void enqueue(Runnable command) {
        queue.add(command);
    }

    public void processAll() {
        if (processing) return;
        processing = true;
        try {
            while (!queue.isEmpty()) {
                queue.poll().run();
            }
        } finally {
            processing = false;
        }
    }

    public int size() {
        return queue.size();
    }
}
