package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.ApartementDetailsRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.apartment.details.ApartmentDetails;

public class ApartementDetailsAssembler {

  public ApartementDetailsRequestDTO create(ApartmentDetails apartmentDetails) {
    ApartementDetailsRequestDTO apartementDetailsRequestDTO = new ApartementDetailsRequestDTO();
    apartementDetailsRequestDTO.nbOfHousing = apartmentDetails.getNbOfHousing();
    apartementDetailsRequestDTO.hasAlarm = apartmentDetails.getHasAlarm();
    apartementDetailsRequestDTO.hasCommerce = apartmentDetails.getHasCommerce();
    apartementDetailsRequestDTO.hasJet = apartmentDetails.getHasJet();
    return apartementDetailsRequestDTO;
  }

  public ApartmentDetails create(ApartementDetailsRequestDTO apartementDetailsRequestDTO) {
    ApartmentDetails apartmentDetails = new ApartmentDetails();
    apartmentDetails.setNbOfHousing(apartementDetailsRequestDTO.nbOfHousing);
    apartmentDetails.setHasAlarm(apartementDetailsRequestDTO.hasAlarm);
    apartmentDetails.setHasCommerce(apartementDetailsRequestDTO.hasCommerce);
    apartmentDetails.setHasJet(apartementDetailsRequestDTO.hasJet);
    return apartmentDetails;
  }
}
