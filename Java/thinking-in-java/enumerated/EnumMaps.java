import enumerated.AlarmPoints;

import java.util.EnumMap;
import java.util.Map;

import static enumerated.AlarmPoints.*;
import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

interface Command {
    void action();
}

public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<>(AlarmPoints.class);
        em.put(KITCHEN, () -> print("Kitchen fire!"));
        em.put(BATHROOM, () -> print("Bathroom alert!"));

        for(Map.Entry<AlarmPoints,Command> e : em.entrySet()) {
            printnb(e.getKey() + ": ");
            e.getValue().action();
        }

        try { // If there's no value for a particular key:
            em.get(UTILITY).action();
        } catch(Exception e) {
            print(e);
        }
    }
}
