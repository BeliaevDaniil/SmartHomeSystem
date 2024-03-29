package org.example.generators.activities.petActivities.strategies;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.devices.Device;
import org.example.devices.DeviceController;
import org.example.generators.activities.ActivityStrategy;
import org.example.houseResidents.HouseResident;
import org.example.houseResidents.people.Person;

@Data
@Slf4j
public class ComeOutOfShelterStrategy implements ActivityStrategy {

    // TODO пофиксить person аргумент
    @Override
    public void performActivity(DeviceController deviceController, Device device, HouseResident petName) throws Exception {
        if (device != null) {
            deviceController.turnOffDevice(device);
            log.info(String.format("Pet %s came out of the shelter, shelter was turned off", petName.getName()));
        }
    }
}
