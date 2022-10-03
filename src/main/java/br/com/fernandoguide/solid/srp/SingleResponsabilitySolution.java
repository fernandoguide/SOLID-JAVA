package br.com.fernandoguide.solid.srp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;




public class SingleResponsabilitySolution {
    public interface RegraCalculo {
        double calcula(Funcionario funcionario);
    }
    public static class Funcionario {
      private Integer id;
      private String nome;
      private double salario;
      private Cargo cargo;

      public Funcionario() {}

      public Funcionario(Integer id, String nome, double salario, Cargo cargo) {
          this.id = id;
          this.nome = nome;
          this.salario = salario;
          this.cargo = cargo;
      }

      public Integer getId() {
          return id;
      }

      public void setId(Integer id) {
          this.id = id;
      }

      public String getNome() {
          return nome;
      }

      public void setNome(String nome) {
          this.nome = nome;
      }

      public double getSalario() {
          return salario;
      }

      public void setSalario(double salario) {
          this.salario = salario;
      }

      public Cargo getCargo() {
          return cargo;
      }

      public void setCargo(Cargo cargo) {
          this.cargo = cargo;
      }

      public double calculaSalario() {
          return cargo.getRegra().calcula(this);
      }

      @Override
      public String toString() {
          return "Funcionario [id=" + id + ", nome=" + nome + ", salario=" + salario + ", cargo=" + cargo + "]";
      }
  }
    public enum Cargo {

        DESENVOLVEDOR_SENIOR(new RegraVinteDoisEMeioPorcento()),
        DESENVOLVEDOR_JUNIOR(new RegraOnzePorcento());

        private RegraCalculo regra;

        Cargo(RegraCalculo regra){
            this.regra = regra;
        }

        public RegraCalculo getRegra() {
            return regra;
        }

    }
    public static class ConnectionDAO {

        private Properties connectionProps;
        private Connection conn;
        private String dbms;
        private String dbName;
        private String serverName;
        private String portNumber;

        private static final String JDBC = "jdbc:";

        public ConnectionDAO (){
            super();
        }

        public ConnectionDAO (String username, String password){
            super();
            connectionProps = new Properties();
            connectionProps.put("user", username);
            connectionProps.put("password", password);
        }

        public Properties getConnectionProps() {
            return connectionProps;
        }

        public void setConnectionProps(Properties connectionProps) {
            this.connectionProps = connectionProps;
        }

        public Connection getConnection() {
            return conn;
        }

        public void setConnection(Connection conn) {
            this.conn = conn;
        }

        public String getDbms() {
            return dbms;
        }

        public void setDbms(String dbms) {
            this.dbms = dbms;
        }

        public String getDbName() {
            return dbName;
        }

        public void setDbName(String dbName) {
            this.dbName = dbName;
        }

        public String getServerName() {
            return serverName;
        }

        public void setServerName(String serverName) {
            this.serverName = serverName;
        }

        public String getPortNumber() {
            return portNumber;
        }

        public void setPortNumber(String portNumber) {
            this.portNumber = portNumber;
        }

        public Connection createConnection() {
            Connection newConnection = null;
            try {

                if (getDbms().equals("mysql")) {
                    newConnection = DriverManager.getConnection(JDBC + getDbms() + "://" + getServerName() + ":" + getPortNumber()
                            + "/" + getDbName() + "?useSSL=false", getConnectionProps());
                }else if (getDbms().equals("postgreSQL")){
                    newConnection = DriverManager.getConnection(JDBC + getDbms() + "://" + getServerName() + ":" + getPortNumber()
                            + "/" + getDbName() + "?useSSL=false", getConnectionProps());
                }else if (getDbms().equals("derby")) {
                    newConnection = DriverManager.getConnection(JDBC + getDbms() + ":" + getDbName() + ";create=true", getConnectionProps());
                }
                setConnection(newConnection);
                System.out.println("Connected to database");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return newConnection;
        }
    }

    public static class FuncionarioDAO {

        public void salva(Funcionario funcionario) throws SQLException {

            ConnectionDAO connectionDAO = new ConnectionDAO("root", "");
            connectionDAO.setDbms("mysql");
            connectionDAO.setServerName("localhost");
            connectionDAO.setPortNumber("8080");
            connectionDAO.setDbName("mock");

            try (Connection connection = connectionDAO.createConnection();
                 Statement stmt = connection.createStatement();) {

                String sql = "insert into funcionario (id, nome, salario) values (" + funcionario.getId() + "," +
                        funcionario.getNome() + "," + funcionario.getSalario() + ")";
                int rs = stmt.executeUpdate(sql);

                if (rs == 1) {
                    System.out.println("Funcionario inserido com sucesso.");
                }
            } catch (SQLException e) {
                System.out.println("Nenhum funcionario inserido." + e);
            }
        }
    }
    public static class RegraVinteDoisEMeioPorcento implements RegraCalculo{

        @Override
        public double calcula(Funcionario funcionario) {
            return funcionario.getSalario() - (funcionario.getSalario() * 0.225);
        }

    }
    public static class RegraOnzePorcento implements RegraCalculo{

        @Override
        public double calcula(Funcionario funcionario) {
            return funcionario.getSalario() - (funcionario.getSalario() * 0.11);
        }

    }
}
