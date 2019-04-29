package pl.parser.nbp;



public class MainClass {

    public static void main(String[] args) {

        if(args.length == 3){
            RunArgsHandler data = new RunArgsHandler(args[0], args[1], args[2]);
            UrlsCreator ch = new UrlsCreator(data.getBegDate(),data.getEndDate());
            XMLReader reader = new XMLReader();
            reader.read(ch.create(), data.getCurrency());
        }
        else{
            System.err.print("Wrong input");
            System.exit(-1);
        }



    }
}
