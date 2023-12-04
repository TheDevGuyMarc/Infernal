package de.traumastudios.infernal.core.networking.server;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerType {
    Auth(0),
    Game(1),
    Chat(2);

    private final int value;
}
