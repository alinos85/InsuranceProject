package ca.ulaval.glo4003.projet.base.ws.infrastructure.api;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteCalculatedDTO;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator.CalculatorException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperApiResponse {

  public static ObjectMapper objectMapper;

  public MapperApiResponse(ObjectMapper objectMapper) {
    MapperApiResponse.objectMapper = objectMapper;
  }

  public static QuoteCalculatedDTO mapQuoteToObject(String apiResponse) {
    try {
      QuoteCalculatedDTO quote = objectMapper.readValue(apiResponse, QuoteCalculatedDTO.class);
      return quote;
    } catch (Exception e) {
      throw new CalculatorException();
    }
  }

}
