package ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.OfferResponseDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.RenewInsurancePolicyRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.dto.UpdatePolicyDTO;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.CancelUpcommingInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.RenewInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.UpdateInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.math.BigDecimal;
import java.net.URI;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

//import ca.ulaval.glo4003.projet.base.ws.service.informations.InformationsService;

@RunWith(MockitoJUnitRunner.class)
public class InsurancePolicyResourceImplTest {

  private static final BigDecimal VALID_INSURANCE_AMOUNT = new BigDecimal(10000);
  private static final BigDecimal VALID_PUBLIC_LIABILITY_AMOUNT = new BigDecimal(2000000);
  private static final String USER_EMAIL = "email@test.com";
  private static final String OFFER_ID = "OFFER_ID";
  private URI BASE_URL = URI.create("BASE_URL/");
  private String TOKEN_STRING = "AUTH_TOKEN";

  @Mock
  private RenewInsurancePolicyService renewInsurancePolicyService;
  @Mock
  private UpdateInsurancePolicyService updateInsurancePolicyService;
  @Mock
  private CancelUpcommingInsurancePolicy cancelUpcommingInsurancePolicy;
  @Mock
  private UserService userService;
  @Mock
  private UriInfo uriInfo;

  private InsurancePolicyResource insurancePolicyResource;

  private UpdatePolicyDTO updatePolicyDTO;
  private OfferResponseDTO offerResponseDTO;
  private RenewInsurancePolicyRequestDTO renewInsurancePolicyRequestDTO;

  @Before
  public void setUp() {

    initMocks(true);

    insurancePolicyResource = new InsurancePolicyResourceImpl(userService, renewInsurancePolicyService, updateInsurancePolicyService,
        cancelUpcommingInsurancePolicy);
    offerResponseDTO = new OfferResponseDTO();
    offerResponseDTO.offerId = OFFER_ID;

    willReturn(offerResponseDTO).given(updateInsurancePolicyService)
        .updateInsurancePolicyRequest(USER_EMAIL, VALID_INSURANCE_AMOUNT, VALID_PUBLIC_LIABILITY_AMOUNT);
    willReturn(offerResponseDTO).given(renewInsurancePolicyService).renewInsurancePolicyRequest(USER_EMAIL, VALID_INSURANCE_AMOUNT);
    willReturn(USER_EMAIL).given(userService).getUserEmailFromAuthorizationToken(TOKEN_STRING);
    willReturn(BASE_URL).given(uriInfo).getBaseUri();
  }

  @Test
  public void givenUpdatePolicyDTO_whenUpdatingInsurancePolicy_thenReturnResponseWithAcceptOfferURLInLocation() {

    updatePolicyDTO = new UpdatePolicyDTO();
    updatePolicyDTO.insuranceAmount = VALID_INSURANCE_AMOUNT;
    updatePolicyDTO.publicLiabilityAmount = VALID_PUBLIC_LIABILITY_AMOUNT;

    Response response = insurancePolicyResource.updateInsurancePolicy(uriInfo, TOKEN_STRING, updatePolicyDTO);

    assertEquals(response.getLocation(), URI.create(BASE_URL.toString() + "quote/offer/" + offerResponseDTO.offerId + "/accept"));

  }

  @Test
  public void givenUpdatePolicyDTO_whenUpdatingInsurancePolicy_thenReturnResponseWithOfferIdInEntity() {

    updatePolicyDTO = new UpdatePolicyDTO();
    updatePolicyDTO.insuranceAmount = VALID_INSURANCE_AMOUNT;
    updatePolicyDTO.publicLiabilityAmount = VALID_PUBLIC_LIABILITY_AMOUNT;

    Response response = insurancePolicyResource.updateInsurancePolicy(uriInfo, TOKEN_STRING, updatePolicyDTO);

    assertEquals(offerResponseDTO.offerId, ((OfferResponseDTO) response.getEntity()).offerId);
  }

  @Test
  public void givenUpdatePolicyDTO_whenUpdatingInsurancePolicy_thenReturnResponseWithCreatedHeader() {

    updatePolicyDTO = new UpdatePolicyDTO();
    updatePolicyDTO.insuranceAmount = VALID_INSURANCE_AMOUNT;
    updatePolicyDTO.publicLiabilityAmount = VALID_PUBLIC_LIABILITY_AMOUNT;

    Response response = insurancePolicyResource.updateInsurancePolicy(uriInfo, TOKEN_STRING, updatePolicyDTO);

    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenRenewInsurancePolicyDTO_whenRenewingInsurancePolicy_thenReturnResponseWithAcceptOfferURLInLocation() {

    renewInsurancePolicyRequestDTO = new RenewInsurancePolicyRequestDTO();
    renewInsurancePolicyRequestDTO.insuranceAmount = VALID_INSURANCE_AMOUNT;

    Response response = insurancePolicyResource.renewInsurancePolicy(uriInfo, TOKEN_STRING, renewInsurancePolicyRequestDTO);

    assertEquals(response.getLocation(), URI.create(BASE_URL.toString() + "quote/offer/" + offerResponseDTO.offerId + "/accept"));

  }

  @Test
  public void givenRenewInsurancePolicyDTO_whenRenewingInsurancePolicy_thenReturnResponseWithCreatedHeader() {

    renewInsurancePolicyRequestDTO = new RenewInsurancePolicyRequestDTO();
    renewInsurancePolicyRequestDTO.insuranceAmount = VALID_INSURANCE_AMOUNT;

    Response response = insurancePolicyResource.renewInsurancePolicy(uriInfo, TOKEN_STRING, renewInsurancePolicyRequestDTO);

    assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }

  @Test
  public void givenRenewInsurancePolicyDTO_whenRenewingInsurancePolicy_thenReturnResponseWithOfferIdInEntity() {

    renewInsurancePolicyRequestDTO = new RenewInsurancePolicyRequestDTO();
    renewInsurancePolicyRequestDTO.insuranceAmount = VALID_INSURANCE_AMOUNT;

    Response response = insurancePolicyResource.renewInsurancePolicy(uriInfo, TOKEN_STRING, renewInsurancePolicyRequestDTO);

    assertEquals(offerResponseDTO.offerId, ((OfferResponseDTO) response.getEntity()).offerId);
  }

  @Test
  public void whenCancellingUpcommingInsurancePolicy_thenReturnResponseWithOKHeader() {

    Response response = insurancePolicyResource.cancelRenewInsurancePolicy(TOKEN_STRING);

    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  }

}