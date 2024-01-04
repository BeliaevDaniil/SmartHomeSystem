package org.example.reports.reportGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.generators.events.EventGeneratorForHandlingByPerson;
import org.example.generators.events.EventToHandle;
import org.example.houseResidents.people.Person;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class EventReportGenerator extends BaseReportGenerator {

    private Map<Person, EventToHandle> eventsHandledByPeople = new HashMap<>();
    private List<EventToHandle> eventsHandledAutomatically = new ArrayList<>();

    public EventReportGenerator() {
        this.reportFile = "./src/main/java/org/example/reports/reportsFiles/eventReport.txt";
        try {
            this.writer = new PrintWriter(new PrintWriter(reportFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void generateReport() {
        writer.println("EVENTS REPORT");
        writer.println("");
        printEventsHandledByPeople();
        writer.println("---");
        printEventsHandledAutomatically();
        writer.close();
    }

    private void printEventsHandledByPeople() {
        writer.println("EVENTS HANDLED BY PEOPLE:");
        int i = 1;
        for (Map.Entry<Person, EventToHandle> entry : eventsHandledByPeople.entrySet()) {
            writer.println(i + ") Event '" + entry.getValue() + "' was handled by person '" + entry.getKey().getType() + "'");
            ++i;
        }
    }

    private void printEventsHandledAutomatically(){
        writer.println("EVENTS HANDLED AUTOMATICALLY:");
        int j = 1;
        for (EventToHandle event : eventsHandledAutomatically){
            writer.println(j + ") Event '" + event + "' was handled automatically");
            ++j;
        }
    }

    public void writeEventToReport(Person eventHandler, EventToHandle handledEvent) {
        if (eventHandler != null) {
            eventsHandledByPeople.put(eventHandler, handledEvent);
        } else {
            eventsHandledAutomatically.add(handledEvent);
        }
    }
}
