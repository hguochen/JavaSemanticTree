package libraries;

import java.util.Observable;
import java.util.Observer;

/**
 * Define an Observable(subject)
 */
class WeatherData extends Observable {
    private float temperature;

    public void setTemperature(float temp) {
        this.temperature = temp;
        // mark the observable as changed
        setChanged();
        notifyObservers(temp); // notify all observers
    }
}

/**
 * Define an Observer
 */
class TemperatureDisplay implements Observer {
    private String name;

    public TemperatureDisplay(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        System.out.println(name + ": Temperature updated to " + arg + "C");
    }
}

/**
 * Observer pattern is a behavior design pattern where the object(the subject) maintains a list of dependents(Observers)
 * and notifies them automatically of any change in its state.
 *
 * Java used to have built-in support for this pattern using:
 * - java.util.Observable — the subject (a.k.a. "publisher")
 * - java.util.Observer — the observer (a.k.a. "subscriber")
 *
 * Observable and Observer are deprecated. For modern implementations, you should consider:
 * - Using the Observer pattern manually via custom interfaces (preferred).
 * - Or using Reactive Streams / RxJava / Project Reactor for advanced reactive programming.
 */
public class ObserverDemo {
    public static void main(String[] args) {
        WeatherData weather = new WeatherData();
        TemperatureDisplay display1 = new TemperatureDisplay("Display 1");
        TemperatureDisplay display2 = new TemperatureDisplay("Display 2");

        weather.addObserver(display1);
        weather.addObserver(display2);

        weather.setTemperature(23.5f);
        weather.setTemperature(25.0f);
    }
}

// here's how to implement above without using deprecated classes. ie. modern observer pattern
//interface Observer {
//    void update(float temperature);
//}
//
//class WeatherStation {
//    private List<Observer> observers = new ArrayList<>();
//    private float temperature;
//
//    public void addObserver(Observer o) {
//        observers.add(o);
//    }
//
//    public void setTemperature(float temperature) {
//        this.temperature = temperature;
//        notifyAllObservers();
//    }
//
//    private void notifyAllObservers() {
//        for (Observer o : observers) {
//            o.update(temperature);
//        }
//    }
//}