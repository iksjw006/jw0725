package main.initialization;

import main.inventory.RentalTool;
import main.inventory.RentalToolList;
import main.pricing.RentalPriceList;

import java.util.HashMap;
import java.util.Map;

public class InitializeValues {

    public Map<String, RentalTool> RentalToolInventory = InitializeAndBuildToolInventory();

    private static Map<String, RentalTool> InitializeAndBuildToolInventory() {
        Map<String, RentalTool> InventoryMap = new HashMap<>();
        for(RentalToolList rentalToolList : RentalToolList.values()) {
            InventoryMap.put(rentalToolList.name(),
                new RentalTool().setRentalTool(
                        rentalToolList.getToolType().getToolType(),
                        rentalToolList.getToolBrand().getToolBrand(),
                        rentalToolList.name(),
                        RentalPriceList.valueOf(rentalToolList.getToolType().getToolType()).getRentalPrice(),
                        RentalPriceList.valueOf(rentalToolList.getToolType().getToolType()).isWeekdayCharge(),
                        RentalPriceList.valueOf(rentalToolList.getToolType().getToolType()).isWeekendCharge(),
                        RentalPriceList.valueOf(rentalToolList.getToolType().getToolType()).isHolidayCharge()));
        }
        return InventoryMap;
    }

}
