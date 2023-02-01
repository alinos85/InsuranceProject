package ca.ulaval.glo4003.projet.base.contexts;

import ca.ulaval.glo4003.projet.base.ws.ServiceLocator;
import ca.ulaval.glo4003.projet.base.ws.application.context.ApplicationContext;
import ca.ulaval.glo4003.projet.base.ws.domain.authentification.AuthentificationRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.claim.ClaimSettings;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageAbstractFactory;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageSender;
import ca.ulaval.glo4003.projet.base.ws.domain.communication.MessageService;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.OfferCalculator;
import ca.ulaval.glo4003.projet.base.ws.domain.offer.QuoteOfferRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.quote.QuoteRepository;
import ca.ulaval.glo4003.projet.base.ws.domain.user.UserRepository;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiDataSource;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.ApiService;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.MapperApiResponse;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.UrlConnectionValidation;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler.AddressToApiAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler.QuoteCalculatedAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.api.assembler.QuoteToApiAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.calculator.CalculatorProxy;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.CommuncationTechnic;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.EmailMessageSender;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.EmailSender;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.InformationAssembler;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.communication.message.EmailMessageAbstractFactory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.quote.OfferRepositoryInMemory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.quote.QuoteRepositoryInMemory;
import ca.ulaval.glo4003.projet.base.ws.infrastructure.user.UserRepositoryInMemory;
import ca.ulaval.glo4003.projet.base.ws.service.actuary.UpdateClaimAmountRatio;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.assembler.AuthentificationAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.claim.AddSPVQNumberToClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.CloseClaimsWithSPVQNumberExpired;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GenerateClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GetClaimStatus;
import ca.ulaval.glo4003.projet.base.ws.service.claim.assembler.ClaimAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.AcceptInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.CancelUpcommingInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.InsurancePolicyAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.RenewInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.UpdateInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.offer.AcceptOffer;
import ca.ulaval.glo4003.projet.base.ws.service.offer.GenerateQuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.service.quote.GetQuoteInformation;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.AddressAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.AnimalAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.ApartementDetailsAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.IdentityAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.MoneyAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.OfferAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.quote.assembler.QuoteAssembler;
import ca.ulaval.glo4003.projet.base.ws.service.task.manager.TaskManager;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import ca.ulaval.glo4003.projet.base.ws.service.user.assembler.UserAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Clock;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TestContext extends ApplicationContext {

  public TestContext() {
  }

  @Override
  public void apply() {
    this.registerServiceLocator();
    this.createDemoData();
  }

  private void registerServiceLocator() {

    //Clock
    ServiceLocator.getInstance().register(Clock.class, Clock.systemDefaultZone());

    //Repository
    ServiceLocator.getInstance().register(QuoteRepository.class, new QuoteRepositoryInMemory());

    ServiceLocator.getInstance().register(QuoteOfferRepository.class, new OfferRepositoryInMemory());
    ServiceLocator.getInstance().register(UserRepository.class, new UserRepositoryInMemory());

    ServiceLocator.getInstance().register(CancelUpcommingInsurancePolicy.class, new CancelUpcommingInsurancePolicy(
        ServiceLocator.getInstance().resolve(UserRepository.class)
    ));

    // Assembler
    ServiceLocator.getInstance().register(IdentityAssembler.class, new IdentityAssembler());
    ServiceLocator.getInstance().register(ApartementDetailsAssembler.class, new ApartementDetailsAssembler());
    ServiceLocator.getInstance().register(AddressAssembler.class, new AddressAssembler());
    ServiceLocator.getInstance().register(AnimalAssembler.class, new AnimalAssembler());
    ServiceLocator.getInstance().register(MoneyAssembler.class, new MoneyAssembler());
    ServiceLocator.getInstance().register(QuoteAssembler.class, new QuoteAssembler(
        ServiceLocator.getInstance().resolve(IdentityAssembler.class),
        ServiceLocator.getInstance().resolve(ApartementDetailsAssembler.class),
        ServiceLocator.getInstance().resolve(AddressAssembler.class),
        ServiceLocator.getInstance().resolve(AnimalAssembler.class),
        ServiceLocator.getInstance().resolve(MoneyAssembler.class)
    ));
    ServiceLocator.getInstance().register(OfferAssembler.class, new OfferAssembler());
    ServiceLocator.getInstance().register(AuthentificationAssembler.class, new AuthentificationAssembler());
    ServiceLocator.getInstance().register(ClaimAssembler.class, new ClaimAssembler());
    ServiceLocator.getInstance().register(InsurancePolicyAssembler.class, new InsurancePolicyAssembler());

    //MessageSender
    ServiceLocator.getInstance().register(CommuncationTechnic.class, new CommunicationNull());
    ServiceLocator.getInstance().register(EmailSender.class, new EmailSender(
        ServiceLocator.getInstance().resolve(CommuncationTechnic.class)
    ));
    ServiceLocator.getInstance().register(MessageSender.class, new EmailMessageSender(
        ServiceLocator.getInstance().resolve(EmailSender.class)
    ));
    ServiceLocator.getInstance().register(MessageAbstractFactory.class, new EmailMessageAbstractFactory());
    ServiceLocator.getInstance().register(MessageService.class, new MessageService(
        ServiceLocator.getInstance().resolve(MessageSender.class),
        ServiceLocator.getInstance().resolve(MessageAbstractFactory.class)
    ));

    //Quote Calculator
    AddressToApiAssembler addressToApiAssembler = new AddressToApiAssembler();
    QuoteToApiAssembler quoteToApiAssembler = new QuoteToApiAssembler(addressToApiAssembler);
    MapperApiResponse mapperApiResponse = new MapperApiResponse(new ObjectMapper());
    ApiDataSource apiDataSource = new ApiDataSource(mapperApiResponse, quoteToApiAssembler);
    ApiService apiService = new ApiService(apiDataSource, new QuoteCalculatedAssembler());
    UrlConnectionValidation urlConnectionValidation = new UrlConnectionValidation();
    ServiceLocator.getInstance().register(OfferCalculator.class, new CalculatorProxy(apiService, urlConnectionValidation));

    //Authentication
    ServiceLocator.getInstance().register(AuthentificationService.class, new AuthentificationService(
        ServiceLocator.getInstance().resolve(AuthentificationRepository.class),
        ServiceLocator.getInstance().resolve(AuthentificationAssembler.class)
    ));

    //Student
    ServiceLocator.getInstance().register(UserAssembler.class, new UserAssembler());
    ServiceLocator.getInstance().register(UserService.class, new UserService(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(UserAssembler.class),
        ServiceLocator.getInstance().resolve(MessageService.class),
        ServiceLocator.getInstance().resolve(AuthentificationService.class)));

    ServiceLocator.getInstance().register(CalculatorProxy.class, new CalculatorProxy(apiService, urlConnectionValidation
    ));

    //Information Service
    ServiceLocator.getInstance().register(InformationAssembler.class, new InformationAssembler(
        ServiceLocator.getInstance().resolve(QuoteAssembler.class),
        ServiceLocator.getInstance().resolve(OfferAssembler.class)
    ));

    //SPVQ Service
    ServiceLocator.getInstance().register(ScheduledExecutorService.class, Executors.newScheduledThreadPool(1));
    ServiceLocator.getInstance().register(CloseClaimsWithSPVQNumberExpired.class, new CloseClaimsWithSPVQNumberExpired(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(MessageService.class)
    ));
    ServiceLocator.getInstance().register(TaskManager.class, new TaskManager(
        ServiceLocator.getInstance().resolve(CloseClaimsWithSPVQNumberExpired.class),
        ServiceLocator.getInstance().resolve(ScheduledExecutorService.class),
        ServiceLocator.getInstance().resolve(UserService.class)
    ));

    //InsurancePolicyService
    ServiceLocator.getInstance().register(RenewInsurancePolicyService.class, new RenewInsurancePolicyService(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(OfferCalculator.class),
        ServiceLocator.getInstance().resolve(OfferAssembler.class)

    ));

    ServiceLocator.getInstance().register(UpdateInsurancePolicyService.class, new UpdateInsurancePolicyService(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(OfferCalculator.class),
        ServiceLocator.getInstance().resolve(OfferAssembler.class)

    ));

    //InsurancePolicyService
    ServiceLocator.getInstance().register(AcceptOffer.class, new AcceptOffer(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(InsurancePolicyAssembler.class)

    ));

    //Use cases
    ServiceLocator.getInstance().register(ClaimSettings.class, new ClaimSettings());
    ServiceLocator.getInstance().register(GenerateClaim.class, new GenerateClaim(
        ServiceLocator.getInstance().resolve(ClaimAssembler.class),
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(ClaimSettings.class)
    ));

    ServiceLocator.getInstance().register(GetClaimStatus.class, new GetClaimStatus(
        ServiceLocator.getInstance().resolve(ClaimAssembler.class),
        ServiceLocator.getInstance().resolve(UserRepository.class)
    ));

    ServiceLocator.getInstance().register(AddSPVQNumberToClaim.class, new AddSPVQNumberToClaim(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(MessageService.class)
    ));

    ServiceLocator.getInstance().register(AcceptInsurancePolicy.class, new AcceptInsurancePolicy(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(QuoteOfferRepository.class),
        ServiceLocator.getInstance().resolve(InsurancePolicyAssembler.class)
    ));

    ServiceLocator.getInstance().register(GenerateQuoteOffer.class, new GenerateQuoteOffer(
        ServiceLocator.getInstance().resolve(QuoteAssembler.class),
        ServiceLocator.getInstance().resolve(OfferCalculator.class),
        ServiceLocator.getInstance().resolve(MessageService.class),
        ServiceLocator.getInstance().resolve(QuoteOfferRepository.class),
        ServiceLocator.getInstance().resolve(OfferAssembler.class),
        ServiceLocator.getInstance().resolve(Clock.class)
    ));

    ServiceLocator.getInstance().register(GetQuoteInformation.class, new GetQuoteInformation(
        ServiceLocator.getInstance().resolve(QuoteOfferRepository.class),
        ServiceLocator.getInstance().resolve(InformationAssembler.class),
        ServiceLocator.getInstance().resolve(OfferCalculator.class),
        ServiceLocator.getInstance().resolve(MessageService.class),
        ServiceLocator.getInstance().resolve(Clock.class)
    ));

    ServiceLocator.getInstance().register(UpdateClaimAmountRatio.class, new UpdateClaimAmountRatio(
        ServiceLocator.getInstance().resolve(UserRepository.class),
        ServiceLocator.getInstance().resolve(ClaimSettings.class),
        new ClaimAssembler()
    ));
  }

  private void createDemoData() {

  }
}
