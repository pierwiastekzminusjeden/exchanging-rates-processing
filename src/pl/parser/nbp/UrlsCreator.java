package pl.parser.nbp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class UrlsCreator {
    private LocalDate begDate;
    private LocalDate endDate;
    private ArrayList<URL> dirsURLs;
    private ArrayList<URL> xmlURLs;

    UrlsCreator(LocalDate begDate, LocalDate endDate){
        dirsURLs = new ArrayList<>();
        xmlURLs = new ArrayList<>();
        this.begDate = begDate;
        this.endDate = endDate;
    }

    public ArrayList<URL> create(){
        try {
            createDirsUrls();
            createXmlUrls();
        } catch (IOException e) { System.err.print(e.getStackTrace()); }
        return xmlURLs;
    }

    private void createDirsUrls() throws MalformedURLException{
        int begYear = begDate.getYear();
        int endYear = endDate.getYear();

        if (begYear == LocalDate.now().getYear())
            dirsURLs.add(new URL("http://www.nbp.pl/kursy/xml/dir.txt"));
        else if (endYear == LocalDate.now().getYear()) {
            for (int i = begYear; i <= endYear - 1; i++)
                dirsURLs.add(new URL("http://www.nbp.pl/kursy/xml/" + "dir" + i + ".txt"));
            dirsURLs.add(new URL("http://www.nbp.pl/kursy/xml/dir.txt"));
        }
        else
            for (int i = begYear; i <= endYear; i++)
                dirsURLs.add(new URL("http://www.nbp.pl/kursy/xml/" + "dir" + i + ".txt"));
    }

    private void createXmlUrls() throws IOException{
        BufferedReader in;
        String regex = "c[0-9]{3}z[0-9]{6}";
        String tmp = "" + begDate.getYear()
                + String.format("%02d", begDate.getMonthValue())
                + String.format("%02d", begDate.getDayOfMonth());
        int begInt = Integer.parseInt(tmp.substring(2));

        tmp = "" + endDate.getYear()
                + String.format("%02d", endDate.getMonthValue())
                + String.format("%02d", endDate.getDayOfMonth());
        int endInt = Integer.parseInt(tmp.substring(2));

        for (URL i : dirsURLs) {
            in = new BufferedReader(new InputStreamReader(i.openStream()));
            in.lines()
                    .filter( line-> Pattern.matches(regex,line))
                    .filter(line ->  Integer.parseInt(line.substring(5)) >= begInt &&
                            Integer.parseInt(line.substring(5)) <= endInt)
                    .map(this::createSingleXmlUrl)
                    .forEach(url -> xmlURLs.add(url));
            in.close();
        }
    }

    private URL createSingleXmlUrl(String data){
        try{
            return new URL("http://www.nbp.pl/kursy/xml/"+data+".xml");
        } catch(MalformedURLException e){ System.err.print(e.getStackTrace()); }
        return null;
    }


}
