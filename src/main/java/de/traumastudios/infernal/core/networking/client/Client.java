package de.traumastudios.infernal.core.networking.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Client implements IClient {
    private int clientID;
    private String hostname;
    private int port;

    @Override
    public void connect(String host, int port, String username, String password) {

    }

    @Override
    public void disconnect() {

    }

    @Override
    public void monitor() {

    }
}
