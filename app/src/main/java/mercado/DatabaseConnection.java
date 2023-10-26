package mercado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  String jdbcUrl = "jdbc:mysql://localhost:3306/Mercado";
  String username = "root";
  String password = "my-secret-pw";

  public Connection connection;


  void getConnection() {

    try {
      Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

      this.connection = connection;

      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    ;
  }
}
