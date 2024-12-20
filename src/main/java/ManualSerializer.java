/**
 * The ManualSerializer class provides methods for manually serializing objects to JSON and XML formats.
 */
public class ManualSerializer {

  /**
   * Serializes a Person object to JSON format.
   *
   * @param person the Person object to serialize
   * @return the JSON representation of the Person object
   */
  public static String toJson(Person person) {
    return String.format("{\"name\": \"%s\", \"age\": %d}", person.getName(), person.getAge());
  }

  /**
   * Serializes a Person object to XML format.
   *
   * @param person the Person object to serialize
   * @return the XML representation of the Person object
   */
  public static String toXml(Person person) {
    return String.format("<person><name>%s</name><age>%d</age></person>",
        person.getName(), person.getAge());
  }

  /**
   * Serializes a Product object to JSON format.
   *
   * @param product the Product object to serialize
   * @return the JSON representation of the Product object
   */
  public static String toJson(Product product) {
    return String.format("{\"id\": %d, \"title\": \"%s\", \"product\": \"%s\"}",
        product.getId(), product.getTitle(), product.getCategory());
  }

  /**
   * Serializes a Product object to XML format.
   *
   * @param product the Product object to serialize
   * @return the XML representation of the Product object
   */
  public static String toXml(Product product) {
    return String.format("<product><id>%d</id><title>%s</title><category>%s</category></product>",
        product.getId(), product.getTitle(), product.getCategory());
  }

  /**
   * Serializes an Order object to JSON format.
   *
   * @param order the Order object to serialize
   * @return the JSON representation of the Order object
   */
  public static String toJson(Order order) {
    return String.format("{\"orderNumber\": \"%s\", \"price\": %.2f}",
        order.getOrderNumber(), order.getPrice());
  }

  /**
   * Serializes an Order object to XML format.
   *
   * @param order the Order object to serialize
   * @return the XML representation of the Order object
   */
  public static String toXml(Order order) {
    return String.format("<order><orderNumber>%s</orderNumber><price>%.2f</price></order>",
        order.getOrderNumber(), order.getPrice());
  }
}
