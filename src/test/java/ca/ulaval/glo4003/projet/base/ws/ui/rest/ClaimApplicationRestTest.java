package ca.ulaval.glo4003.projet.base.ws.ui.rest;

import ca.ulaval.glo4003.projet.base.contexts.ServiceLocatorTestData;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import org.junit.Before;
import org.junit.Test;

public class ClaimApplicationRestTest extends RestTestBase {

  private String claimid;
  private String authorzationToken;
  private ClaimRequestDTO claimRequestDTO;
  private SignupRequestDTO signinRequestDTO;

  @Before
  public void setUp() {
    signinRequestDTO = new SignupRequestDTO();
    signinRequestDTO.email = "claims@gmail.com";
    signinRequestDTO.password = "Password123";

    authorzationToken = ServiceLocatorTestData.getInstance().getTokenClaim();
    if (authorzationToken == null) {
      authorzationToken = getAuthorization(signinRequestDTO);
      ServiceLocatorTestData.getInstance().setTokenClaim(authorzationToken);
    }

    if (ServiceLocatorTestData.getInstance().getIdInsurancePolicyClaim() == null) {
      QuoteRequestDTO quoteApiDto = createQuoteResquestDTO("claims@gmail.com");
      String quoteOfferId = getOfferId(quoteApiDto);
      String insurancePolicyId = getInsurancePolicyId(authorzationToken, quoteOfferId);
      ServiceLocatorTestData.getInstance().setIdInsurancePolicyClaim(insurancePolicyId);
    }

    claimRequestDTO = createClaim();

    claimid = ServiceLocatorTestData.getInstance().getIdClaim2();
    if (claimid == null) {
      claimid = getClaimId(authorzationToken, claimRequestDTO);
      ServiceLocatorTestData.getInstance().setIdClaim2(claimid);
    }

  }

  @Test
  public void givenclaimrequest_whencreatingclaim_thenCreated() {

    givenLargeBaseRequest()
        .header("Authorization", authorzationToken)
        .body(claimRequestDTO)
        .when()
        .post("/api/claims")
        .then()
        .statusCode(201);

  }

  @Test
  public void givenClaimIdCreate_whenrequestforit_thenReturnit() {

    givenLargeBaseRequest()
        .header("Authorization", authorzationToken)
        .pathParam("id", claimid)
        .when()
        .get("/api/claims/{id}")
        .then()
        .statusCode(200);

  }

  @Test
  public void givenSPVQ_whensendIt_thenClaimAccepted() {

    givenLargeBaseRequest()
        .header("Authorization", authorzationToken)
        .pathParam("claimId", claimid)
        .pathParam("spvqId", "QUE121212001")
        .when()
        .post("/api/claims/{claimId}/spvq/{spvqId}/add")
        .then()
        .statusCode(202);

  }

}
