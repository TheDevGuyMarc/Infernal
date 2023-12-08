package de.traumastudios.infernal.platform.primitives;

import de.traumastudios.infernal.core.debug.InfernalLogger;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ScopedBuffer<T> {
    private static final InfernalLogger logger = InfernalLogger.getInstance("primitives.log");
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
            logger.warning("Buffer is full. Cannot add more items.");
        }
    }

    public void clearBuffer() {
        buffer.clear();
    }

    public boolean isFull() {
        return buffer.size() == maxSize;
    }
}
