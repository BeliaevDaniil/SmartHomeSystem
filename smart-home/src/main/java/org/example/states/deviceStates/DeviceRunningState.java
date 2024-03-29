package org.example.states.deviceStates;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.devices.Device;
import org.example.exceptions.deviceExceptions.DeviceStateException;

@Slf4j
public class DeviceRunningState extends DeviceState{


    public DeviceRunningState(Device device) {
        super(device, "Running");
    }

    @Override
    public void turnOn() {
        log.info("{} is already turned on", device.toString());
    }

    @Override
    public void turnOff() {
        device.changeState(new DeviceReadyState(device));
        device.stopDeviceAction();
        log.info("{} state changed to ready", device.toString());
    }

    @Override
    public void run() {
        log.info("{} is already running", device.toString());
    }
}
