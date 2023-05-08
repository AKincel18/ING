package ing.contest.transactions.utils;

public class Utils {
    public static float roundTo2DecimalPlaces(float value) {
        return (float) (Math.round(value * 100.0) / 100.0);
    }
}
