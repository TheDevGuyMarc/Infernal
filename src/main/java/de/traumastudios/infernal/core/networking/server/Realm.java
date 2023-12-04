package de.traumastudios.infernal.core.networking.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class Realm {
    private int realmID;
    private String name;
    private Map<Integer, RealmShard> shards;
    private int maxPlayers;

    public void addPlayer(int instanceID, Player player) {
        if (this.isLoggedIn(player)) {
            removePlayer(player);
        }

        this.shards.entrySet().stream()
                .filter(entry -> entry.getValue().getShardID() == instanceID)
                .findFirst()
                .ifPresent(entry -> entry.getValue().add(player));
    }

    public void removePlayer(Player player) {
        this.shards.entrySet().stream()
                .filter(entry -> entry.getValue().getPlayers().contains(player))
                .findFirst()
                .ifPresent(entry -> entry.getValue().remove(player));
    }

    public void createShard(int id, String name, int maxPlayers) {
        RealmShard shard = new RealmShard(id, name, new ArrayList<>(), maxPlayers);

        if (shardsAreFull()) {
            this.shards.put(this.shards.size() + 1, shard);
        }
    }

    public void removeShard(int id) {
        this.shards.remove(id);
    }

    private boolean shardsAreFull() {
        for (RealmShard shard : this.shards.values()) {
            if (!(shard.getPlayers().size() == shard.getMaxPlayers())) {
                return false;
            }
        }
        return true;
    }

    private boolean isLoggedIn(Player player) {
        Optional<Map.Entry<Integer, RealmShard>> shard = this.shards.entrySet().stream()
                .filter(entry -> entry.getValue().getPlayers().contains(player))
                .findFirst();

        return shard.isPresent();
    }
}
