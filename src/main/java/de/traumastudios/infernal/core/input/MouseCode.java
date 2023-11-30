package de.traumastudios.infernal.core.input;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MouseCode {
    Button0(0),
    Button1(1),
    Button2(2),
    Button3(3),
    Button4(4),
    Button5(5),
    Button6(6),
    Button7(7),
    ButtonLast(7), // Adjust as needed
    ButtonLeft(0),
    ButtonRight(1),
    ButtonMiddle(2);

    private final int value;
}
