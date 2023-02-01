package ca.ulaval.glo4003.projet.base.ws.ui.rest;

import static com.jayway.restassured.RestAssured.given;

import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.dto.ClaimValueDetailsRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.AddressRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.AnimalRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.ApartementDetailsRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.IdentityRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.dto.SignupRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.ui.suites.LargeTestSuite;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestTestBase {

  protected RequestSpecification givenLargeBaseRequest() {
    return given().accept(ContentType.JSON).port(LargeTestSuite.TEST_SERVER_PORT).contentType(ContentType.JSON);
  }

  protected Map<String, Object> buildPathParams(String key, Object value) {
    Map<String, Object> params = new HashMap<>();
    params.put(key, value);
    return params;
  }

  public ClaimRequestDTO createClaim() {
    ClaimValueDetailsRequestDTO claimValueDetailsRequestDTO = new ClaimValueDetailsRequestDTO();
    claimValueDetailsRequestDTO.amount = new BigDecimal(100);
    claimValueDetailsRequestDTO.name = "Electronique";
    ClaimRequestDTO claimRequestDTO = new ClaimRequestDTO();
    claimRequestDTO.claimType = "THIEVERY";
    claimRequestDTO.claimValuesDetailsDTO = new ArrayList<>();
    claimRequestDTO.claimValuesDetailsDTO.add(claimValueDetailsRequestDTO);
    return claimRequestDTO;
  }

  public QuoteRequestDTO createQuoteResquestTomorrowDTO(String email) {
    AddressRequestDTO addressApiDto = new AddressRequestDTO();
    QuoteRequestDTO quoteApiDto = new QuoteRequestDTO();
    IdentityRequestDTO identityRequestDTO = new IdentityRequestDTO();
    ApartementDetailsRequestDTO apartementDetailsRequestDTO = new ApartementDetailsRequestDTO();

    addressApiDto.streetNumber = "2";
    addressApiDto.postalCode = "G1P 3A9";
    addressApiDto.apartmentNumber = "1";
    addressApiDto.floor = "2";
    quoteApiDto.address = addressApiDto;
    identityRequestDTO.birthday = "1992-12-12";
    quoteApiDto.effectiveDate = LocalDate.now().plusDays(1).toString();
    quoteApiDto.insuranceAmount = new BigDecimal(100);
    identityRequestDTO.name = "xavier";
    identityRequestDTO.gender = "male";
    identityRequestDTO.email = email;
    quoteApiDto.identity = identityRequestDTO;
    apartementDetailsRequestDTO.hasCommerce = true;
    apartementDetailsRequestDTO.hasAlarm = true;
    apartementDetailsRequestDTO.hasJet = true;
    apartementDetailsRequestDTO.nbOfHousing = 100;
    quoteApiDto.apartmentDetails = apartementDetailsRequestDTO;
    List<AnimalRequestDTO> animals = new ArrayList<>();
    quoteApiDto.animals = animals;

    return quoteApiDto;
  }

  public QuoteRequestDTO createQuoteResquestDTO(String email) {
    AddressRequestDTO addressApiDto = new AddressRequestDTO();
    QuoteRequestDTO quoteApiDto = new QuoteRequestDTO();
    IdentityRequestDTO identityRequestDTO = new IdentityRequestDTO();
    ApartementDetailsRequestDTO apartementDetailsRequestDTO = new ApartementDetailsRequestDTO();

    addressApiDto.streetNumber = "2";
    addressApiDto.postalCode = "G1P 3A9";
    addressApiDto.apartmentNumber = "1";
    addressApiDto.floor = "2";
    quoteApiDto.address = addressApiDto;
    identityRequestDTO.birthday = "1992-12-12";
    quoteApiDto.effectiveDate = LocalDate.now().toString();
    quoteApiDto.insuranceAmount = new BigDecimal(100);
    identityRequestDTO.name = "xavier";
    identityRequestDTO.gender = "male";
    identityRequestDTO.email = email;
    quoteApiDto.identity = identityRequestDTO;
    apartementDetailsRequestDTO.hasCommerce = true;
    apartementDetailsRequestDTO.hasAlarm = true;
    apartementDetailsRequestDTO.hasJet = true;
    apartementDetailsRequestDTO.nbOfHousing = 100;
    quoteApiDto.apartmentDetails = apartementDetailsRequestDTO;
    List<AnimalRequestDTO> animals = new ArrayList<>();
    quoteApiDto.animals = animals;

    return quoteApiDto;
  }

  public String getClaimId(String authorzationToken, ClaimRequestDTO claimRequestDTO) {
    return givenLargeBaseRequest()
        .header("Authorization", authorzationToken)
        .body(claimRequestDTO)
        .when()
        .post("/api/claims").andReturn().getBody().peek().jsonPath().getString("idClaim");
  }

  public String getInsurancePolicyId(String authorzationToken, String quoteId) {
    return givenLargeBaseRequest()
        .header("Authorization", authorzationToken)
        .pathParam("id", quoteId)
        .when()
        .post("/api/quote/offer/{id}/accept")
        .andReturn().getBody().peek().jsonPath().getString("insurancePolicyId");
  }

  public String getAuthorization(SignupRequestDTO signupRequestDTO) {
    return givenLargeBaseRequest()
        .body(signupRequestDTO)
        .when().post("/api/user/student/signup").andReturn().getHeader("Authorization");
  }

  public String getAuthorizationActuary(SignupRequestDTO actuarySign) {
    return givenLargeBaseRequest()
        .body(actuarySign)
        .when().post("/api/user/actuary/signup").andReturn().getHeader("Authorization");
  }

  public String getOfferId(QuoteRequestDTO quoteApiDto) {
    return givenLargeBaseRequest()
        .body(quoteApiDto)
        .when().post("/api/quote").andReturn().getBody().peek().jsonPath().getString("offerId");
  }

  public String getOfferIdToAccept(QuoteRequestDTO quoteApiDtoToAccept) {
    return givenLargeBaseRequest()
        .body(quoteApiDtoToAccept)
        .when().post("/api/quote").andReturn().getBody().peek().jsonPath().getString("offerId");
  }

}
