package de.traumastudios.infernal.core.serialization;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JSONSerializer extends Serializer {
    private static final Gson gson = new Gson();

    /**
     * Read JSON files
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    @Override
    public List<String[]> deserialize(String filePath) throws IOException {
        String content = FileUtil.readTextFile(filePath);

        // Using Gson to parse JSON content to List<String[]>
        Type listType = new TypeToken<List<String[]>>(){}.getType();
        return gson.fromJson(content, listType);
    }

    /**
     * Write JSON files
     * @param data
     * @param filePath
     * @throws IOException
     */
    @Override
    public void serialize(List<String[]> data, String filePath) throws IOException {
        String json = gson.toJson(data);
        FileUtil.writeTextFile(filePath, json);
    }
}
