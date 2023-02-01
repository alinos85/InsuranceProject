package ca.ulaval.glo4003.projet.base.ws.domain.common.email;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddress {

  public String value;

  public EmailAddress(String email) {
    if (!EmailAddress.isValidFormat(email)) {
      throw new EmailFormatInvalidException(email);
    }
    this.value = email;
  }

  public static boolean isValidFormat(String email) {
    String regex = "^(.+)@(.+)$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public boolean isEqual(EmailAddress other) {
    return this.value.equalsIgnoreCase(other.value);
  }
}
