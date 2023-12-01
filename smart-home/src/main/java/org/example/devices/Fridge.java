package org.example.devices;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Fridge extends Device{
    private Food food;
    private List<Food> foodInside;
}