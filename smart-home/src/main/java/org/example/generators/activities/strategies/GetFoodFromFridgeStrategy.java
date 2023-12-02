package org.example.generators.activities.strategies;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.devices.Device;
import org.example.devices.DeviceController;
import org.example.devices.Food;
import org.example.devices.Fridge;

import java.util.Random;

@Data
@Slf4j
public class GetFoodFromFridgeStrategy implements ActivityStrategy{

    @Override
    public void performActivity(DeviceController deviceController, Device device, String personName) throws Exception {
        Fridge fridge = (Fridge) deviceController.getDeviceByName("Fridge");
        Food food = pickFood(deviceController);
        if (fridge.getFoodInside().contains(food)) {
            fridge.getFoodInside().remove(food);
            System.out.printf("%s has took %s from fridge%n", personName, food.toString());
            log.info("Person took food from fridge");
        } else {
            throw new Exception("There is not enough " + food.toString() + " in the fridge");
        }
    }

    private Food pickFood(DeviceController deviceController){
        Fridge fridge = (Fridge)deviceController.getDeviceByName("Fridge");
        int foodCount = fridge.getFoodInside().size();
        int randomFoodIndex = new Random().nextInt(foodCount+1);
        return fridge.getFoodInside().get(randomFoodIndex);
    }
}
