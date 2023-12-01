package de.traumastudios.infernal.core.serialization;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVSerializer extends Serializer {
    /**
     * Read CSV files
     *
     * @param filePath
     * @return
     * @throws IOException
     * @throws CsvException
     */
    @Override
    public List<String[]> deserialize(String filePath) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            return reader.readAll();
        }
    }

    /**
     * Write CSV files
     * @param data
     * @param filePath
     * @throws IOException
     */
    @Override
    public void serialize(List<String[]> data, String filePath) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(data);
        }
    }
}
