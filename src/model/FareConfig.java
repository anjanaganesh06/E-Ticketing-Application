package model;
import java.util.HashMap;
import java.util.Map;

public class FareConfig {
    private static final Map<Integer, Double> fareMap = new HashMap<>();

    static {
        // Predefine fares for each train ID
        fareMap.put(2, 45.00);  // SuperFast 202
        fareMap.put(6, 60.00);  // FastTrack 303
        fareMap.put(7, 75.00);  // Bangalore Mail
        fareMap.put(8, 65.00);  // Chennai Express
    }

    public static double getFare(int trainId) {
        return fareMap.getOrDefault(trainId, 50.00); // Default fare if not found
    }
}
