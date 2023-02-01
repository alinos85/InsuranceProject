package ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details;

public class ComputerEquipments extends ClaimValueDetails {

  private final String COMPUTER_EQUIPMENTS = "Équipements informatiques";

  public ComputerEquipments() {
  }

  @Override
  public String getName() {
    return this.COMPUTER_EQUIPMENTS;
  }
}
