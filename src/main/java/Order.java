import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Order class represents an order with an order number and price.
 */
@XmlJsonRoot("order")
@Data
@AllArgsConstructor
public class Order {

  @XmlJsonField("orderNumber")
  private String orderNumber;

  @XmlJsonField("price")
  private double price;
}
