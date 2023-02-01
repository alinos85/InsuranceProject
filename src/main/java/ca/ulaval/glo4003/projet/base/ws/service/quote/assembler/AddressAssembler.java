package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.AddressRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.address.Address;

public class AddressAssembler {

  public AddressRequestDTO create(Address address) {
    AddressRequestDTO addressRequestDTO = new AddressRequestDTO();
    addressRequestDTO.apartmentNumber = address.getApartmentNumber();
    addressRequestDTO.floor = address.getFloor();
    addressRequestDTO.postalCode = address.getPostalCode();
    addressRequestDTO.streetNumber = address.getStreetNumber();
    return addressRequestDTO;
  }

  public Address create(AddressRequestDTO addressRequestDTO) {
    Address address = new Address();
    address.setApartmentNumber(addressRequestDTO.apartmentNumber);
    address.setFloor(addressRequestDTO.floor);
    address.setPostalCode(addressRequestDTO.postalCode);
    address.setStreetNumber(addressRequestDTO.streetNumber);
    return address;
  }
}
