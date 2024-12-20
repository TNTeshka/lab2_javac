/**
 * The Main class demonstrates the serialization of objects to JSON and XML formats
 * using both reflection-based and manual serialization methods.
 */
public class Main {

  /**
   * Main entry point of the program.
   */
  public static void main(String[] args) throws IllegalAccessException {
    Person person = new Person("John", 32);
    Product product = new Product(25, "Laptop", "Electronics");
    Order order = new Order("ORD67043406473256321", 2999.99);

    long start, end;

    start = System.nanoTime();
    System.out.println(ReflectionSerializer.toJson(person));
    System.out.println(ReflectionSerializer.toJson(product));
    System.out.println(ReflectionSerializer.toJson(order));
    System.out.println(ReflectionSerializer.toXml(person));
    System.out.println(ReflectionSerializer.toXml(product));
    System.out.println(ReflectionSerializer.toXml(order));
    end = System.nanoTime();
    System.out.println("Reflection serialization time: " + (end - start) / 1_000_000 + " ms");

    start = System.nanoTime();
    System.out.println(ManualSerializer.toJson(person));
    System.out.println(ManualSerializer.toJson(product));
    System.out.println(ManualSerializer.toJson(order));
    System.out.println(ManualSerializer.toXml(person));
    System.out.println(ManualSerializer.toXml(product));
    System.out.println(ManualSerializer.toXml(order));
    end = System.nanoTime();
    System.out.println("Manual serialization time: " + (end - start) / 1_000_000 + " ms");
  }
}
