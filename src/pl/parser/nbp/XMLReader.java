package pl.parser.nbp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class XMLReader {

    private DataContainer dc;

    XMLReader(){
        dc = new DataContainer();
    }

    public void read(ArrayList<URL> listToRead, String currency) {
        try {
            BufferedReader in;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            for (URL i : listToRead) {
                InputStreamReader is = new InputStreamReader(i.openStream());
                Document doc = dBuilder.parse(new InputSource(is));
                doc.getDocumentElement().normalize();

                //TODO porównać date publikacji z datą wczytywaną z pliku dla pewności
                NodeList pubDate = doc.getElementsByTagName("data_publikacji");
                System.out.println(pubDate.item(0).getTextContent());

                NodeList nList = doc.getElementsByTagName("pozycja");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        if(currency.equals( eElement.getElementsByTagName("kod_waluty").item(0).getTextContent())){
                            dc.addNewData( eElement.getElementsByTagName("kurs_sprzedazy").item(0).getTextContent(),
                                    eElement.getElementsByTagName("kurs_kupna").item(0).getTextContent());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); }
        dc.calculations.average();
        System.out.println();
        dc.calculations.standardDeviation();
    }
}
