package ca.ulaval.glo4003.projet.base.ws.domain.spvq;

public class SpvqNumber {

  private String INVALID_SPVQ_NUMBER = "Invalid SPVQ Number";
  private String SPVQ_NUMBER_REGEX = "QUE+\\w{2}(0[1-9]|1[0-2])(0[1-9]|1[0-2])\\w{3}";

  private String spvqNumber;

  public SpvqNumber(String spvqNumber) {
    if (!this.isValid(spvqNumber)) {
      throw new InvalidSPVQNumberException(INVALID_SPVQ_NUMBER);
    }
    this.spvqNumber = spvqNumber;
  }

  private boolean isValid(String spvqNumber) {
    return spvqNumber.matches(SPVQ_NUMBER_REGEX);
  }
}
