package com.KrystianMolenda;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class DataHandler {
    private Date begDate;
    private Date endDate;
    private String currency;

    DataHandler(String currency, String begDate, String endDate){
        HashSet<String> currencySet = new HashSet<>(Arrays.asList("EUR","GBP","USD","CHF"));
        if(currencySet.contains(currency))
            this.currency = currency;
        else {
            System.err.print("Wrong currency. Please try one of \"EUR\",\"GBP\",\"USD\",\"CHF\"");
            System.exit(0);
        }
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            this.begDate = simpleDateFormat.parse(begDate);
            this.endDate = simpleDateFormat.parse(endDate);
        }
        catch(ParseException e){
            System.err.print("Wrong date format. Please try YYYY-MM-DD \n" + e.getStackTrace());
            System.exit(0);
        }

        if(begDate.compareTo(endDate) > 0){
            System.err.print("Beggining date is after end date");
            System.exit(0);
        }


        System.out.print("Parameters inserted");
    }

    public Date getBegDate() {
        return begDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCurrency() {
        return currency;
    }
}
