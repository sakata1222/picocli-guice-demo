import com.google.inject.*;
import picocli.*;
import picocli.CommandLine.*;

public class GuiceFactory implements IFactory {

  private final Injector injector = Guice.createInjector(new DemoModule());

  @Override
  public <K> K create(Class<K> aClass) throws Exception {
    try {
      return injector.getInstance(aClass);
    } catch (ConfigurationException ex) { // no implementation found in Guice configuration
      return CommandLine.defaultFactory().create(aClass); // fallback if missing
    }
  }

  static class DemoModule extends AbstractModule {

    @Override
    protected void configure() {
      bind(java.util.List.class).to(java.util.LinkedList.class);
    }
  }
}