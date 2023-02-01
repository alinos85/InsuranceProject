package ca.ulaval.glo4003.projet.base.ws.service.task.manager;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.service.claim.CloseClaimsWithSPVQNumberExpired;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class TaskManagerTest {

  @Mock
  private CloseClaimsWithSPVQNumberExpired spvqService;
  @Mock
  private UserService userService;
  @Mock
  private ScheduledExecutorService scheduledExecutorService;

  private TaskManager taskManager;

  @Before
  public void setUp() {
    initMocks(this);
  }

  @Test
  public void whenCreatingSPVQTaskManager_thenShouldScheduleSPVQServiceTask() {
    taskManager = new TaskManager(spvqService, scheduledExecutorService, userService);

    verify(scheduledExecutorService).scheduleAtFixedRate(any(TaskManager.class), any(long.class), any(long.class), any(TimeUnit.class));
  }
}