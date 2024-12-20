import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The SqlColumn annotation is used to specify the mapping between a field in a Java object
 * and a column in the database table.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SqlColumn {
    /**
     * The name of the column in the database table.
     *
     * @return the name of the column
     */
    String value();
}