package apresentacao.relatorios;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import acessoBD.MariaDB.AcessoBD;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import utilidade.Alerta;

public class RelatorioUm {
	 
	public void gerar() throws JRException , SQLException, ClassNotFoundException {

	 
	//compila o relatório
	JasperReport relatorio = JasperCompileManager.compileReport( getClass().getResourceAsStream("rel1.jrxml"));
	 
	//estabelece conexão
	
		
	//implementação da interface JRDataSource para DataSource ResultSet
	
	JasperPrint impressao = JasperFillManager.fillReport( relatorio , null,  new AcessoBD().getConexao());
	
	//exibe o resultado
	JasperViewer.viewReport(impressao,true);
	}
	 
}