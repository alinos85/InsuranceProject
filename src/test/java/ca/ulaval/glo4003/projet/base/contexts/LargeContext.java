package ca.ulaval.glo4003.projet.base.contexts;

import ca.ulaval.glo4003.projet.base.ws.ServiceLocator;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.ClaimResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.BadRequestExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.GenericExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.InsurancePolicyResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.offer.OfferResource;
import ca.ulaval.glo4003.projet.base.ws.application.api.offer.OfferResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.QuoteResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.UserResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.context.ApplicationContext;
import ca.ulaval.glo4003.projet.base.ws.application.http.CORSResponseFilter;
import ca.ulaval.glo4003.projet.base.ws.service.actuary.UpdateClaimAmountRatio;
import ca.ulaval.glo4003.projet.base.ws.service.authentification.AuthentificationService;
import ca.ulaval.glo4003.projet.base.ws.service.claim.AddSPVQNumberToClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GenerateClaim;
import ca.ulaval.glo4003.projet.base.ws.service.claim.GetClaimStatus;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.AcceptInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.CancelUpcommingInsurancePolicy;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.RenewInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.insurancepolicy.UpdateInsurancePolicyService;
import ca.ulaval.glo4003.projet.base.ws.service.offer.AcceptOffer;
import ca.ulaval.glo4003.projet.base.ws.service.offer.GenerateQuoteOffer;
import ca.ulaval.glo4003.projet.base.ws.service.quote.GetQuoteInformation;
import ca.ulaval.glo4003.projet.base.ws.service.user.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class LargeContext {

  private UserResourceImpl userResource;
  private ClaimResourceImpl claimResource;
  private QuoteResourceImpl quoteResource;
  private InsurancePolicyResourceImpl contractResource;
  private OfferResource offerResource;

  public void configure(Server server) {

    // Use ContextHandlerCollection
    ContextHandlerCollection contexts = new ContextHandlerCollection();
// Don't forget to add it to the server!

    ServletContextHandler context = new ServletContextHandler(contexts, "/*");
    ApplicationContext applicationContext = new TestContext();
    applicationContext.apply();
    userResource = createUserResource();
    contractResource = createContractResource();
    claimResource = createClaimResource();
    quoteResource = createQuoteResource();
    offerResource = createOfferResource();

    context.setContextPath("/api");
    ResourceConfig packageConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        HashSet<Object> resources = new HashSet<>();
        // Add resources to context
        resources.add(claimResource);
        resources.add(quoteResource);
        resources.add(contractResource);
        resources.add(userResource);
        resources.add(offerResource);
        return resources;
      }
    });
    packageConfig.register(CORSResponseFilter.class);

    ServletContainer container = new ServletContainer(packageConfig);
    ServletHolder servletHolder = new ServletHolder(container);

    context.addServlet(servletHolder, "/*");
    server.setHandler(contexts);

  }

  private void setupExceptionMappers(ResourceConfig packageConfig) {
    packageConfig.register(new GenericExceptionMapper());
    packageConfig.register(new BadRequestExceptionMapper());
  }

  private static InsurancePolicyResourceImpl createContractResource() {
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
    RenewInsurancePolicyService renewInsurancePolicyService = ServiceLocator.getInstance().resolve(RenewInsurancePolicyService.class);
    UpdateInsurancePolicyService updateInsurancePolicyService = ServiceLocator.getInstance().resolve(UpdateInsurancePolicyService.class);
    CancelUpcommingInsurancePolicy cancelUpcommingInsurancePolicy = ServiceLocator.getInstance().resolve(CancelUpcommingInsurancePolicy.class);
    return new InsurancePolicyResourceImpl(userService, renewInsurancePolicyService, updateInsurancePolicyService, cancelUpcommingInsurancePolicy);
  }

  private static OfferResource createOfferResource() {
    AcceptOffer acceptOffer = ServiceLocator.getInstance().resolve(AcceptOffer.class);
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
    return new OfferResourceImpl(acceptOffer, userService);
  }

  private static UserResourceImpl createUserResource() {
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
    AuthentificationService authentificationService = ServiceLocator.getInstance().resolve(AuthentificationService.class);
    return new UserResourceImpl(userService);
  }

  private static QuoteResourceImpl createQuoteResource() {
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
    GenerateQuoteOffer generateQuoteOffer = ServiceLocator.getInstance().resolve(GenerateQuoteOffer.class);
    GetQuoteInformation getQuoteInformation = ServiceLocator.getInstance().resolve(GetQuoteInformation.class);
    AcceptInsurancePolicy acceptInsurancePolicy = ServiceLocator.getInstance().resolve(AcceptInsurancePolicy.class);

    return new QuoteResourceImpl(userService, generateQuoteOffer, getQuoteInformation, acceptInsurancePolicy);

  }

  private static ClaimResourceImpl createClaimResource() {
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
    AddSPVQNumberToClaim addSPVQNumberToClaim = ServiceLocator.getInstance().resolve(AddSPVQNumberToClaim.class);
    GenerateClaim generateClaim = ServiceLocator.getInstance().resolve(GenerateClaim.class);
    GetClaimStatus getClaimStatus = ServiceLocator.getInstance().resolve(GetClaimStatus.class);
    UpdateClaimAmountRatio updateClaimAmountRatio = ServiceLocator.getInstance().resolve(UpdateClaimAmountRatio.class);

    return new ClaimResourceImpl(userService, generateClaim, getClaimStatus, addSPVQNumberToClaim, updateClaimAmountRatio);

  }

}
