package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.IdentityRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.common.email.EmailAddress;
import ca.ulaval.glo4003.projet.base.ws.domain.identity.Identity;

public class IdentityAssembler {

  public IdentityRequestDTO create(Identity identity) {
    IdentityRequestDTO identityRequestDTO = new IdentityRequestDTO();
    identityRequestDTO.name = identity.name;
    identityRequestDTO.birthday = identity.birthday;
    identityRequestDTO.email = identity.email.value;
    identityRequestDTO.gender = identity.gender;
    return identityRequestDTO;
  }

  public Identity create(IdentityRequestDTO identityRequestDTO) {
    Identity identity = new Identity();
    identity.name = identityRequestDTO.name;

    if (EmailAddress.isValidFormat(identityRequestDTO.email)) {
      identity.email = new EmailAddress(identityRequestDTO.email);
    }

    identity.gender = identityRequestDTO.gender;
    identity.birthday = identityRequestDTO.birthday;
    return identity;
  }
}
