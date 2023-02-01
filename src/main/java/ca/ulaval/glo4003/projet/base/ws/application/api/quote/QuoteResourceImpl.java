package ca.ulaval.glo4003.projet.base.ws.application.api.quote;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.InsurancePolicyResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.AcceptInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.offer.GenerateQuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.service.quote.GetQuoteInformation;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.net.URI;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

public class QuoteResourceImpl implements QuoteResource {

  private static final Logger LOGGER = Logger.getLogger(AuthentificationService.class.getName());
  private UserService userService;
  private GenerateQuoteOffer generateQuoteOffer;
  private GetQuoteInformation getQuoteInformation;
  private AcceptInsurancePolicy acceptInsurancePolicy;

  public QuoteResourceImpl(UserService userService, GenerateQuoteOffer generateQuoteOffer, GetQuoteInformation getQuoteInformation,
      AcceptInsurancePolicy acceptInsurancePolicy) {
    this.userService = userService;
    this.generateQuoteOffer = generateQuoteOffer;
    this.getQuoteInformation = getQuoteInformation;
    this.acceptInsurancePolicy = acceptInsurancePolicy;
  }

  @Override
  public Response createQuote(UriInfo uriInfo, QuoteRequestDTO quoteRequestDTO) {
    OfferResponseDTO offerResponseDTO = this.generateQuoteOffer.generateQuoteOffer(quoteRequestDTO);

    LOGGER.info("new quote offer added. Quote offer ID : " + offerResponseDTO.offerId);

    return Response
        .status(Response.Status.CREATED)
        .location(URI.create(uriInfo.getBaseUri() + "quote/offer/" + offerResponseDTO.offerId + "/accept"))
        .entity(offerResponseDTO)
        .build();
  }

  @Override
  public Response acceptQuoteOffer(String authorizationToken, String id) {
    String userEmail = userService.getUserEmailFromAuthorizationToken(authorizationToken);
    InsurancePolicyResponseDTO insurancePolicyResponseDTO = this.acceptInsurancePolicy.acceptOffer(userEmail, id);
    return Response
        .status(Response.Status.CREATED)
        .entity(insurancePolicyResponseDTO)
        .build();
  }

  @Override
  public Response getQuoteInformations(String id) {
    return Response
        .status(Status.OK)
        .entity(this.getQuoteInformation.findQuote(id))
        .build();
  }
}
