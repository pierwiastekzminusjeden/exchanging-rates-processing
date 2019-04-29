package pl.parser.nbp;

/**
 * Simple JAVA application processing exchange rates from NBP website.
 * @author Krystian Molenda
 * @version 1.0
 */
public class MainClass {

    public static void main(String[] args) {
        if(args.length == 3){
            //validation of run args
            RunArgsHandler data = new RunArgsHandler(args[0], args[1], args[2]);
            //container for buying and selling values. Available basics operations on collected data
            DataContainer dc = new DataContainer(data.getCurrency());
            //Reading XML files from NBP site. XMLReader has static read function
            XMLReader.read(new UrlsCreator(data.getBegDate(),data.getEndDate()).create(),dc, data.getCurrency());
            //operations on collected data and printing results. Order of values is not important
            System.out.format("%.4f", dc.calculations.buyingAverage());
            System.out.println();
            System.out.format("%.4f", dc.calculations.sellingStandardDeviation());
        }
        else{
            System.err.print("Wrong input");
            System.exit(1);
        }

    }
}
