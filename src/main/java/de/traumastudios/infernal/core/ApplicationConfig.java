package de.traumastudios.infernal.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApplicationConfig {
    private String name;
    private String workingDirectory;
    private String renderingEngine;
    private String audioEngine;
    private String physicsEngine;
    private int targetFPS = 60;
    private int targetUPS;
}
