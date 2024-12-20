import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The XmlJsonField annotation is used to specify the mapping between a field in a Java object
 * and a tag in the serialized XML/JSON format.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface XmlJsonField {
  /**
   * The name of the tag in the serialized format.
   *
   * @return the name of the tag
   */
  String value();
}
