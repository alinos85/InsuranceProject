package ca.ulaval.glo4003.projet.base.ws.service.quote.assembler;

import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.AnimalRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.dto.QuoteRequestDTO;
import ca.ulaval.glo4003.projet.base.ws.domain.animal.Animal;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.Quote;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class QuoteAssembler {

  private IdentityAssembler identityAssembler;
  private ApartementDetailsAssembler apartementDetailsAssembler;
  private AddressAssembler addressAssembler;
  private AnimalAssembler animalAssembler;
  private MoneyAssembler moneyAssembler;
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

  public QuoteAssembler(IdentityAssembler identityAssembler,
      ApartementDetailsAssembler apartementDetailsAssembler, AddressAssembler addressAssembler,
      AnimalAssembler animalAssembler, MoneyAssembler moneyAssembler) {
    this.identityAssembler = identityAssembler;
    this.apartementDetailsAssembler = apartementDetailsAssembler;
    this.addressAssembler = addressAssembler;
    this.animalAssembler = animalAssembler;
    this.moneyAssembler = moneyAssembler;
  }

  public QuoteRequestDTO create(Quote quote) {
    QuoteRequestDTO quoteRequestDTO = new QuoteRequestDTO();
    quoteRequestDTO.effectiveDate = quote.getEffectiveDate().toString();
    quoteRequestDTO.identity = this.identityAssembler.create(quote.getIdentity());
    quoteRequestDTO.address = this.addressAssembler.create(quote.getAddress());
    quoteRequestDTO.apartmentDetails = this.apartementDetailsAssembler.create(quote.getApartmentDetails());
    quoteRequestDTO.animals = new ArrayList<>();
    quoteRequestDTO.publicLiabilityAmount = quote.getPublicLiability().getAmount().amount;
    for (Animal animal : quote.getAnimals()) {
      quoteRequestDTO.animals.add(this.animalAssembler.create(animal));
    }
    quoteRequestDTO.insuranceAmount = moneyAssembler.create(quote.getInsuranceAmount());
    quoteRequestDTO.publicLiabilityAmount = moneyAssembler.create(quote.getPublicLiability().amount);
    return quoteRequestDTO;
  }

  public Quote create(QuoteRequestDTO quoteRequestDTO) {
    List<Animal> animals = new ArrayList<>();
    for (AnimalRequestDTO animalRequestDTO : quoteRequestDTO.animals) {
      animals.add(this.animalAssembler.create(animalRequestDTO));
    }

    if (quoteRequestDTO.publicLiabilityAmount != null) {
      return createQuoteWithPublicLiabilityAmount(quoteRequestDTO, animals);
    }

    return createQuoteWithoutPublicLiabilityAmount(quoteRequestDTO, animals);
  }

  private Quote createQuoteWithPublicLiabilityAmount(QuoteRequestDTO quoteRequestDTO, List<Animal> animals) {
    return new Quote(
        LocalDate.parse(quoteRequestDTO.effectiveDate, formatter),
        this.identityAssembler.create(quoteRequestDTO.identity),
        this.addressAssembler.create(quoteRequestDTO.address),
        this.apartementDetailsAssembler.create(quoteRequestDTO.apartmentDetails),
        animals,
        moneyAssembler.create(quoteRequestDTO.insuranceAmount),
        moneyAssembler.create(quoteRequestDTO.publicLiabilityAmount)
    );
  }

  private Quote createQuoteWithoutPublicLiabilityAmount(QuoteRequestDTO quoteRequestDTO, List<Animal> animals) {
    return new Quote(
        LocalDate.parse(quoteRequestDTO.effectiveDate, formatter),
        this.identityAssembler.create(quoteRequestDTO.identity),
        this.addressAssembler.create(quoteRequestDTO.address),
        this.apartementDetailsAssembler.create(quoteRequestDTO.apartmentDetails),
        animals,
        this.moneyAssembler.create(quoteRequestDTO.insuranceAmount)
    );
  }
}


