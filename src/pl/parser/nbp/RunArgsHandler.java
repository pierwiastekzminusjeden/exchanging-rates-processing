package pl.parser.nbp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

public class RunArgsHandler {
    private Date begDate;
    private Date endDate;
    private String currency;

    RunArgsHandler(String currency, String begDate, String endDate){

        if(dataValidation(currency, begDate, endDate))
            System.out.print("Parameters inserted");
        else
            System.exit(-1);
    }

    private boolean dataValidation(String currency, String begDate, String endDate ){
        HashSet<String> currencySet = new HashSet<>(Arrays.asList("EUR","GBP","USD","CHF"));

        if(currencySet.contains(currency))
            this.currency = currency;
        else {
            System.err.print("Wrong currency. Please try one of \"EUR\",\"GBP\",\"USD\",\"CHF\"");
            return false;
        }

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            this.begDate = simpleDateFormat.parse(begDate);
            this.endDate = simpleDateFormat.parse(endDate);
        }
        catch(ParseException e){
            System.err.print("Wrong date format. Please try YYYY-MM-DD \n" + e.getStackTrace());
            return false;
        }

        if(begDate.compareTo(endDate) > 0){
            System.err.print("Beginning date is after end date");
            return false;
        }

        return true;
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
