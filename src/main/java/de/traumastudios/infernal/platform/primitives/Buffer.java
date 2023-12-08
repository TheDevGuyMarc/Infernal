package de.traumastudios.infernal.platform.primitives;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Buffer {
    private byte[] data;
    private int size;

    public Buffer(int size) {
        this.data = new byte[size];
        this.size = size;
    }
}
