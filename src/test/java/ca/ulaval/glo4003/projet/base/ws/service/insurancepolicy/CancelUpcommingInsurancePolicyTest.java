package ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CancelUpcommingInsurancePolicyTest {

  private String EMAIL = "EMAIL";

  @Mock
  private Student student;

  @Mock
  private UserRepository userRepository;

  private CancelUpcommingInsurancePolicy cancelUpcommingInsurancePolicy;

  @Before
  public void setUp() {
    initMocks(this);

    cancelUpcommingInsurancePolicy = new CancelUpcommingInsurancePolicy(userRepository);
    willReturn(student).given(userRepository).findStudentByEmail(EMAIL);
  }

  @Test
  public void givenAnEmail_whenCancelUpcommingInsurancePolicy_thenStudentCancelUpcommingInsurancePolicy() {
    cancelUpcommingInsurancePolicy.cancelUpcommingInsurancePolicy(EMAIL);

    verify(student).cancelUpcomingInsurancePolicy();
  }
}