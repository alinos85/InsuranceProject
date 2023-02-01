package ca.ulaval.glo4003.projet.base.ws.application.api.claim;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.SPVQNumberRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.Claim;
import ca.ulaval.glo4003.projet.base.ws.service.actuary.UpdateClaimAmountRatio;
import ca.ulaval.glo4003.projet.base.ws.service.claim.AddSPVQNumberToClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GenerateClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GetClaimStatus;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import org.junit.Before;
import org.mockito.Mock;

public class ClaimResourceImplTest {

  private String ID_CLAIM = "1243533783";
  private String SPVQ_NUMBER = "QUE201212001";
  private String USER_EMAIL = "test@email.com";
  private String TOKEN_STRING = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJyYXBoYWVsLmdhdWRyZWF1bHRAZXZhLmNvb3AifQ.rwKVSvZa3IYdWvyIe2uTYc1lLTF23Q54QQtR5sO93FQ";

  @Mock
  private Claim claimMock;

  @Mock
  private ClaimRequestDTO claimRequestDTOMock;

  @Mock
  private SPVQNumberRequestDTO spvqNumberRequestDTOMock;

  @Mock
  private ClaimResourceImpl claimResource;

  @Mock
  private UserService userServiceMock;

  @Mock
  private GenerateClaim generateClaim;

  @Mock
  private AddSPVQNumberToClaim addSPVQNumberToClaim;

  @Mock
  private UpdateClaimAmountRatio updateClaimAmountRatio;

  @Mock
  private GetClaimStatus getClaimStatus;

  @Before
  public void setUp() {
    initMocks(this);

    claimResource = new ClaimResourceImpl(userServiceMock, generateClaim, getClaimStatus, addSPVQNumberToClaim, updateClaimAmountRatio);

    willReturn(ID_CLAIM).given(generateClaim).addClaim(USER_EMAIL, claimRequestDTOMock);

  }

//  @Test
//  public void givenAClaimDTO_whenAddingAClaim_shouldSaveClaim() {
//
//    claimResource.addClaim(TOKEN_STRING, claimRequestDTOMock);
//    verify(generateClaim).addClaim(TOKEN_STRING, claimRequestDTOMock);
//  }
//
//  @Test
//  public void givenAId_whenGettingclaim_shouldGetClaimStatus() {
//    claimResource.getClaimStatus(TOKEN_STRING, ID_CLAIM);
//
//    verify(getClaimStatus).getClaimStatus(TOKEN_STRING, ID_CLAIM);
//  }
//  @Test
//  public void givenAQuoteDTO_whenCreatingAQuote_shouldSaveQuote() {
//    SpvqNumber spvqNumber = new SpvqNumber(SPVQ_NUMBER);
//    addSPVQNumberToClaim.addSPVQNumber(USER_EMAIL, ID_CLAIM, SPVQ_NUMBER);
//    claimResource.addSPVQNumber(TOKEN_STRING, ID_CLAIM, spvqNumber.toString());
//
//    verify(addSPVQNumberToClaim).addSPVQNumber(USER_EMAIL, ID_CLAIM, SPVQ_NUMBER);
//  }

}