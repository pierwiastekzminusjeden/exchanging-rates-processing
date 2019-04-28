package pl.parser.nbp.connection;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionHandler {

    URL toConnect;
    URLConnection connection;


    ConnectionHandler(){
        try {
            toConnect = new URL("http://example.com/");
            connection = toConnect.openConnection();
            connection.connect();
        }
        catch (MalformedURLException e) {
            // new URL() failed
            // ...
        }
        catch (IOException e) {
            // openConnection() failed
            // ...
        }
    }



}
