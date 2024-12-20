import java.lang.reflect.Field;
import java.util.StringJoiner;

/**
 * The SqlQueryBuilder class provides methods for generating SQL queries for CRUD operations
 * based on the annotations present in the class.
 */
public class SqlQueryBuilder {

    /**
     * Generates an SQL INSERT query for the given object.
     *
     * @param obj the object to generate the query for
     * @return the SQL INSERT query
     * @throws IllegalAccessException if the reflection-based access encounters an illegal access
     */
    public static String generateInsertQuery(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(SqlTable.class)) {
            throw new IllegalArgumentException("Class must be annotated with @SqlTable");
        }
        SqlTable tableAnnotation = clazz.getAnnotation(SqlTable.class);
        String tableName = tableAnnotation.value();

        StringJoiner columns = new StringJoiner(", ");
        StringJoiner values = new StringJoiner(", ", "(", ")");

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(SqlColumn.class)) {
                field.setAccessible(true);
                SqlColumn columnAnnotation = field.getAnnotation(SqlColumn.class);
                columns.add(columnAnnotation.value());
                values.add("'" + field.get(obj).toString() + "'");
            }
        }

        return String.format("INSERT INTO %s (%s) VALUES %s;", tableName, columns.toString(), values.toString());
    }

    /**
     * Generates an SQL SELECT query for the given class.
     *
     * @param clazz the class to generate the query for
     * @return the SQL SELECT query
     */
    public static String generateSelectQuery(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(SqlTable.class)) {
            throw new IllegalArgumentException("Class must be annotated with @SqlTable");
        }
        SqlTable tableAnnotation = clazz.getAnnotation(SqlTable.class);
        String tableName = tableAnnotation.value();
        return String.format("SELECT * FROM %s;", tableName);
    }

    /**
     * Generates an SQL UPDATE query for the given object.
     *
     * @param obj the object to generate the query for
     * @param idField the field representing the primary key
     * @return the SQL UPDATE query
     * @throws IllegalAccessException if the reflection-based access encounters an illegal access
     */
    public static String generateUpdateQuery(Object obj, Field idField) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(SqlTable.class)) {
            throw new IllegalArgumentException("Class must be annotated with @SqlTable");
        }
        SqlTable tableAnnotation = clazz.getAnnotation(SqlTable.class);
        String tableName = tableAnnotation.value();

        StringJoiner setClause = new StringJoiner(", ");

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(SqlColumn.class) && !field.equals(idField)) {
                field.setAccessible(true);
                SqlColumn columnAnnotation = field.getAnnotation(SqlColumn.class);
                setClause.add(columnAnnotation.value() + " = '" + field.get(obj).toString() + "'");
            }
        }

        idField.setAccessible(true);
        SqlColumn idAnnotation = idField.getAnnotation(SqlColumn.class);
        String idValue = idField.get(obj).toString();

        return String.format("UPDATE %s SET %s WHERE %s = '%s';", tableName, setClause.toString(), idAnnotation.value(), idValue);
    }

    /**
     * Generates an SQL DELETE query for the given class and id.
     *
     * @param clazz the class to generate the query for
     * @param idField the field representing the primary key
     * @param idValue the value of the primary key
     * @return the SQL DELETE query
     */
    public static String generateDeleteQuery(Class<?> clazz, Field idField, Object idValue) {
        if (!clazz.isAnnotationPresent(SqlTable.class)) {
            throw new IllegalArgumentException("Class must be annotated with @SqlTable");
        }
        SqlTable tableAnnotation = clazz.getAnnotation(SqlTable.class);
        String tableName = tableAnnotation.value();
        SqlColumn idAnnotation = idField.getAnnotation(SqlColumn.class);
        return String.format("DELETE FROM %s WHERE %s = '%s';", tableName, idAnnotation.value(), idValue.toString());
    }
}