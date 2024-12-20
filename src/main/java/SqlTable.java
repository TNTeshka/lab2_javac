import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The SqlTable annotation is used to specify the table name in the database.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SqlTable {
    /**
     * The name of the table in the database.
     *
     * @return the name of the table
     */
    String value();
}