package ca.ulaval.glo4003.projet.base.ws.domain.claim.value.details;

import ca.ulaval.glo4003.projet.base.ws.domain.claim.exception.InvalidClaimCategoryException;

public class ClaimValueDetailsFactory {

  private static final String FURNITURE = "Meuble et electromenagers";
  private static final String ELECTRONICS = "Electronique";
  private static final String CLOTHES = "Vetements";
  private static final String COMPUTER_EQUIPMENTS = "equipements informatiques";
  private static final String OTHER_VALUE_DETAILS = "Autres biens";

  public static final ClaimValueDetails buildClaimValueDetails(String name) {
    ClaimValueDetails claimValueDetails;
    switch (name) {
      case CLOTHES:
        claimValueDetails = new Clothes();
        break;
      case ELECTRONICS:
        claimValueDetails = new Electronics();
        break;
      case FURNITURE:
        claimValueDetails = new Furniture();
        break;
      case COMPUTER_EQUIPMENTS:
        claimValueDetails = new ComputerEquipments();
        break;
      case OTHER_VALUE_DETAILS:
        claimValueDetails = new OtherValueDetails();
        break;
      default:
        throw new InvalidClaimCategoryException(name);
    }
    return claimValueDetails;
  }
}
