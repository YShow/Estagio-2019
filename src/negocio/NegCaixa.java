package negocio;
import acessoBD.MariaDB.AcessoBD;
import objeto.Caixa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class NegCaixa {
    
    public boolean inserir(Caixa caixa) throws Exception
    {
	return false;	
    }
    
    public ArrayList<Caixa> consultar(String metodo) throws Exception
    {
	return null;	
    }
    
    public boolean alterar (Caixa caixa) throws Exception
    {
	return false;	
    }
    
    public boolean excluir(int id) throws Exception
    {
	return false;
    }
}
