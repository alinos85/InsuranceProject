package ca.ulaval.glo4003.projet.base.ws.domain.money;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;

public class MoneyTest {

  @Test
  public void givenAnAmountThatShouldBeRoundedUp_whenCreatingMoney_thenShouldRoundUpTo2Decimals() {
    BigDecimal SHOULD_ROUND_UP = new BigDecimal(1.156543543);
    BigDecimal expected = new BigDecimal("1.16");

    Money money = new Money(SHOULD_ROUND_UP);

    assertEquals(money.amount, expected);
  }

  @Test
  public void givenAnAmountThatShouldBeRoundedDown_whenCreatingMoney_thenShouldRoundDownTo2Decimals() {
    BigDecimal SHOULD_ROUND_DOWN = new BigDecimal(1.1111);
    BigDecimal expected = new BigDecimal("1.11");

    Money money = new Money(SHOULD_ROUND_DOWN);

    assertEquals(money.amount, expected);
  }
}