package de.traumastudios.infernal.core.networking.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Player {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthDate;
    private boolean isVerified;
    private boolean deleted;
}
