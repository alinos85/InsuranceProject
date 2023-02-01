package ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler;

import ca.ulaval.glo4003.projet.base.ws.domain.address.Address;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.AddressApiDto;
import java.math.BigDecimal;

public class AddressToApiAssembler {

  public AddressApiDto toDao(Address address) {
    AddressApiDto addressApiDto = new AddressApiDto();
    addressApiDto.floor = toBig(address.getFloor());
    addressApiDto.postalCode = address.getPostalCode();
    addressApiDto.streetNumber = toBig(address.getStreetNumber());
    addressApiDto.streetName = "Chemin Ste-Foy";
    return addressApiDto;
  }

  public BigDecimal toBig(String num) {

    int i = Integer.parseInt(num);
    return new BigDecimal(i);

  }

}
