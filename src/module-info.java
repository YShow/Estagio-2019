module estagio {
	exports apresentacao to javafx.graphics;

	opens apresentacao to javafx.fxml;
	opens apresentacao.insere to javafx.fxml;
	opens objeto to javafx.base;

	requires java.sql;
	requires transitive javafx.fxml;
	requires org.bouncycastle.provider;
	requires transitive org.jfxtras.styles.jmetro;
}