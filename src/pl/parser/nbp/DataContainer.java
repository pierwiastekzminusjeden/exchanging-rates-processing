package pl.parser.nbp;


import java.util.HashSet;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

public class DataContainer {
    private String currency;
    private HashSet<Double> sellSet;
    private HashSet<Double> buySet;

    public Calculations calculations;

    DataContainer(){
        sellSet = new HashSet<>();
        buySet = new HashSet<>();
        calculations = new Calculations();
    }

    DataContainer(String currency, int initialSize){
        this.currency = currency;
        sellSet = new HashSet<>(initialSize);
        buySet = new HashSet<>(initialSize);
    }

    public void addNewData(String sell, String buy){

        sellSet.add(Double.parseDouble(sell.replace(',', '.')));
        buySet.add(Double.parseDouble(buy.replace(',', '.')));
    }
    public class Calculations {
        public void average() {
            System.out.format("%.4f", buySet.stream().mapToDouble(d -> d).average().getAsDouble());
        }

        public void standardDeviation() {
            double avg = sellSet.stream().mapToDouble(d -> d).average().getAsDouble();
            System.out.format("%.4f", sqrt(sellSet.stream().map(d -> pow(d - avg, 2)).mapToDouble(d -> d).sum() / sellSet.size()));
        }
    }
}
