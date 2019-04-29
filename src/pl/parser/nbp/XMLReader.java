package pl.parser.nbp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class allows reading xml files
 */
public class XMLReader {
    /**
     * Function reads multiple xml files and collects data about purchasing and selling currencies.
     * Function adds readed data to dataContainer object.
     * @param listToRead list of xml urls
     * @param dc dataContainer wchih stores collected data from xml files
     * @param currency
     */
    public static void read(ArrayList<URL> listToRead,DataContainer dc, String currency) {
        try {
            BufferedReader in;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            for (URL i : listToRead) {
                InputStreamReader is = new InputStreamReader(i.openStream());
                Document doc = dBuilder.parse(new InputSource(is));
                doc.getDocumentElement().normalize();

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
        } catch (ParserConfigurationException| SAXException| IOException e) {
            System.err.println(e.getStackTrace()); }
    }
}
