module estagio {
	exports apresentacao.insere;
	exports acessoBD.MariaDB;
	exports apresentacao;
	exports objeto;
	exports utilidade;
	exports negocio;


	opens apresentacao to javafx.fxml,javafx.base,javafx.controls;
	opens apresentacao.insere to javafx.fxml,javafx.base,javafx.controls;



	requires java.sql;
	requires transitive javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.fxml;
	requires transitive javafx.graphics;
	requires org.bouncycastle.provider;
	requires org.jfxtras.styles.jmetro;
}