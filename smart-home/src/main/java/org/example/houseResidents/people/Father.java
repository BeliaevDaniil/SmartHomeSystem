package org.example.houseResidents.people;

import lombok.extern.slf4j.Slf4j;
import org.example.generators.events.EventToHandle;
import org.example.generators.events.EventToHandleByPerson;
import org.example.generators.events.strategies.forPerson.PersonNothingToDoStrategy;
import org.example.generators.events.strategies.forPerson.*;
import org.example.houses.House;

@Slf4j
public class Father extends Person{

    public Father(Integer id, House house, String name) {
        super(id, house, name, PersonType.FATHER);
    }

    @Override
    public void handleEvent(EventToHandle event) {
        setStrategyByEvent(event);
        getEventStrategy().handle(house.getDeviceController());
    }

    public void setStrategyByEvent(EventToHandle event) {
         switch ((EventToHandleByPerson) event) {
            case BREAK_DEVICE -> setEventStrategy(new BreakDeviceStrategy());
            case BAD_FOOD -> setEventStrategy(new ExpiredFoodStrategy());
            case CHILD_GOT_HURT -> setEventStrategy(new ChildGotHurtStrategy());
            case GUEST_ARRIVAL -> setEventStrategy(new GuestArrivalStrategy());
            case FIRE_ALARM -> setEventStrategy(new FireAlarmStrategy());
             case NO_EVENT_FOR_PERSON -> setEventStrategy(new PersonNothingToDoStrategy());
        };
    }

}
