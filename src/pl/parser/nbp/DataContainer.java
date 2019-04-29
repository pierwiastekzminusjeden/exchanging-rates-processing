package pl.parser.nbp;


import java.util.HashSet;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;


/**
 * Contener that stores read data. Data is not sequenced.
 */
public class DataContainer {
    /**
     * Nested class that stores possible operations on stored data.
     */
    public class Calculations {
        /**
         * Function counts average of data.
         * @return average of purchased
         */
        public double buyingAverage() {
            return buySet.stream().mapToDouble(d -> d).average().getAsDouble();
        }
        /**
         * Function counts standard deviation of data.
         * @return standard deviation of sold
         */
        public double sellingStandardDeviation() {
            double avg = sellSet.stream().mapToDouble(d -> d).average().getAsDouble();
            return sqrt(sellSet.stream().map(d -> pow(d - avg, 2)).mapToDouble(d -> d).sum() / sellSet.size());
        }
    }

    private String currency;
    private HashSet<Double> sellSet;
    private HashSet<Double> buySet;

    public final Calculations calculations;

    DataContainer(String currency){
        this.currency = currency;
        sellSet = new HashSet<>();
        buySet = new HashSet<>();
        calculations = new Calculations();
    }

    /**
     * Function allows adding new data to container.
     * @param sell string with exchange rate
     * @param buy string with exkchange rate
     */
    public void addNewData(String sell, String buy){
        sellSet.add(Double.parseDouble(sell.replace(',', '.')));
        buySet.add(Double.parseDouble(buy.replace(',', '.')));
    }

}
