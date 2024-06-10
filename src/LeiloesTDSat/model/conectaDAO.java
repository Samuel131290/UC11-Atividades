package LeiloesTDSat.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class conectaDAO 
{    
    private Connection conexao;
    
    public Connection getConexao() 
    {
        return conexao;
    }
    
    public void conectar() 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://root@localhost/uc11","root","1234");
            System.out.println("Conexão com DataBase efetuada com sucesso!");
        }
        catch (ClassNotFoundException e) 
        {
            System.out.println("Falha ao carregar a classe de conexão. Classe não encontrada!");
        } 
        catch (SQLException se) 
        {
            System.out.println("Falha ao conectar com o banco. Erro de SQL.");
        }
    }
    
    public void desconectar() 
    {
        try 
        {
            if(conexao != null && !conexao.isClosed()) 
            {
                conexao.close();
                System.out.println("Desconectado com Sucesso!");
            }
        } 
        catch (SQLException e) 
        {   
            System.out.println("Erro ao desconectar");
        }
    }
}