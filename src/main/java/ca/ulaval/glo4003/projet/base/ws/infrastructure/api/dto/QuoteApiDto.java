package ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler.Gender;
import java.math.BigDecimal;

public class QuoteApiDto {

  public String name;
  public String birthdate;
  public Gender sex;
  public String effectiveDate;
  public BigDecimal numberOfTenants;
  public boolean withBusiness;
  public boolean withSprinkler;
  public boolean withCentralAlarm;
  public int insuredAmount;
  public AddressApiDto address;

}
