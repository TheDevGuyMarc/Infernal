package de.traumastudios.infernal.core.serialization;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileUtil {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    /**
     * Read text files
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readTextFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        // Using buffered reader for efficient reading
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }

    /**
     * Write text files
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void writeTextFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        // Using buffered writer for efficient writing
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(content);
        }
    }

    /**
     * Read binary data files
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readBinaryFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        // Using buffered input stream for efficient reading of binary data
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(path))) {
            return inputStream.readAllBytes();
        }
    }

    /**
     * Write binary data files
     * @param filePath
     * @param data
     * @throws IOException
     */
    public static void writeBinaryFile(String filePath, byte[] data) throws IOException {
        Path path = Paths.get(filePath);
        // Using buffered output stream for efficient writing of binary data
        try (BufferedOutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(path))) {
            outputStream.write(data);
        }
    }

    public static ByteBuffer ioResourceToByteBuffer(String resource) throws IOException {
        return ioResourceToByteBuffer(resource, DEFAULT_BUFFER_SIZE);
    }

    public static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;

        try (FileChannel fc = FileChannel.open(Path.of(resource), StandardOpenOption.READ)) {
            long fileSize = fc.size();
            buffer = ByteBuffer.allocateDirect((int) fileSize);

            while (fc.position() < fileSize) {
                int bytesRead = fc.read(buffer);
                if (bytesRead == -1) {
                    break; // End of file
                }
            }

            buffer.flip();
        } catch (IOException e) {
            throw new IOException("Failed to read resource: " + resource, e);
        }

        return buffer;
    }
}
