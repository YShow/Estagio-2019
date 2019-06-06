package negocio;
import acessoBD.MariaDB.AcessoBD;
import objeto.VendaProd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class NegVendaProd {
    public boolean inserir(VendaProd vendaProd) throws Exception
    {
	return false;	
    }
    
    public ArrayList<VendaProd> consultar(String metodo) throws Exception
    {
	return null;	
    }
    
    public boolean alterar (VendaProd vendaProd) throws Exception
    {
	return false;	
    }
    
    public boolean excluir(int id) throws Exception
    {
	return false;
    }
}
