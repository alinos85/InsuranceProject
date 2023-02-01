package ca.ulaval.glo4003.projet.base.ws.service.claim;

import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.student.Student;
import ca.ulaval.glo4003.projet.base.ws.domain.user.User;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class CloseClaimsWithSPVQNumberExpiredTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private MessageService messageService;

  private CloseClaimsWithSPVQNumberExpired closeClaimsWithSPVQNumberExpired;

  @Mock
  private Student student;

  @Before
  public void setUp() {
    initMocks(this);

    List<User> users = new ArrayList<>();
    users.add(student);

    closeClaimsWithSPVQNumberExpired = new CloseClaimsWithSPVQNumberExpired(userRepository, messageService);

    willReturn(users).given(userRepository).findAllStudents();
  }

  @Test
  public void givenAStudent_whenCloseReclamationTask_thenCloseClaimsWithoutSPVQNumberSuppliedBeforeDeadline() {
    closeClaimsWithSPVQNumberExpired.closeReclamationTask();

    verify(student).closeClaimsWithoutSpvqNumberSuppliedBeforeDeadline(any());
  }
}