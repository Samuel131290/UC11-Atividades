package LeiloesTDSat.model;

public class ProdutosDTO 
{
    private int id;
    private String nome;
    private float valor;
    private String status;
    
    public ProdutosDTO() 
    {

    }

    public ProdutosDTO(int id, String nome, float valor, String status)
    {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }
    
    public float getValor() 
    {
        return valor;
    }

    public void setValor(float valor) 
    {
        this.valor = valor;
    }
    
    public String getStatus() 
    {
        return status;
    }
    
    public void setStatus(String status) 
    {
        this.status = status;
    }
}