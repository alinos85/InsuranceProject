package ca.ulaval.glo4003.projet.base.contexts;

public class ServiceLocatorTestData {

  private static ServiceLocatorTestData staticServiceLocator = null;
  private String idOffer = null;
  private String idOfferTomorrow = null;
  private String idAcceptOffer = null;
  private String idClaim = null;
  private String idClaim2 = null;
  private String token = null;
  private String tokenTomorrow = null;
  private String tokenClaim = null;
  private String idInsurancePolicy = null;
  private String idInsurancePolicyFuture = null;
  private String idInsurancePolicyClaim = null;
  private String tokenActuary = null;
  private String tokenActuary2 = null;

  public static ServiceLocatorTestData getInstance() {
    if (staticServiceLocator == null) {
      staticServiceLocator = new ServiceLocatorTestData();
    }

    return staticServiceLocator;
  }

  public String getIdOffer() {
    return idOffer;
  }

  public void setIdOffer(String idOffer) {
    this.idOffer = idOffer;
  }

  public String getIdAcceptOffer() {
    return idAcceptOffer;
  }

  public void setIdAcceptOffer(String idAcceptOffer) {
    this.idAcceptOffer = idAcceptOffer;
  }

  public String getIdClaim() {
    return idClaim;
  }

  public void setIdClaim(String idClaim) {
    this.idClaim = idClaim;
  }

  public String getIdClaim2() {
    return idClaim2;
  }

  public void setIdClaim2(String idClaim) {
    this.idClaim2 = idClaim;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getTokenFutur() {
    return tokenTomorrow;
  }

  public void setTokenFutur(String token) {
    this.tokenTomorrow = token;
  }

  public String getTokenTomorrow() {
    return tokenTomorrow;
  }

  public void setTokenTomorrow(String token) {
    this.tokenTomorrow = token;
  }

  public String getTokenClaim() {
    return tokenClaim;
  }

  public void setTokenClaim(String token) {
    this.tokenClaim = token;
  }

  public String getIdInsurancePolicy() {
    return idInsurancePolicy;
  }

  public void setIdInsurancePolicy(String idInsurancePolicy) {
    this.idInsurancePolicy = idInsurancePolicy;
  }

  public String getIdInsurancePolicyClaim() {
    return idInsurancePolicyClaim;
  }

  public void setIdInsurancePolicyClaim(String idInsurancePolicy) {
    this.idInsurancePolicyClaim = idInsurancePolicy;
  }

  public String getTokenActuary() {
    return tokenActuary;
  }

  public void setTokenActuary(String tokenActuary) {
    this.tokenActuary = tokenActuary;
  }

  public String getTokenActuary2() {
    return tokenActuary2;
  }

  public void setTokenActuary2(String tokenActuary) {
    this.tokenActuary2 = tokenActuary;
  }

  public String getIdInsurancePolicyTomorrow() {
    return idInsurancePolicyFuture;
  }

  public void setIdInsurancePolicyTomorrow(String insurancePolicyIdTomorrow) {
    this.idInsurancePolicyFuture = insurancePolicyIdTomorrow;
  }

  public String getIdAcceptOfferTommorrow() {
    return idOfferTomorrow;
  }

  public void setIdAcceptOfferTommorrow(String quoteIdToAcceptTomorrow) {
    idOfferTomorrow = quoteIdToAcceptTomorrow;
  }
}
