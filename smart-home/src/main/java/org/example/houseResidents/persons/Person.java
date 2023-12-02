package org.example.houseResidents.persons;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.example.devices.Device;
import org.example.devices.DeviceController;
import org.example.generators.activities.Activity;
import org.example.generators.activities.PersonActivity;
import org.example.generators.activities.strategies.*;
import org.example.generators.events.EventToHandle;
import org.example.houseResidents.HouseResident;

@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Person extends HouseResident implements Subscriber{

    private String name;
    private boolean atHome;
    private final DeviceController deviceController;

    public Person(DeviceController deviceController) {
        this.deviceController = deviceController;
    }

    public abstract void handleEvent(EventToHandle event);

    public void doActivity(Activity activity) throws Exception {
        Device device = getDeviceByActivity(activity);
        setStrategy(getStrategyByActivity(activity));
        strategy.performActivity(deviceController, device, name);
    }

    private Device getDeviceByActivity(Activity activity) {
        return switch ((PersonActivity) activity) {
            case GetFoodFromFridge, AddFoodToFridge -> deviceController.getDeviceByName("Fridge");
            case StartDoingLaundry, FinishDoingLaundry -> deviceController.getDeviceByName("Washing machine");
            case StartWashingDishes, FinishWashingDishes -> deviceController.getDeviceByName("Dishwasher");
            case StartGrillingMeet, FinishGrillingMeet -> deviceController.getDeviceByName("Grill");
            case StartMakingCoffee, FinishMakingCoffee -> deviceController.getDeviceByName("Coffee Machine");
            case StartHeatingFood, FinishHeatingFood -> deviceController.getDeviceByName("Microwave");
            case StartBakingFood, FinishBakingFood -> deviceController.getDeviceByName("Oven");
            case StartUsingComputer, FinishUsingComputer -> deviceController.getDeviceByName("Computer");
        };
    }

    private ActivityStrategy getStrategyByActivity(Activity activity) {
        return switch ((PersonActivity) activity) {
            case GetFoodFromFridge -> new GetFoodFromFridgeStrategy();
            case AddFoodToFridge -> new AddFoodToFridgeStrategy();

            case StartDoingLaundry, StartWashingDishes,
                 StartGrillingMeet, StartMakingCoffee,
                 StartHeatingFood, StartBakingFood,
                 StartUsingComputer -> new StartUsingDeviceStrategy();

            case FinishDoingLaundry, FinishWashingDishes,
                 FinishGrillingMeet, FinishMakingCoffee,
                 FinishHeatingFood, FinishBakingFood,
                 FinishUsingComputer -> new FinishUsingDeviceStrategy();
        };
    }
}