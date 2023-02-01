package ca.ulaval.glo4003.projet.base.ws.application.api.offer;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.InsurancePolicyResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.service.offer.AcceptOffer;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OfferResourceImplTest {

  private static final String AUTH_TOKEN = "AUTH_TOKEN";
  private static final String OFFER_ID = "OFFER_ID";
  private static final String USER_EMAIL = "email@test.com";
  private static final String INSURANCE_POLICY_ID = "INSURANCE_POLICY_ID";

  private OfferResourceImpl offerResource;
  private InsurancePolicyResponseDTO insurancePolicyResponseDTO;

  @Mock
  AcceptOffer acceptOffer;

  @Mock
  private UserService userService;

  @Before
  public void setUp() {

    initMocks(true);

    offerResource = new OfferResourceImpl(acceptOffer, userService);
    insurancePolicyResponseDTO = new InsurancePolicyResponseDTO();
    insurancePolicyResponseDTO.insurancePolicyId = INSURANCE_POLICY_ID;

    willReturn(insurancePolicyResponseDTO).given(acceptOffer).acceptOffer(USER_EMAIL, OFFER_ID);
    willReturn(USER_EMAIL).given(userService).getUserEmailFromAuthorizationToken(AUTH_TOKEN);
  }

  @Test
  public void givenOfferId_whenAcceptingOffer_thenReturnResponseWithCreatedHeader() {

    Response response = offerResource.acceptOffer(AUTH_TOKEN, OFFER_ID);

    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenOfferId_whenAcceptingOffer_thenReturnResponseInsurancePolicyIdInEntity() {

    Response response = offerResource.acceptOffer(AUTH_TOKEN, OFFER_ID);

    assertEquals(insurancePolicyResponseDTO.insurancePolicyId, ((InsurancePolicyResponseDTO) response.getEntity()).insurancePolicyId);
  }

}