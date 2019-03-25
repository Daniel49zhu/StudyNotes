import enumerated.MySignal;

import static enumerated.MySignal.*;
import static net.mindview.util.Print.print;

enum Signal {
    GREEN, YELLOW, RED
}

public class TrafficLight {
    MySignal color = MySignal.RED;
//使用static import进行了改写
    public void change() {
        switch (color) {
            case RED:
                color = GREEN;
                break;
            case GREEN:
                color = YELLOW;
                break;
            case YELLOW:
                color = RED;
                break;
        }
    }

//         public void change() {
//        switch (color) {
//            case RED:
//                color = Signal.GREEN;
//                break;
//            case GREEN:
//                color = Signal.YELLOW;
//                break;
//            case YELLOW:
//                color = Signal.RED;
//                break;
//        }
//    }

    public String toString() {
        return "The traffic light is " + color;
    }

    public static void main(String[] args) {
        TrafficLight t = new TrafficLight();
        for(int i = 0 ; i< 7;i++) {
            print(t);
            t.change();
        }
    }
}
/*
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
The traffic light is GREEN
The traffic light is YELLOW
The traffic light is RED
 */
