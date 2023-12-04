package de.traumastudios.infernal.core.networking.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class RealmShard {
    private int shardID;
    private String name;
    private List<Player> players;
    private int maxPlayers;

    public void add(Player player) {
        if (this.players.size() != maxPlayers)  {
            this.players.add(player);
        }
    }

    public void remove(Player player) {
        this.players.remove(player);
    }
}
