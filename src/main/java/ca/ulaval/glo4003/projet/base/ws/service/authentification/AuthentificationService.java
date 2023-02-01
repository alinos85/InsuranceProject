package ca.ulaval.glo4003.projet.base.ws.service.authentification;

import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationToken;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.assembler.AuthentificationAssembler;

public class AuthentificationService {

  private AuthentificationAssembler authentificationAssembler;

  public AuthentificationService(AuthentificationRepository authentificationRepository,
      AuthentificationAssembler authentificationAssembler) {
    this.authentificationAssembler = authentificationAssembler;
  }

  public AuthentificationToken addTokenFromUser(User user) {
    AuthentificationToken authentificationToken = this.authentificationAssembler.create(user);
    return authentificationToken;
  }
}
