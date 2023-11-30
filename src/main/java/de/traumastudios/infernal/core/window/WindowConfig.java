package de.traumastudios.infernal.core.window;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class WindowConfig {
    private boolean debugMode = false;
    private boolean compatibleProfile;
    private boolean isVisible;
    private boolean isResizable = true;
    private boolean isFullscreen = true;
    private boolean antiAliasing = true;
    private int antiAliasingSamples;
    private String iconPath;
    private String cursorPath;
    private int width;
    private int height;
    private String title;
}
