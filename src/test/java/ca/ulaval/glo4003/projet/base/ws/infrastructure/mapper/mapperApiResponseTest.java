package ca.ulaval.glo4003.projet.base.ws.infrastructure.mapper;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.MapperApiResponse;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.dto.QuoteCalculatedDTO;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator.CalculatorException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class mapperApiResponseTest {

  QuoteCalculatedDTO quoteCalculatedDTO;
  BigDecimal price = new BigDecimal(500);
  private String parseQuote = "{ \"price\" : 500 }";
  private String invalidParseQuote = "{ \"invalid\" : 500 }";
  private String apiResponse = "response";
  @Mock
  private ObjectMapper objectMapper;
  @InjectMocks
  private MapperApiResponse Mapper;

  @Before
  public void setUp() throws IOException {
    initMocks(this);
    quoteCalculatedDTO = new QuoteCalculatedDTO();
    quoteCalculatedDTO.price = new BigDecimal(500);

    willThrow(CalculatorException.class).given(objectMapper).readValue(invalidParseQuote, QuoteCalculatedDTO.class);
  }

  @Test
  public void givenApiStringResponse_whenConvertItToBasicClass_thenReturnObjectWithType() throws IOException {
    when(objectMapper.readValue(apiResponse, QuoteCalculatedDTO.class)).thenReturn(quoteCalculatedDTO);
    QuoteCalculatedDTO quote = Mapper.mapQuoteToObject(apiResponse);

    verify(objectMapper).readValue(apiResponse, QuoteCalculatedDTO.class);
    assertEquals(quoteCalculatedDTO.price, new BigDecimal(500));
  }

  @Test(expected = CalculatorException.class)
  public void givenInvalidQuoteCalculatedFormat_whenConvertResponseToQuoteCalculated_thenThrowCalculatorException() throws IOException {
    QuoteCalculatedDTO quote = Mapper.mapQuoteToObject(invalidParseQuote);
    verify(objectMapper).readValue(invalidParseQuote, QuoteCalculatedDTO.class);
  }

}
