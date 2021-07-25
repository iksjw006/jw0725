package main.inventory;

import lombok.Getter;

public enum ToolType {
    LADDER("Ladder"),
    CHAINSAW("Chainsaw"),
    JACKHAMMER("Jackhammer");

    @Getter private final String toolType;

    ToolType(String toolType) {
        this.toolType = toolType;
    }

}