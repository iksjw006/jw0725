package main.inventory;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public class RentalTool extends Tool implements RentalToolInterface {

    @Getter @Setter private BigDecimal rentalPrice;
    @Getter @Setter private boolean chargeWeekday;
    @Getter @Setter private boolean chargeWeekend;
    @Getter @Setter private boolean chargeHoliday;
    
    public RentalTool setRentalTool(String toolType, String toolBrand, String toolCode, BigDecimal rentalPrice,
                                    boolean chargeWeekday, boolean chargeWeekend, boolean chargeHoliday) {
        this.setToolType(toolType);
        this.setToolCode(toolCode);
        this.setToolBrand(toolBrand);
        this.setRentalPrice(rentalPrice);
        this.setChargeWeekday(chargeWeekday);
        this.setChargeWeekend(chargeWeekend);
        this.setChargeHoliday(chargeHoliday);
        return this;
    }

}
