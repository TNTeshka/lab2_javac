import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Product class represents a product with an id, title, and category.
 */
@XmlJsonRoot("product")
@Data
@AllArgsConstructor
public class Product {

  @XmlJsonField("id")
  private int id;

  @XmlJsonField("title")
  private String title;

  @XmlJsonField("category")
  private String category;
}
