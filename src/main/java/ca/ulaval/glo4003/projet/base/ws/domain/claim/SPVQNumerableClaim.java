package ca.ulaval.glo4003.projet.base.ws.domain.claim;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.Messageable;
import ca.ulaval.glo4003.projet.base.ws.domain.spvq.SpvqNumber;

public interface SPVQNumerableClaim {

  void addSPVQNumber(SpvqNumber spvqNumber);

  void expireIfSPVQNumberNotSuppliedBeforeDeadline(MessageService messageService, Messageable user);

}
