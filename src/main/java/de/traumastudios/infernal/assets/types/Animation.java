package de.traumastudios.infernal.assets.types;

import de.traumastudios.infernal.assets.Asset;
import de.traumastudios.infernal.assets.AssetMetaData;
import lombok.Getter;
import lombok.Setter;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
public class Animation extends Asset {
    private Map<Integer, ByteBuffer> data;

    public Animation(UUID id, AssetMetaData metaData, Map<Integer, ByteBuffer> data) {
        super(id, metaData);
        this.data = data;
    }
}
