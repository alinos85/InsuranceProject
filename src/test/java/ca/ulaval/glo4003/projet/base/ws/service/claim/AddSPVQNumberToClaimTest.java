package ca.ulaval.glo4003.projet.base.ws.service.claim;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class AddSPVQNumberToClaimTest {

  private String USER_EMAIL = "email@test.com";
  private String SPVQ_NUMBER = "QUE121212001";
  private String ID_CLAIM = UUID.randomUUID().toString();

  @Mock
  private UserRepository userRepository;

  @Mock
  private MessageService messageService;

  private AddSPVQNumberToClaim addSPVQNumberToClaim;

  @Mock
  private Student student;

  @Before
  public void setUp() {
    initMocks(this);
    addSPVQNumberToClaim = new AddSPVQNumberToClaim(userRepository, messageService);

    willReturn(student).given(userRepository).findStudentByEmail(USER_EMAIL);
  }

  @Test
  public void givenAIdClaim_whenAddSPVQNumber_thenAddSPVQNumber() {
    addSPVQNumberToClaim.addSPVQNumber(USER_EMAIL, ID_CLAIM, SPVQ_NUMBER);

    verify(student).addSPVQNumberToClaim(any(), any());

  }
}