package ca.ulaval.glo4003.projet.base.ws.domain.spvq;

import org.junit.Test;

public class SpvqNumberTest {

  private String VALID_SPVQ_NUMBER = "QUE201212001";
  private String INVALID_SPVQ_NUMBER = "XXXXX";
  private SpvqNumber spvqNumber;

  @Test
  public void givenValidSPVQNumber_whenCreatingSPVQNumber_thenShouldCreateNewSpvqNumber() {
    spvqNumber = new SpvqNumber(VALID_SPVQ_NUMBER);
  }

  @Test(expected = InvalidSPVQNumberException.class)
  public void givenInvalidSPVQNumber_whenCreatingSPVQNumber_thenShouldThrowInvalidSPVQNumberException() {
    spvqNumber = new SpvqNumber(INVALID_SPVQ_NUMBER);
  }
}