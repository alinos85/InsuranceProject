package ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

public abstract class EmailMecanism {

  public String mecanismEmail(String template) {
    ClassLoader classLoader = getClass().getClassLoader();
    URL resource = classLoader.getResource(template);
    File htmlTemplateFile = new File(resource.getFile());

    String htmlString = null;

    try {
      htmlString = FileUtils.readFileToString(htmlTemplateFile, "UTF-8");
    } catch (IOException e) {
      e.printStackTrace();
    }

    return htmlString;
  }

}
