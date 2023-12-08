package de.traumastudios.infernal.assets.types;

import de.traumastudios.infernal.assets.Asset;
import de.traumastudios.infernal.assets.AssetMetaData;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.UUID;

@Getter
@Setter
public class Font extends Asset {
    private ByteBuffer data;

    public Font(UUID id, AssetMetaData metaData, ByteBuffer data) {
        super(id, metaData);
        this.data = data;
    }
}
