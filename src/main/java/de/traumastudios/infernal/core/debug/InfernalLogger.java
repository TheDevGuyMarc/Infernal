package de.traumastudios.infernal.core.debug;

import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;
import org.tinylog.configuration.Configuration;
import org.tinylog.writers.ConsoleWriter;
import org.tinylog.writers.RollingFileWriter;

import java.nio.file.Path;
import java.nio.file.Paths;

@Getter
@Setter
public class InfernalLogger {
    private String logFileName;
    private static InfernalLogger instance;

    private InfernalLogger(String logFileName) {
        this.logFileName = logFileName;
        initializeLogger();
    }

    public static InfernalLogger getInstance(String logFileName) {
        if (instance == null) {
            instance = new InfernalLogger(logFileName);
        }
        return instance;
    }

    private void initializeLogger() {
        // Configure Tinylog for console logging
        Configuration.set("writer", "console");
        Configuration.set("writer.console", ConsoleWriter.class.getName());
        Configuration.set("writer.console.format", "{date} [{thread}] {level}: {message}");

        // Configure Tinylog for file logging
        Path logFilePath = Paths.get(logFileName).toAbsolutePath();
        Configuration.set("writer2", "file");
        Configuration.set("writer2.file", RollingFileWriter.class.getName());
        Configuration.set("writer2.file.file", logFilePath.toString());
        Configuration.set("writer2.file.format", "{date} [{thread}] {level}: {message}");
    }

    public void info(String message) {
        Logger.info(message);
    }

    public void warning(String message) {
        Logger.warn(message);
    }

    public void error(String message) {
        Logger.error(message);
    }

    public void error(String message, Throwable throwable) {
        Logger.error(throwable, message);
    }

    public void debug(String message) {
        Logger.debug(message);
    }

    public void trace(String message) {
        Logger.trace(message);
    }

    public void logCustomLevel(String level, String message) {
        Logger.info(level, message);
    }
}