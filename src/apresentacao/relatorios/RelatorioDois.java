package apresentacao.relatorios;

import java.sql.SQLException;

import acessoBD.MariaDB.AcessoBD;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioDois {
	public void gerar() throws JRException, SQLException {
		final JasperReport relatorio = JasperCompileManager.compileReport(getClass().getResourceAsStream("rel2.jrxml"));
		final JasperPrint impressao = JasperFillManager.fillReport(relatorio, null, new AcessoBD().getConexao());
		JasperViewer.viewReport(impressao, false);
	}
}
