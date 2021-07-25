package main.pricing;

import lombok.Getter;
import main.inventory.ToolType;

import java.math.BigDecimal;

public enum RentalPriceList {
    Ladder(ToolType.LADDER, new BigDecimal("1.99"), true, true, false),
    Chainsaw(ToolType.CHAINSAW, new BigDecimal("1.49"), true, false, true),
    Jackhammer(ToolType.JACKHAMMER, new BigDecimal("2.99"), true, false, false);

    @Getter private final ToolType toolType;
    @Getter private final BigDecimal rentalPrice;
    @Getter private final boolean weekdayCharge;
    @Getter private final boolean weekendCharge;
    @Getter private final boolean holidayCharge;

    RentalPriceList(ToolType toolType, BigDecimal rentalPrice, boolean weekdayCharge, boolean weekendCharge,
                    boolean holidayCharge) {
        this.toolType = toolType;
        this.rentalPrice = rentalPrice;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }

}
