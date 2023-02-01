package ca.ulaval.glo4003.projet.base.ws.ui.rest;

import ca.ulaval.glo4003.projet.base.contexts.ServiceLocatorTestData;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.RenewInsurancePolicyRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.UpdatePolicyDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

public class ApplicationRestTest extends RestTestBase {

  private QuoteRequestDTO quoteApiDto;
  private QuoteRequestDTO quoteApiDtoToAccept;
  private QuoteRequestDTO quoteApiDtoTomorrow;
  private SignupRequestDTO sign;
  private SignupRequestDTO newsign;
  private SignupRequestDTO newsignTommorrow;
  private String authorzationToken = "";
  private String authorzationTokenFutur = "";
  private String quoteId;
  private String quoteIdAccepted;
  private String quoteIdToAcceptTomorrow;
  private String insurancePolicyId;
  private String insurancePolicyIdTomorrow;
  private UpdatePolicyDTO updatePolicyDTO;
  private RenewInsurancePolicyRequestDTO renewInsurancePolicyRequestDTO;

  @Before
  public void setUp() {

    sign = new SignupRequestDTO();
    sign.password = "test";
    sign.email = "test@gmail.com";

    newsign = new SignupRequestDTO();
    newsign.password = "newtest";
    newsign.email = "newtest@gmail.com";

    newsignTommorrow = new SignupRequestDTO();
    newsignTommorrow.password = "Password123";
    newsignTommorrow.email = "testTommorrow@gmail.com";

    renewInsurancePolicyRequestDTO = new RenewInsurancePolicyRequestDTO();
    renewInsurancePolicyRequestDTO.insuranceAmount = new BigDecimal(1000);

    createClaim();

    updatePolicyDTO = new UpdatePolicyDTO();
    updatePolicyDTO.insuranceAmount = new BigDecimal(300);
    updatePolicyDTO.publicLiabilityAmount = new BigDecimal(2000000);

    quoteApiDtoToAccept = createQuoteResquestDTO("testAccept@gmail.com");
    quoteApiDto = createQuoteResquestDTO("test@gmail.com");
    quoteApiDtoTomorrow = createQuoteResquestTomorrowDTO("testTommorrow@gmail.com");

    quoteId = ServiceLocatorTestData.getInstance().getIdOffer();
    if (quoteId == null) {
      quoteId = getOfferId(quoteApiDtoToAccept);
      ServiceLocatorTestData.getInstance().setIdOffer(quoteId);
    }

    authorzationToken = ServiceLocatorTestData.getInstance().getToken();
    if (authorzationToken == null) {
      authorzationToken = getAuthorization(sign);
      ServiceLocatorTestData.getInstance().setToken(authorzationToken);
    }

    authorzationTokenFutur = ServiceLocatorTestData.getInstance().getTokenFutur();
    if (authorzationTokenFutur == null) {
      authorzationTokenFutur = getAuthorization(newsignTommorrow);
      ServiceLocatorTestData.getInstance().setTokenFutur(authorzationTokenFutur);
    }

    insurancePolicyId = ServiceLocatorTestData.getInstance().getIdInsurancePolicy();
    if (insurancePolicyId == null) {
      insurancePolicyId = getInsurancePolicyId(authorzationToken, quoteId);
      ServiceLocatorTestData.getInstance().setIdInsurancePolicy(insurancePolicyId);
    }

    quoteIdAccepted = ServiceLocatorTestData.getInstance().getIdAcceptOffer();
    if (quoteIdAccepted == null) {
      quoteIdAccepted = getOfferIdToAccept(quoteApiDtoToAccept);
      ServiceLocatorTestData.getInstance().setIdAcceptOffer(quoteIdAccepted);
    }

    quoteIdToAcceptTomorrow = ServiceLocatorTestData.getInstance().getIdAcceptOfferTommorrow();
    if (quoteIdToAcceptTomorrow == null) {
      quoteIdToAcceptTomorrow = getOfferId(quoteApiDtoTomorrow);
      ServiceLocatorTestData.getInstance().setIdAcceptOfferTommorrow(quoteIdToAcceptTomorrow);
    }

    insurancePolicyIdTomorrow = ServiceLocatorTestData.getInstance().getIdInsurancePolicyTomorrow();
    if (insurancePolicyIdTomorrow == null) {
      insurancePolicyIdTomorrow = getInsurancePolicyId(authorzationTokenFutur, quoteIdToAcceptTomorrow);
      ServiceLocatorTestData.getInstance().setIdInsurancePolicyTomorrow(insurancePolicyIdTomorrow);
    }

  }

  @Test
  public void givenValidQuote_whenLookingForQuote_thenCreateResponseWithPrice() {

    givenLargeBaseRequest()
        .body(quoteApiDto)
        .when()
        .post("/api/quote")
        .then()
        .statusCode(201);

  }

  @Test
  public void givennewUser_whenGoesToSignup_thenCan() {
    givenLargeBaseRequest()
        .body(newsign)
        .when().post("/api/user/student/signup")
        .then()
        .statusCode(201);
  }

  @Test
  public void givenValidQuoteId_whenLookforinformation_thenrecceiveanswer() {
    givenLargeBaseRequest()
        .pathParam("id", quoteIdAccepted)
        .when()
        .get("/api/quote/{id}")
        .then()
        .statusCode(200);
  }

  @Test
  public void givenRequestToUpdateContrat_whenContratValid_thenUpdateContract() {
    givenLargeBaseRequest()
        .header("Authorization", authorzationToken)
        .body(updatePolicyDTO)
        .when()
        .post("/api/insurancepolicy/update")
        .then()
        .statusCode(201);
  }

  @Test
  public void givenRequestToDeleteUpcomingInsurancePolicy_whenCUpcomingPolicy_thenDeleteIt() {
    givenLargeBaseRequest()
        .header("Authorization", authorzationTokenFutur)
        .when()
        .delete("/api/insurancepolicy/upcoming")
        .then()
        .statusCode(200);
  }

  @Test
  public void givenValidEmail_whenSignin_thenAccept() {
    givenLargeBaseRequest()
        .body(sign)
        .when().post("/api/user/signin")
        .then()
        .statusCode(200);
  }

}
