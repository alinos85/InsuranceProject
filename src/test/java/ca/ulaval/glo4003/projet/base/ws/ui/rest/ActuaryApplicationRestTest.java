package ca.ulaval.glo4003.projet.base.ws.ui.rest;

import ca.ulaval.glo4003.projet.base.contexts.ServiceLocatorTestData;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimSettingsRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import org.junit.Before;
import org.junit.Test;

public class ActuaryApplicationRestTest extends RestTestBase {

  private SignupRequestDTO newactuarySign;
  private SignupRequestDTO actuarySignLogin;
  private ClaimSettingsRequestDTO claimSettingsRequestDTO;
  private String authorzationTokenActuary;

  @Before
  public void setUp() {

    claimSettingsRequestDTO = new ClaimSettingsRequestDTO();
    newactuarySign = new SignupRequestDTO();
    newactuarySign.password = "Password123";
    newactuarySign.email = "actuary2@gmail.com";
    actuarySignLogin = new SignupRequestDTO();
    actuarySignLogin.password = "Password123";
    actuarySignLogin.email = "actuaryLogin@gmail.com";

    double d;
    d = 4.00;
    claimSettingsRequestDTO.claimsAmountRatio = d;

    authorzationTokenActuary = ServiceLocatorTestData.getInstance().getTokenActuary2();
    if (authorzationTokenActuary == null) {
      authorzationTokenActuary = getAuthorizationActuary(newactuarySign);
      ServiceLocatorTestData.getInstance().setTokenActuary2(authorzationTokenActuary);
    }

  }

  @Test
  public void givenValidEmail_whenSigninLikeActuary_thenAccept() {
    givenLargeBaseRequest()
        .body(actuarySignLogin)
        .when().post("/api/user/actuary/signup")
        .then()
        .statusCode(201);
  }

  @Test
  public void givenNewRatio_whenSigninLikeActuary_thenClaimRatioChange() {
    givenLargeBaseRequest()
        .header("Authorization", authorzationTokenActuary)
        .body(claimSettingsRequestDTO)
        .when().post("/api/claims/settings")
        .then()
        .statusCode(202);
  }
}
