package de.traumastudios.infernal.assets;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public abstract class Asset {
    private UUID id;
    private AssetMetaData metaData;
}
