package de.traumastudios.infernal.platform.primitives;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ScopedBuffer<T> {
    private List<T> buffer;
    private int maxSize;

    public ScopedBuffer(int maxSize) {
        this.buffer = new ArrayList<>();
        this.maxSize = maxSize;
    }

    public void add(T item) {
        if (buffer.size() < maxSize) {
            buffer.add(item);
        } else {
            System.out.println("Buffer is full. Cannot add more items.");
        }
    }

    public void clearBuffer() {
        buffer.clear();
    }

    public boolean isFull() {
        return buffer.size() == maxSize;
    }
}
