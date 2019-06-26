package apresentacao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ControladorMenuPrincipal {

    
    @FXML
    void menuTelaCaixa(ActionEvent event) {
	Parent root;
        try {          
            root = FXMLLoader.load(getClass().getResource("Caixa.fxml"));
          final  Stage stage = new Stage();
            stage.setTitle("Menu de Caixa");
            stage.setScene(new Scene(root, 450, 450));      
           stage.show();           
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuTelaCidade(ActionEvent event) {
	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Cidade.fxml"));
          final  Stage stage = new Stage();
            stage.setTitle("Menu de Cidade");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            
           
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuTelaCliente(ActionEvent event) {
	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Cliente.fxml"));
           final Stage stage = new Stage();
            stage.setTitle("Menu de Cliente");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            
          
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuTelaFuncionario(ActionEvent event) {
	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Funcionario.fxml"));
           final Stage stage = new Stage();
            stage.setTitle("Menu de Funcionario");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            
         
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuTelaProduto(ActionEvent event) {
	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Produto.fxml"));
          final  Stage stage = new Stage();
            stage.setTitle("Menu de Produto");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            
       
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void menuTelaVenda(ActionEvent event) {
	Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Venda.fxml"));
          final  Stage stage = new Stage();
            stage.setTitle("Menu de Venda");
            stage.setScene(new Scene(root, 450, 450));
            stage.show();
            
          
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
