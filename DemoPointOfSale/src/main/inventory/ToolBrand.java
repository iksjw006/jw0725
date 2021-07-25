package main.inventory;

import lombok.Getter;

public enum ToolBrand {
    WERNER("Werner"),
    STIHL("Stihl"),
    RIDGID("Ridgid"),
    DEWALT("DeWalt");

    @Getter private final String toolBrand;

    ToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }

}
