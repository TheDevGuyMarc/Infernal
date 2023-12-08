package de.traumastudios.infernal.platform.platformdetection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum OperatingSystem {
    Unknown(0),
    MacOS(1),
    Windows(2),
    Linux(3);

    private final int value;
}
