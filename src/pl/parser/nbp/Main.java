package pl.parser.nbp;



public class Main {

    public static void main(String[] args) {

        if(args.length == 3){
            RunArgsHandler data = new RunArgsHandler(args[0], args[1], args[2]);
        }
        else{
            System.out.print("Wrong input");
        }

    }
}
