module estagio {
	exports apresentacao.insere;
	exports apresentacao.relatorios;
	exports acessoBD.MariaDB;
	exports apresentacao;
	exports objeto;
	exports utilidade;
	exports negocio;

	opens apresentacao.insere;
	opens apresentacao.relatorios;
	opens acessoBD.MariaDB;
	opens apresentacao;
	opens objeto;
	opens utilidade;
	opens negocio;

	requires jasperreports;
	requires java.logging;
	requires java.naming;
	requires java.persistence;
	requires java.sql;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires org.bouncycastle.provider;
	requires org.hibernate.orm.core;
	requires org.jfxtras.styles.jmetro;
	requires net.bytebuddy;
	requires com.sun.xml.bind;
	requires java.transaction;
}