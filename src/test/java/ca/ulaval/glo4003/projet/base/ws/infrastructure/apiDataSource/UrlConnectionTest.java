package ca.ulaval.glo4003.projet.base.ws.infrastructure.apiDataSource;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.UrlConnectionValidation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UrlConnectionTest {

  UrlConnectionValidation urlConnectionValidation;

  @Before
  public void setUp() {
    urlConnectionValidation = new UrlConnectionValidation();
  }

  @Test
  public void givenValidUrl_whenCheckForPing_thenGetTrue() {
    boolean ping = urlConnectionValidation.pingHost("https://ulaval-tenants-quote.herokuapp.com/quotes/home/tenants");
    Assert.assertTrue(ping);
  }

}
