package ca.ulaval.glo4003.projet.base.ws.application.api.quote;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.AcceptInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.offer.GenerateQuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.service.quote.GetQuoteInformation;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class QuoteResourceImplTest {

  private String EMAIL = null;
  private String QUOTE_ID = "232424244";
  private String AUTHENTICATION_TOKEN = "324hj2h3424mn24";

  @Mock
  private QuoteRequestDTO quoteRequestDTO;

  @Mock
  private UserService userService;

  @Mock
  private GenerateQuoteOffer generateQuoteOffer;

  @Mock
  private GetQuoteInformation getQuoteInformation;

  @Mock
  private OfferResponseDTO offerResponseDTO;

  @Mock
  private AcceptInsurancePolicy acceptInsurancePolicy;

  @Mock
  private UriInfo uriInfo;

  private QuoteResource quoteResource;

  @Before
  public void setUp() {
    initMocks(this);
    quoteResource = new QuoteResourceImpl(userService, generateQuoteOffer, getQuoteInformation, acceptInsurancePolicy);

    willReturn(offerResponseDTO).given(generateQuoteOffer).generateQuoteOffer(quoteRequestDTO);
  }

  @Test
  public void givenQuoteRequestDTO_whenCreateQuote_thenCreateQuote() {
    Response response = quoteResource.createQuote(uriInfo, quoteRequestDTO);

    verify(generateQuoteOffer).generateQuoteOffer(quoteRequestDTO);
    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenAuthentificationToken_whenAcceptingQuoteOffer_thenAcceptQuoteOffer() {
    Response response = quoteResource.acceptQuoteOffer(AUTHENTICATION_TOKEN, QUOTE_ID);

    verify(userService).getUserEmailFromAuthorizationToken(AUTHENTICATION_TOKEN);
    verify(acceptInsurancePolicy).acceptOffer(EMAIL, QUOTE_ID);
    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenQuoteId_whenGettingQuoteInformations_thenGetQuoteInformations() {
    Response response = quoteResource.getQuoteInformations(QUOTE_ID);

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }
}