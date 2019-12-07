package base;

import java.util.Arrays;

public enum SetType {

    EQU_Set_A("EQU_Set_A"),
    STR_GEO_Set_B_1("STR_GEO_Set_B_1"),
    STR_GEO_Set_B_2("STR_GEO_Set_B_2"),
    STR_GEO_Set_B_3("STR_GEO_Set_B_3"),
    STR_GEO_Set_C("STR_GEO_Set_C");

    String name;

    SetType(String name) {
        this.name = name;
    }

    public static SetType findSetTypeByString(String typeString) {
        return Arrays.stream(SetType.values())
                .filter(type -> type.name.equalsIgnoreCase(typeString))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("No SetType was found for argument: " + typeString));
    }
}
