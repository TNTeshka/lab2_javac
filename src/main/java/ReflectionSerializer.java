import java.lang.reflect.Field;

/**
 * The ReflectionSerializer class provides methods for serializing objects to JSON and XML formats
 * using reflection.
 */
public class ReflectionSerializer {

  /**
   * Serializes an object to JSON format using reflection.
   *
   * @param obj the object to serialize
   * @return the JSON representation of the object
   * @throws IllegalAccessException if the reflection-based serialization encounters an illegal access
   */
  public static String toJson(Object obj) throws IllegalAccessException {
    Class<?> clazz = obj.getClass();
    if (!clazz.isAnnotationPresent(XmlJsonRoot.class)) {
      throw new IllegalArgumentException("Class must be annotated with @XmlJsonRoot");
    }

    StringBuilder json = new StringBuilder("{");
    XmlJsonRoot root = clazz.getAnnotation(XmlJsonRoot.class);

    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(XmlJsonField.class)) {
        field.setAccessible(true);
        XmlJsonField annotation = field.getAnnotation(XmlJsonField.class);
        String key = annotation.value();
        Object value = field.get(obj);

        json.append(String.format("\"%s\": \"%s\",", key, value));
      }
    }
    if (json.charAt(json.length() - 1) == ',') {
      json.deleteCharAt(json.length() - 1);
    }
    json.append("}");
    return json.toString();
  }

  /**
   * Serializes an object to XML format using reflection.
   *
   * @param obj the object to serialize
   * @return the XML representation of the object
   * @throws IllegalAccessException if the reflection-based serialization encounters an illegal access
   */
  public static String toXml(Object obj) throws IllegalAccessException {
    Class<?> clazz = obj.getClass();
    if (!clazz.isAnnotationPresent(XmlJsonRoot.class)) {
      throw new IllegalArgumentException("Class must be annotated with @XmlJsonRoot");
    }

    StringBuilder xml = new StringBuilder();
    XmlJsonRoot root = clazz.getAnnotation(XmlJsonRoot.class);
    xml.append("<").append(root.value()).append(">");

    Field[] fields = clazz.getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(XmlJsonField.class)) {
        field.setAccessible(true);
        XmlJsonField annotation = field.getAnnotation(XmlJsonField.class);
        String tag = annotation.value();
        Object value = field.get(obj);

        xml.append(String.format("<%s>%s</%s>", tag, value, tag));
      }
    }
    xml.append("</").append(root.value()).append(">");
    return xml.toString();
  }
}
