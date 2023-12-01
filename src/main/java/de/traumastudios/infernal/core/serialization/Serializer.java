package de.traumastudios.infernal.core.serialization;

import java.io.IOException;
import java.util.List;

public abstract class Serializer {
    public abstract List<String[]> deserialize(String filePath) throws IOException, Exception;
    public abstract void serialize(List<String[]> data, String filePath) throws IOException;
}
