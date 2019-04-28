package pl.parser.nbp;



public class MainClass {

    public static void main(String[] args) {

        if(args.length == 3){
            RunArgsHandler data = new RunArgsHandler(args[0], args[1], args[2]);
            ConnectionHandler ch = new ConnectionHandler(data);
            ch.searchingDirs();
        }
        else{
            System.err.print("Wrong input");
            System.exit(-1);
        }



    }
}
