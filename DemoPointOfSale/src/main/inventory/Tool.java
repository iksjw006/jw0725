package main.inventory;

import lombok.Getter;
import lombok.Setter;

public abstract class Tool {

    @Getter @Setter protected String toolType;
    @Getter @Setter protected String toolBrand;
    @Getter @Setter protected String toolCode;

    Tool(){
        toolType = "";
        toolBrand = "";
        toolCode = "";
    }

}
