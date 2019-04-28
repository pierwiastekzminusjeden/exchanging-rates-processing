package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;


public class ConnectionHandler {
    RunArgsHandler args;
    ArrayList<URL> dirsURLs;
    ArrayList<String> xmlList;

    ConnectionHandler(RunArgsHandler args) {
        this.args = args;
        dirsURLs = new ArrayList<>();
        xmlList = new ArrayList<>();
        buildDirsList();
    }

    private void buildDirsList() {

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
        dirsURLs.stream().forEach(System.out::println);
    }

    public void run() {
        BufferedReader in;
        String inputLine;

        try {
            for (URL i : dirsURLs) {
                in = new BufferedReader(new InputStreamReader(i.openStream()));

                while ((inputLine = in.readLine()) != null)
                    System.out.println(inputLine);
                in.close();

            }
        } catch (IOException e) { System.err.print(e.getStackTrace()); }
    }


    
    
}
