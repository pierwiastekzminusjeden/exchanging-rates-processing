package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class ConnectionHandler {
    RunArgsHandler args;
    ArrayList<URL> dirsURLs;
    ArrayList<URL> xmlURLs;

    ConnectionHandler(RunArgsHandler args) {
        this.args = args;
        dirsURLs = new ArrayList<>();
        xmlURLs = new ArrayList<>();
        prepareDirs();
    }

    private void prepareDirs() {
        int begYear = args.getBegDate().getYear();
        int endYear = args.getEndDate().getYear();
        String tmp;

        try {
            if (begYear == LocalDate.now().getYear()) {
                tmp = "http://www.nbp.pl/kursy/xml/dir.txt";
                dirsURLs.add(new URL(tmp));
            } else if (endYear == LocalDate.now().getYear()) {

                for (int i = begYear; i <= endYear - 1; i++) {
                    tmp = "http://www.nbp.pl/kursy/xml/" + "dir" + i + ".txt";
                    dirsURLs.add(new URL(tmp));
                }
                tmp = "http://www.nbp.pl/kursy/xml/dir.txt";
                dirsURLs.add(new URL(tmp));
            } else {
                for (int i = begYear; i <= endYear; i++) {
                    tmp = "http://www.nbp.pl/kursy/xml/" + "dir" + i + ".txt";
                    dirsURLs.add(new URL(tmp));
                }
            }
        } catch (MalformedURLException e) { System.err.print(e.getStackTrace()); }
        //print to check
//        dirsURLs.stream().forEach(System.out::println);
    }

        public void searchingDirs() {
        BufferedReader in;
        String inputLine;
        String regex = "c[0-9]{3}z[0-9]{6}";
        String tmp = "" + args.getBegDate().getYear()
                + String.format("%02d", args.getBegDate().getMonthValue())
                + String.format("%02d", args.getBegDate().getDayOfMonth());
        int begDate = Integer.parseInt(tmp.substring(2));

        tmp = "" + args.getEndDate().getYear()
                + String.format("%02d", args.getEndDate().getMonthValue())
                + String.format("%02d", args.getEndDate().getDayOfMonth());
        int endDate= Integer.parseInt(tmp.substring(2));


        try {
            for (URL i : dirsURLs) {
                in = new BufferedReader(new InputStreamReader(i.openStream()));
                in.lines()
                        .filter( line-> Pattern.matches(regex,line))
                        .filter(line ->  Integer.parseInt(line.substring(5)) >= begDate && Integer.parseInt(line.substring(5)) <= endDate)
                        .map(this::createXMLURL)
                        .forEach(url -> xmlURLs.add(url));
                in.close();
            }
            System.out.println();
            xmlURLs.stream().forEach(System.out::println);

        } catch (IOException e) { System.err.print(e.getStackTrace()); }
    }

    private URL createXMLURL(String data){
        try{
            return new URL("http://www.nbp.pl/kursy/xml/"+data+".xml");

        }
        catch(MalformedURLException e){
            System.err.print(e.getStackTrace());
        }
        return null;

    }


    
    
}
