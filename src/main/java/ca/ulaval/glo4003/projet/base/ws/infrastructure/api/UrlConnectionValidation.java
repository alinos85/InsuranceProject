package ca.ulaval.glo4003.projet.base.ws.infrastructure.api;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlConnectionValidation {

  public boolean pingHost(String address) {
    boolean result = false;
    try {
      final URL url = new URL(address);
      final HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
      urlConn.setConnectTimeout(1000 * 2); // mTimeout is in seconds
      urlConn.connect();
      int code = urlConn.getResponseCode();
      result = code >= 200 && code < 500;
    } catch (MalformedURLException e) {
      e.printStackTrace();
      result = false;
    } finally {
      return result;
    }
  }
}


