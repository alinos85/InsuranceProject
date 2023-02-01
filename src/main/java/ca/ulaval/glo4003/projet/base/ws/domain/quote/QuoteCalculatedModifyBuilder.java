package ca.ulaval.glo4003.projet.base.ws.domain.quote;

import ca.ulaval.glo4003.projet.base.ws.domain.money.Money;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.DecreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.IncreasePriceModifierValue;
import ca.ulaval.glo4003.projet.base.ws.domain.pricemodifier.PriceModifierValue;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class QuoteCalculatedModifyBuilder {

  private List<PriceModifierValue> priceModifiers;

  public QuoteCalculatedModifyBuilder() {
    this.priceModifiers = new ArrayList<>();
  }

  public Money applyPriceModifiers(BigDecimal price) {
    BigDecimal modification = applyModifierValue(price);
    Money money = new Money(modification);
    return money;
  }

  public BigDecimal applyModifierValue(BigDecimal price) {
    BigDecimal priceModify = price.add(applyIncreaseModifierValue());
    return priceModify.subtract(applyDiscountModifierValue());
  }

  public BigDecimal applyIncreaseModifierValue() {
    return priceModifiers.stream()
        .filter(priceModifier -> priceModifier.getClass()
            == IncreasePriceModifierValue.class)
        .map(priceModifier -> priceModifier.value)
        .reduce(new BigDecimal(0), (x, y) -> x.add(y));
  }

  public BigDecimal applyDiscountModifierValue() {
    return priceModifiers.stream()
        .filter(priceModifier -> priceModifier.getClass()
            == DecreasePriceModifierValue.class)
        .map(priceModifier -> priceModifier.value)
        .reduce(new BigDecimal(0), (x, y) -> x.add(y));
  }

  public void addPriceModifier(PriceModifierValue priceModifierValue) {
    priceModifiers.add(priceModifierValue);
  }

  public void setPriceModifiers(List<PriceModifierValue> priceModifiers) {
    this.priceModifiers = priceModifiers;
  }
}
