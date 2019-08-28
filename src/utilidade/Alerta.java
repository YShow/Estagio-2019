package utilidade;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Alerta {
    
    private static final Alert alerta = new Alert(Alert.AlertType.NONE);
    
   public static void alertaSucesso() {
       alerta.setAlertType(AlertType.INFORMATION);
       alerta.setTitle("Inserido com sucesso");
       alerta.setHeaderText("");
       alerta.setHeight(300);
       alerta.setWidth(300);
       alerta.show();
      
   }
   
   public static void alertaErro(String erro)
   {
    alerta.setAlertType(AlertType.WARNING);
    alerta.setTitle("Ocorreu um problema");    
    	alerta.setHeaderText("Verifique os campos e informe o erro ao desenvolvedor");
    	alerta.setContentText("Erro: " + erro);
    	alerta.setResult(ButtonType.OK);
    	 alerta.setHeight(300);
         alerta.setWidth(300);
	alerta.show();
	    
   }
   
   public static void alertaCampoNulo() {
       alerta.setAlertType(AlertType.INFORMATION);
       alerta.setHeaderText("Verifique os campos");
       alerta.setContentText("Por favor verifique se todos os campos foram preenchidos");
       alerta.setResult(ButtonType.OK);
       alerta.setHeight(300);
       alerta.setWidth(300);
       alerta.show();
   }
   
}
