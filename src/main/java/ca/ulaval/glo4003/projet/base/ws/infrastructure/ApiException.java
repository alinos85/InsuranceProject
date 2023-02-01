package ca.ulaval.glo4003.projet.base.ws.infrastructure;

public class ApiException extends RuntimeException {

  private final String error;
  private final int status;

  public ApiException(String description, String error, int status) {
    super(description);
    this.error = error;
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public int getStatus() {
    return status;
  }

}
