package org.example;

import org.example.configBuilders.ConfigBuilder;
import org.example.houses.House;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        String pathToConfig1 = "/config/config1.json";
        String pathToConfig2 = "/config/config1.json";

        URL resource = Main.class.getResource(pathToConfig1);
        File file = new File(resource.getFile());
        String filePath = file.getPath();

        House house1 = ConfigBuilder.buildHouseFromJson(filePath);
//        House house1 = ConfigBuilder.buildHouseFromJson("config/config1.json");
//        House house1 = ConfigBuilder.buildHouseFromJson("./config/config1.json");
//        House house2 = ConfigBuilder.buildHouseFromJson("./config/config2.json");

        Simulation simulation = new Simulation(house1);
        simulation.runSimulation();


    }
}