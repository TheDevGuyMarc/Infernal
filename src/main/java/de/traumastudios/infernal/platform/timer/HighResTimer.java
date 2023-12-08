package de.traumastudios.infernal.platform.timer;

import de.traumastudios.infernal.core.debug.InfernalLogger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class HighResTimer {
    private static final InfernalLogger logger = InfernalLogger.getInstance("time.log");
    private long start;
    private long lastTime;
    private int frames;
    private int updates;

    public HighResTimer() {
        this.start = System.nanoTime();
        this.lastTime = this.start;
        this.frames = 0;
        this.updates = 0;
    }

    public double getElapsedTime() {
        long currentTime = System.nanoTime();
        double elapsedTime = (currentTime - this.lastTime) / 1e9;
        this.lastTime = currentTime;
        return elapsedTime;
    }

    public double getTotalElapsedTime() {
        return (System.nanoTime() - this.start) / 1e9;
    }

    public long getElapsedMilliseconds() {
        return (long) (getElapsedTime() * 1000.0);
    }

    public long getElapsedNanoseconds() {
        return (long) (getElapsedTime() * 1e9);
    }

    public LocalDateTime getCurrentLocalTime() {
        return LocalDateTime.now();
    }

    public double getDeltaTime() {
        return getElapsedTime();
    }

    public double getFPS() {
        double deltaTime = getElapsedTime();
        if (deltaTime > 0) {
            return 1.0 / deltaTime;
        } else {
            return 0.0; // Avoid division by zero
        }
    }

    public double getUPS() {
        double deltaTime = getElapsedTime();
        if (deltaTime > 0) {
            return 1.0 / deltaTime;
        } else {
            return 0.0; // Avoid division by zero
        }
    }

    public void update() {
        this.frames++;
        this.updates++;

        if (getTotalElapsedTime() >= 1.0) {
            // Print FPS and UPS every second
            logger.info("FPS: " + this.frames + ", UPS: " + this.updates);
            this.frames = 0;
            this.updates = 0;
            this.start = System.nanoTime();
        }
    }

    public String getCurrentFormattedTime(String format) {
        return getCurrentLocalTime().format(DateTimeFormatter.ofPattern(format));
    }
}
