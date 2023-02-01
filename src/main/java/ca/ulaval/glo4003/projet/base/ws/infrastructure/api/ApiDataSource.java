package ca.ulaval.glo4003.projet.base.ws.infrastructure.api;

import ca.ulaval.glo4003.projet.base.ws.domain.insurancepolicy.InsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler.QuoteToApiAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteApiDto;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteCalculatedDTO;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator.CalculatorException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class ApiDataSource {

  private MapperApiResponse mapperApiResponse;
  private String url;
  private QuoteToApiAssembler quoteToApiAssembler;

  public ApiDataSource(MapperApiResponse mapperApiResponse,
      QuoteToApiAssembler quoteToApiAssembler) {
    url = "https://ulaval-tenants-quote.herokuapp.com/quotes/home/tenants";
    this.mapperApiResponse = mapperApiResponse;
    this.quoteToApiAssembler = quoteToApiAssembler;
  }

  public QuoteCalculatedDTO call(QuoteApiDto quotedao) {
    try {
      String type = "application/json";
      String encodedData = URLEncoder.encode(quotedao.toString(), "UTF-8");
      URL calculatorApi = new URL(url);
      URLConnection urlConnection = calculatorApi.openConnection();
      HttpURLConnection http = (HttpURLConnection) urlConnection;
      http.setRequestMethod("POST"); // PUT is another valid option
      http.setDoOutput(true);
      http.setRequestProperty("Content-Type", type);

      http.setRequestProperty("Content-Length", String.valueOf(encodedData.length()));

      String datajson = writeToJson(quotedao);

      OutputStream os = urlConnection.getOutputStream();
      os.write(datajson.getBytes(StandardCharsets.UTF_8));
      os.close();

      InputStreamReader reader2 = new InputStreamReader(urlConnection.getInputStream());
      String response = new BufferedReader(reader2).lines().collect(Collectors.joining("\n"));
      return MapperApiResponse.mapQuoteToObject(response);
    } catch (Exception e) {
      throw new CalculatorException();
    }

  }

  public QuoteCalculatedDTO callForRenewal(InsurancePolicy insurancePolicy, Money newInsuranceMoneyAmount) {
    QuoteApiDto quotedao = quoteToApiAssembler.quoteToDao(insurancePolicy, newInsuranceMoneyAmount);
    QuoteCalculatedDTO quoteCalculatedDTO = call(quotedao);
    return quoteCalculatedDTO;
  }

  public QuoteCalculatedDTO callForQuoteOffer(Quote quote) {
    QuoteApiDto quotedao = quoteToApiAssembler.quoteToDao(quote);
    QuoteCalculatedDTO quoteCalculatedDTO = call(quotedao);
    return quoteCalculatedDTO;
  }

  private String writeToJson(QuoteApiDto quotedao) {

    return "{\"name\" : \"" + quotedao.name + "\", \"birthdate\" "
        + ":\"" + quotedao.birthdate + "\", \"sex\" "
        + ":\"" + quotedao.sex + "\", \"address\" : { "
        + " \"postalCode\" :\"" + quotedao.address.postalCode + "\", \"streetName\" :\""
        + "" + quotedao.address.streetName + "\",\"streetNumber\" :"
        + quotedao.address.streetNumber + ","
        + "\"floor\" :" + quotedao.address.floor + "},"
        + "\"effectiveDate\": \"" + quotedao.effectiveDate + "\","
        + "\"numberOfTenants\":" + quotedao.numberOfTenants + ","
        + "\"withBusiness\":\"" + quotedao.withBusiness + ""
        + "\",\"withSprinkler\":\"" + quotedao.withSprinkler + "\","
        + "\"withCentralAlarm\":\"" + quotedao.withCentralAlarm + "\""
        + ",\"insuredAmount\":" + quotedao.insuredAmount + " }";

  }

  public QuoteCalculatedDTO callForUpdate(InsurancePolicy insurancePolicy, Money newInsuranceAmount, BigDecimal newPublicLiabilityAmount) {
    QuoteApiDto quotedao = quoteToApiAssembler.quoteToDao(insurancePolicy, newInsuranceAmount);
    QuoteCalculatedDTO quoteCalculatedDTO = call(quotedao);
    return quoteCalculatedDTO;
  }
}
