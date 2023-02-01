package ca.ulaval.glo4003.projet.base.ws;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocator {

  private static ServiceLocator instance;

  private static Map<Class<?>, Object> mapping;

  private ServiceLocator() {
    mapping = new HashMap<>();
  }

  public static ServiceLocator getInstance() {
    if (instance == null) {
      instance = new ServiceLocator();
    }

    return instance;
  }

  public <T> void register(Class<T> abstractClass, Object concreteClass) {
    mapping.put(abstractClass, concreteClass);
  }

  public <T> T resolve(Class<T> abstractClass) {
    return (T) mapping.get(abstractClass);
  }

  public static void reset() {
    mapping.clear();
  }

}
