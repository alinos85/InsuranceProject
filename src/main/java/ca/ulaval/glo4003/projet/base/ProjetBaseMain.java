package ca.ulaval.glo4003.projet.base;

import ca.ulaval.glo4003.projet.base.ws.ServiceLocator;
import ca.ulaval.glo4003.projet.base.ws.application.api.authentication.AuthNeededImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.ClaimResource;
import ca.ulaval.glo4003.projet.base.ws.application.api.claim.ClaimResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.BadRequestExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.ForbiddenExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.GenericExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.ResourceConflictExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.application.api.errorhandling.ResourceNotFoundExceptionMapper;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.InsurancePolicyResource;
import ca.ulaval.glo4003.projet.base.ws.application.api.insurancepolicy.InsurancePolicyResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.offer.OfferResource;
import ca.ulaval.glo4003.projet.base.ws.application.api.offer.OfferResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.QuoteResource;
import ca.ulaval.glo4003.projet.base.ws.application.api.quote.QuoteResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.UserResource;
import ca.ulaval.glo4003.projet.base.ws.application.api.user.UserResourceImpl;
import ca.ulaval.glo4003.projet.base.ws.application.context.ApplicationContext;
import ca.ulaval.glo4003.projet.base.ws.application.context.DevApplicationContext;
import ca.ulaval.glo4003.projet.base.ws.application.http.CORSResponseFilter;
import ca.ulaval.glo4003.projet.base.ws.service.actuary.UpdateClaimAmountRatio;
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
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ProjetBaseMain {

  public static boolean isDev = true; // Would be a JVM argument or in a .property file

  public static void main(String[] args)
      throws Exception {

    // Setup resources (API)
    ApplicationContext appContext = new DevApplicationContext();
    appContext.apply();
    QuoteResource quoteResource = createQuoteResource();
    ClaimResource claimResource = createClaimResource();
    InsurancePolicyResource insurancePolicyResource = createContractResource();
    UserResource userResource = createUserResource();
    OfferResource offerResource = createOfferResource();

    // Setup API context (JERSEY + JETTY)
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/api/");
    ResourceConfig resourceConfig = ResourceConfig.forApplication(new Application() {
      @Override
      public Set<Object> getSingletons() {
        HashSet<Object> resources = new HashSet<>();
        // Add resources to context
        resources.add(claimResource);
        resources.add(quoteResource);
        resources.add(insurancePolicyResource);
        resources.add(userResource);
        resources.add(offerResource);
        return resources;
      }
    });
    setupExceptionMappers(resourceConfig);
    resourceConfig.register(CORSResponseFilter.class);
    resourceConfig.register(AuthNeededImpl.class);

    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    context.addServlet(servletHolder, "/*");

    // Setup http server
    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[]{context});
    Server server = new Server(8080);
    server.setHandler(contexts);

    try {
      server.start();
      server.join();
    } finally {
      server.destroy();
    }
  }

  private static InsurancePolicyResource createContractResource() {
    RenewInsurancePolicyService renewInsurancePolicyService = ServiceLocator.getInstance().resolve((RenewInsurancePolicyService.class));
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
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
    return new UserResourceImpl(userService);
  }

  private static QuoteResource createQuoteResource() {
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
    GenerateQuoteOffer generateQuoteOffer = ServiceLocator.getInstance().resolve(GenerateQuoteOffer.class);
    GetQuoteInformation getQuoteInformation = ServiceLocator.getInstance().resolve(GetQuoteInformation.class);
    AcceptInsurancePolicy acceptInsurancePolicy = ServiceLocator.getInstance().resolve(AcceptInsurancePolicy.class);

    return new QuoteResourceImpl(userService, generateQuoteOffer, getQuoteInformation, acceptInsurancePolicy);
  }

  private static ClaimResource createClaimResource() {
    UserService userService = ServiceLocator.getInstance().resolve(UserService.class);
    AddSPVQNumberToClaim addSPVQNumberToClaim = ServiceLocator.getInstance().resolve(AddSPVQNumberToClaim.class);
    GenerateClaim generateClaim = ServiceLocator.getInstance().resolve(GenerateClaim.class);
    GetClaimStatus getClaimStatus = ServiceLocator.getInstance().resolve(GetClaimStatus.class);
    UpdateClaimAmountRatio updateClaimAmountRatio = ServiceLocator.getInstance().resolve(UpdateClaimAmountRatio.class);

    return new ClaimResourceImpl(userService, generateClaim, getClaimStatus, addSPVQNumberToClaim, updateClaimAmountRatio);
  }

  private static void setupExceptionMappers(ResourceConfig packageConfig) {
    packageConfig.register(new BadRequestExceptionMapper());
    packageConfig.register(new ForbiddenExceptionMapper());
    packageConfig.register(new ResourceNotFoundExceptionMapper());
    packageConfig.register(new ResourceConflictExceptionMapper());
    packageConfig.register(new GenericExceptionMapper());
  }
}
