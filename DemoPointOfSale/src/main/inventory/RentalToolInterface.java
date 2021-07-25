package main.inventory;

import java.math.BigDecimal;

public interface RentalToolInterface {

    RentalTool setRentalTool(String toolType, String toolBrand, String toolCode, BigDecimal rentalPrice,
                                    boolean chargeWeekday, boolean chargeWeekend, boolean chargeHoliday);

}
