package de.traumastudios.infernal.assets.types;

import de.traumastudios.infernal.assets.Asset;
import de.traumastudios.infernal.assets.AssetMetaData;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TileMap<T> extends Asset {
    private T data;

    public TileMap(UUID id, AssetMetaData metaData, T data) {
        super(id, metaData);
        this.data = data;
    }
}
