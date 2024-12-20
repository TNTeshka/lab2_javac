import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The Person class represents a person with a name and age.
 */
@XmlJsonRoot("person")
@Data
@AllArgsConstructor
public class Person {

  @XmlJsonField("name")
  private String name;

  @XmlJsonField("age")
  private int age;
}
