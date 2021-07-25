package main.inventory;

import lombok.Getter;

public enum RentalToolList {
    LADW(ToolBrand.WERNER, ToolType.LADDER),
    CHNS(ToolBrand.STIHL, ToolType.CHAINSAW),
    JAKR(ToolBrand.RIDGID, ToolType.JACKHAMMER),
    JAKD(ToolBrand.DEWALT, ToolType.JACKHAMMER);

    @Getter private final ToolBrand toolBrand;
    @Getter private final ToolType toolType;

    RentalToolList(ToolBrand toolBrand, ToolType toolType) {
        this.toolBrand = toolBrand;
        this.toolType = toolType;
    }

}
