package de.traumastudios.infernal.platform.platformdetection;

import lombok.Getter;

public class PlatformDetector {
    private final String osName;

    public PlatformDetector() {
        this.osName = System.getProperty("os.name", "unknown").toLowerCase();
    }

    public OperatingSystem detect() {
        if (this.isWindows()) {
            return OperatingSystem.Windows;
        } else if (this.isMac()) {
            return OperatingSystem.MacOS;
        } else if (this.isLinux()) {
            return OperatingSystem.Linux;
        } else {
            return OperatingSystem.Unknown;
        }
    }

    public boolean isWindows() {
        return this.osName.contains("win");
    }

    public boolean isMac() {
        return this.osName.contains("mac");
    }

    public boolean isLinux() {
        return this.osName.contains("nix") || this.osName.contains("nux");
    }
}
