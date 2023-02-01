package ca.ulaval.glo4003.projet.base.ws.ui.suites;

import ca.ulaval.glo4003.projet.base.contexts.LargeContext;
import ca.ulaval.glo4003.projet.base.ws.ui.rest.ActuaryApplicationRestTest;
import ca.ulaval.glo4003.projet.base.ws.ui.rest.ApplicationRestTest;
import ca.ulaval.glo4003.projet.base.ws.ui.rest.ClaimApplicationRestTest;
import org.eclipse.jetty.server.Server;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ApplicationRestTest.class, ClaimApplicationRestTest.class, ActuaryApplicationRestTest.class})
public class LargeTestSuite {

  private static Server server;
  public static final int TEST_SERVER_PORT = 9191;

  @BeforeClass
  public static void setUpClass() {

    try {
      LargeContext largeContexts = new LargeContext();
      System.out.println("test server");
      server = new Server(TEST_SERVER_PORT);
      largeContexts.configure(server);
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @AfterClass
  public static void tearDownClass() {
    if (server != null) {
      try {
        server.stop();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  private static void filldatabase() {

  }

}
