package ca.ulaval.glo4003.projet.base.ws.service.task.manager;

import ca.ulaval.glo4003.projet.base.ws.service.claim.CloseClaimsWithSPVQNumberExpired;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskManager implements Runnable {

  private CloseClaimsWithSPVQNumberExpired closeClaimsWithSPVQNumberExpired;
  private UserService userService;
  private ScheduledExecutorService scheduler;

  public TaskManager(CloseClaimsWithSPVQNumberExpired closeClaimsWithSPVQNumberExpired, ScheduledExecutorService scheduler, UserService userService) {
    this.closeClaimsWithSPVQNumberExpired = closeClaimsWithSPVQNumberExpired;
    this.scheduler = scheduler;
    this.userService = userService;
    scheduleTask();
  }

  private void scheduleTask() {
    long midnight = LocalDateTime.now().until(LocalDate.now().plusDays(1).atStartOfDay(), ChronoUnit.MINUTES);
    scheduler.scheduleAtFixedRate(this, midnight, TimeUnit.DAYS.toMinutes(1), TimeUnit.MINUTES);
  }

  @Override
  public void run() {
    this.closeClaimsWithSPVQNumberExpired.closeReclamationTask();
    userService.updateInsurancePolicies();
  }

}
