# DAO

From Wikipedia
--------------

"In software, a **data access object (DAO)** is a pattern that provides an
abstract interfact to some type of database or persistence mechanism.
By mapping application calls to the persistence layer, the DAO procides data
operations without exposing database details. This isolation supports the
*Single Responsibility Principle*. It separates the data access the application
needs, in terms of domain-specific objects and data types (the DAOs public
interface), from how these needs can be satisfied with a specific DBMS (the
implementation of the DAO)"


Advantages
----------

1. Changes to persistence mechanism isolated to DAO Layer only
1. DAO Pattern emphasizes loose coupling between application layers
1. DAO Pattern makes it easier to write unit tests
1. DAO Pattern emphasizes the Dependency Inversion Principle (i think)


Organization
------------

<https://www.youtube.com/watch?v=3J5L40MJfU4&list=PL3bGLnkkGnuX_Pa95v_FUdazcFhEF7iBi&index=1>

### Main

```java
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = Database.getConnection();

        if (conn!= null) {
            System.out.println("Database connection successful");
        }
    }
}
```


### DTO

* Class only used to transfer information between processes
* Class does not contain any business logic

```java
// For Example
public class Employee {
    private int id;
    private int employeeId;
    private String firstName;
    private String lastName;
    private int deptId;

    ... // Constructors
    ... // Getters and Setters
    ... // (optional) toString, hashCode
}
```


### DAO Interface

```java
// Generic base template for all Daos
public interface Dao<T> {
    T get(int id) throws SQLException;
    List<T> getAll() throws SQLException;
    int save(T t) throws SQLException; // calls insert or update under the hood
    int insert(T t) throws SQLException;
    int update(T t) throws SQLException;
    int delete(T t);
}
```

```java
public interface EmployeeDao extends Dao<Employee> {
    // We can add any additional methods specific to our specific Dao here
    
}
```

```java
// This can be our controller?
public class EmployeeDaoImpl implements EmployeeDao {
    // Implement our methods here
}
```


### Dao Connection

```java
public class Database {
    private static String utl = "jdbc:mariadb://localhost:3306/tutorial";
    private static String user = "root";
    private static String password = "password";

    private Database() {}

    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection(url, user, password);

        return connection;
    }
}
```


### Dao Retrieve/Insert/Update/Delete

```java
public class Main {

    public static void main(String[] args) throws SQLException {
        EmployeeDao employeeDao = new EmployeeDaoImpl();

        // Retrieve
        Employee e1 = employeeDao.get(10);

        // Insert
        Employee e2 = new Employee(0, 246802, "John", "Doe", 5);
        int result = employeeDao.insert(e2);

        // Update
        // Employee e3 = new Employee(8, 336861, "Sam", "Spade", 5); // employee already in database
        Employee e3 = new Employee(8, 336861, "Sam", "Club", 5); // Make changes here
        int result = employeeDao.update(e3);

        // Delete
        Employee e4 = employeeDao.get(10); // retrieve an employee from database
        System.out.println(e4);
        employeeDao.delete(e4);

        
        System.out.println(employee);
    }

}

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee get(int id) throws SQLException {
        Connection conn = Database.getConnection();
        Employee employee = null;

        String sql = "SELECT id, employee_id, first_name, last_name, dept_id FROM employees WHERE id = ?";

        PreparedStatement ps = conn.getPrepareStatement(sql);

        ps.setInt(1, id); // set id

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int oid = rs.getInt("id");
            int employeeId = rs.getInt("employeeId");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            int deptId = rs.getInt("dept_id");

            employee = new Employee(oid, employeeId, firstName, lastName, deptId);
        }

        return employee;
    }

    @Override
    public int insert(Employee employee) throws SQLException {
        Connection conn = Database.getConnection();

        String sql = "INSERT INTO employees (employee_id, first_name, last_name, dept_id) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, employee.getEmployeeId());
        ps.setString(2, employee.getFirstName());
        ps.setString(3, employee.getLastName());
        ps.setInt(4, employee.getDeptId());

        int result = ps.executeUpdate();

        // Close resources
        Database.closePreparedStatement(ps);
        Database.closeConnection(conn);

        // Returns number of rows affected
        return result;
    }

    @Override
    public int update(Employee employee) throws SQLException {
        Connection conn = Database.getConnection();

        String sql = "UPDATE employees set employee_id = ?, first_name = ?, lst_name = ?, dept_id = ? where id = ?";

        PreparedStatement = connection.prepareStatement(sql);

        ps.setInt(1, employee.getEmployeeId());
        ps.setString(2, employee.getFirstName());
        ps.setString(3, employee.getLastName());
        ps.setString(4, employee.getDeptId());
        ps.setInt(5, employee.getId());

        int result = ps.executeUpdate();

        // Close connections
        Database.closePreparedStatement(ps);
        Database.closeConnection(conn);

        return result;
    }

    @Override
    public int delete(Employee employee) {
        Connection conn = Database.getConnection();

        String sql = "DELETE FROM employees WHERE id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);

        // Replace question marks in our query
        ps.setInt(1, employee.getId());

        int result = ps.executeUpdate();

        Database.closePreparedStatement(ps);
        Database.closeConnection(conn);

        return result;
    }
}
```
