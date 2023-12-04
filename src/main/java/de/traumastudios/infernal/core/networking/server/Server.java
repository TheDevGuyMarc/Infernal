package de.traumastudios.infernal.core.networking.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Server implements IServer {
    private int serverID;
    private String name;
    private String host;
    private int port;
    private ServerType type;

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void monitor() {

    }
}
