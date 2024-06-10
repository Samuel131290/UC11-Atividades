package LeiloesTDSat.model;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO 
{
    public static boolean cadastrarProduto(ProdutosDTO produto) throws SQLException 
    {
        try
        {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "INSERT INTO produtos(nome, valor) VALUES(?,?);";
            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            query.setString(1, produto.getNome());
            query.setFloat(2, produto.getValor());
            
            query.execute();
            conexao.desconectar();
            return true;
        } 
        catch (SQLException se) 
        {
            System.out.println("Erro ao cadastrar registro no banco de dados!");
            return false;
        }
    }
  
    public static boolean venderProduto(int produtoId) throws SQLException 
    {
        try 
        {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            query.setInt(1, produtoId);

            int rowsAffected = query.executeUpdate();
            conexao.desconectar();

            return rowsAffected > 0;
        } 
        catch (SQLException se) 
        {
            System.out.println("Erro ao vender o produto: " + se.getMessage());
            return false;
        }
    }
    
    public List<ProdutosDTO> buscarProdutosVendidos() throws SQLException 
    {
        List<ProdutosDTO> produtosVendidos = new ArrayList<>();
        try 
        {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = query.executeQuery();

            while (rs.next()) 
            {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                float valor = rs.getFloat("valor");
                String status = rs.getString("status");

                ProdutosDTO produto = new ProdutosDTO(id, nome, valor, status);
                produtosVendidos.add(produto);
            }
            conexao.desconectar();
        } 
        catch (SQLException e) 
        {
            throw new SQLException("Erro ao buscar produtos vendidos: " + e.getMessage());
        }
        return produtosVendidos;
    }
    
    public static List<ProdutosDTO> listarProdutos() 
    {
        List<ProdutosDTO> lista = new ArrayList<ProdutosDTO>();

        try 
        {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "SELECT * FROM produtos";
            PreparedStatement consulta = conexao.getConexao().prepareStatement(sql);
            ResultSet resposta = consulta.executeQuery();

            while (resposta.next()) 
            {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resposta.getInt("id"));
                produto.setNome(resposta.getString("nome"));
                produto.setValor(Float.parseFloat(resposta.getString("valor")));
                produto.setStatus(resposta.getString("status"));
                lista.add(produto);
            }

            conexao.desconectar();
        } 
        catch (SQLException e) 
        {
            System.out.println("Erro ao listar os registros do banco de dados!");
        }
        return lista;
    }
}
