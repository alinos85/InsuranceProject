package ca.ulaval.glo4003.projet.base.ws.infrastructure.quote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteRepository;
import org.junit.Before;
import org.junit.Test;

public class QuoteRepositoryInMemoryTest {

  private int numberOfQuotes;
  private Quote quote1;
  private Quote quote2;
  private QuoteRepository quoteRepository;

  @Before
  public void setUp() {
    initMocks(this);
    quoteRepository = new QuoteRepositoryInMemory();

    quote1 = mock(Quote.class);
    when(quote1.getId()).thenReturn("quote1");

    quote2 = mock(Quote.class);
    when(quote2.getId()).thenReturn("quote2");

    numberOfQuotes = 2;
    this.quoteRepository.save(quote1);
    this.quoteRepository.save(quote2);
  }

  @Test
  public void givenNonEmptyRepository_whenFindAllQuotes_thenListShouldHaveTheRightSize() {
    assertEquals(numberOfQuotes, quoteRepository.findAll().size());
  }

  @Test
  public void givenAnExistingId_whenFindingQuote_thenReturnQuote() {
    Quote returnedQuote = quoteRepository.findById(quote1.getId());

    assertEquals(returnedQuote, quote1);
  }

  @Test
  public void givenANonExistingId_whenFindingQuote_thenReturnNull() {
    assertNull(quoteRepository.findById("NOT EXISTING ID"));
  }
}