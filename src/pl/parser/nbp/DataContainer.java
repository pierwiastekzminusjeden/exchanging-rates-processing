package pl.parser.nbp;


import java.util.HashSet;

import static java.lang.Math.sqrt;
import static java.lang.StrictMath.pow;

public class DataContainer {

    public class Calculations {
        public double average() {
            return buySet.stream().mapToDouble(d -> d).average().getAsDouble();
        }

        public double standardDeviation() {
            double avg = sellSet.stream().mapToDouble(d -> d).average().getAsDouble();
            return sqrt(sellSet.stream().map(d -> pow(d - avg, 2)).mapToDouble(d -> d).sum() / sellSet.size());
        }
    }

    private String currency;
    private HashSet<Double> sellSet;
    private HashSet<Double> buySet;

    public Calculations calculations;


    DataContainer(String currency){
        this.currency = currency;
        sellSet = new HashSet<>();
        buySet = new HashSet<>();
        calculations = new Calculations();
    }

    public void addNewData(String sell, String buy){
//        System.out.println(sell + " " + buy);
        sellSet.add(Double.parseDouble(sell.replace(',', '.')));
        buySet.add(Double.parseDouble(buy.replace(',', '.')));
    }

}
