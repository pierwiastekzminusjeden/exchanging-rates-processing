package pl.parser.nbp;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;

public class RunArgsHandler {
    private LocalDate begDate;
    private LocalDate endDate;
    private String currency;

    RunArgsHandler(String currency, String begDate, String endDate){

        if(dataValidation(currency, begDate, endDate) == false)
            System.exit(1);
    }

    private boolean dataValidation(String currency, String begDate, String endDate ){

        HashSet<String> currencySet = new HashSet<>(Arrays.asList("EUR","GBP","USD","CHF"));

        if(currencySet.contains(currency))
            this.currency = currency;
        else {
            System.err.print("Wrong currency. Please try one of \"EUR\",\"GBP\",\"USD\",\"CHF\"");
            return false;
        }

        try {
            this.begDate = LocalDate.parse(begDate);
            this.endDate = LocalDate.parse(endDate);
        } catch(DateTimeParseException e){
            System.err.print("Wrong date format. Please try YYYY-MM-DD \n" + e.getStackTrace());
            return false;
        }

        if(this.begDate.compareTo(this.endDate) > 0){
            System.err.print("Beginning date is after end date");
            return false;
        }
        if(this.begDate.getYear() < 2002){
            System.err.print("First possible date is 2002-01-01");
            return false;
        }
        if(this.begDate.compareTo(LocalDate.now()) > 0 || this.endDate.compareTo(LocalDate.now()) > 0) {
            System.err.print("Last possible date is " + LocalDate.now() +" Set to this date.");
            this.endDate = LocalDate.now();
            return true;
        }
        return true;
    }

    public LocalDate getBegDate() {
        return begDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getCurrency() {
        return currency;
    }

}
