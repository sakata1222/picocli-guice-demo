import javax.inject.*;
import picocli.*;
import picocli.CommandLine.*;

@Command(name = "di-demo")
public class InjectionDemo implements Runnable {

  @Inject
  java.util.List list;

  @Option(names = "-x")
  int x;

  public static void main(String[] args) {
    new CommandLine(InjectionDemo.class, new GuiceFactory()).execute(args);
  }

  @Override
  public void run() {
    System.out.println("Hello " + list.getClass());
    assert list instanceof java.util.LinkedList;
  }
}