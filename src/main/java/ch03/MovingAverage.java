package ch03;

import java.util.Iterator;

public class MovingAverage extends TimeSeries<Double> {
    private final TimeSeries parent;
    private final int length;

    public MovingAverage(TimeSeries parent, int length) {
        this.parent = parent;
        this.length = length;
        if (length > parent.size()) {
            throw new IllegalArgumentException("That's too long.");
        }
        double[] tmp = new double[length];
        double sum = 0;
        int i = 0;
        Iterator it = parent.iterator();
        for(int j = 0; j < length; j ++) {
            sum += tmp[i++] = nextValue(it);
        }
        this.add(System.currentTimeMillis(), sum/length);

        while (it.hasNext()) {
            sum -= tmp[i%length];
            sum += tmp[i++%length] = nextValue(it);
            this.add(System.currentTimeMillis(), sum/length);
        }
    }

    private static double nextValue(Iterator it) {
        TimeSeries.Entry<Double> entry = (TimeSeries.Entry)it.next();
        return entry.getEvent();
    }
}
