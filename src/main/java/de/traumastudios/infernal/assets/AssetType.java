package de.traumastudios.infernal.assets;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssetType {
    None(0),
    Animation(1),
    Audio(2),
    Font(3),
    Sprite(4),
    Shader(5),
    Scene(6),
    Sound(7),
    TileMap(8),
    Texture(9),
    UI(10),
    Video(11);

    private final int value;
}
