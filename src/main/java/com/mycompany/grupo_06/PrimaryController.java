package com.mycompany.grupo_06;

import Modelos.Alerta;
import Modelos.Usuario;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tdas.ArrayList;

public class PrimaryController implements Initializable{
    
    static Usuario usuarioPrincipal;
    @FXML
    TextField tfUsuario;
    @FXML
    PasswordField tfClave;
    @FXML
    Button btIniciarSesion;
    @FXML
    Button btCrearCuenta;
    static ArrayList<Usuario> listaUsuarios;
    @FXML
    VBox panel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaUsuarios=Usuario.leerListaSerializada();
        try(FileInputStream input=new FileInputStream("src/main/resources/source/imagenFondo.jpg")){
            Image image=new Image(input,751, 500,false,false);
             BackgroundImage bImg = new BackgroundImage(image,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundPosition.CENTER,
                                                   BackgroundSize.DEFAULT);
            Background bGround = new Background(bImg);
            panel.setBackground(bGround);
        }catch(IOException e){
            System.out.println("Archivo no encontrado");            
        }
        
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        if(ValidarUsuario()==true){ 
            App.setRoot("Bienvenida");
        }else{
            Alerta.errorIniciarSesion();
            tfUsuario.clear();
            tfClave.clear();
        }       
    }
    
    public boolean ValidarUsuario() {
        String usuario = tfUsuario.getText();
        String clave = tfClave.getText();
        boolean validar=false;
        for (int i=0;i<listaUsuarios.size();i++){
            Usuario u=listaUsuarios.get(i);
            if(u.getUsuario().equals(usuario) && u.getClave().equals(clave)){
                validar=true;
                usuarioPrincipal=u;
            }
        }
        return validar;
        
    }
    
    @FXML
    private void crearUsuario(ActionEvent event) {
        VBox root=new VBox();
        
        HBox hBoton=new HBox();
        Button bVolver=new Button("Volver");
        bVolver.setPrefSize(101, 35);
        bVolver.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        hBoton.setAlignment(Pos.CENTER);
        hBoton.getChildren().add(bVolver);

        Label lbCuenta=new Label("Crear Cuenta");
        lbCuenta.setAlignment(Pos.CENTER);
        lbCuenta.setStyle("-fx-font-weight: bold; -fx-font-size:34; -fx-font-family: Segoe UI Black; -fx-text-fill: black ");
        
        TextField nombre=new TextField();
        nombre.setPromptText("Nombre");
        nombre.setAlignment(Pos.CENTER);
        nombre.setPrefSize(183, 35);
        nombre.setStyle("-fx-font-size:13; -fx-font-family: System; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius:40");
        
        TextField apellido=new TextField();
        apellido.setPromptText("Apellido");
        apellido.setAlignment(Pos.CENTER);
        apellido.setPrefSize(183, 35);
        apellido.setStyle("-fx-font-size:13; -fx-font-family: System; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius:40");
        
        TextField usuario=new TextField();
        usuario.setPromptText("Usuario");
        usuario.setAlignment(Pos.CENTER);
        usuario.setPrefSize(183, 35);
        usuario.setStyle("-fx-font-size:13; -fx-font-family: System; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius:40");
        
        TextField contra=new TextField();
        contra.setPromptText("Clave");
        contra.setAlignment(Pos.CENTER);
        contra.setPrefSize(183, 35);
        contra.setStyle("-fx-font-size:13; -fx-font-family: System; -fx-text-fill: black; -fx-background-color: white; -fx-background-radius:40");
        
        Button crear=new Button("Crear cuenta");
        crear.setAlignment(Pos.CENTER);
        crear.setPrefSize(101, 35);
        crear.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");

        root.getChildren().addAll(lbCuenta,nombre,apellido,usuario,contra,crear,hBoton);
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setFillWidth(false);
        root.setPrefSize(473, 372);
        App.scene.setRoot(root);
        
        try(FileInputStream input=new FileInputStream("src/main/resources/source/fondoBienvenida.jpg")){
            Image image=new Image(input,751, 500,false,false);
            BackgroundImage bImg = new BackgroundImage(image,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundRepeat.NO_REPEAT,
                                                   BackgroundPosition.CENTER,
                                                   BackgroundSize.DEFAULT);
            Background bGround = new Background(bImg);
            root.setBackground(bGround);
        }catch(IOException e){
            System.out.println("Archivo no encontrado");            
        }
        
        
        crear.addEventHandler(ActionEvent.ACTION, (ActionEvent t) -> {
            if(nombre.getText().equals("") || apellido.getText().equals("")|| usuario.getText().equals("")|| contra.getText().equals("")){
                Alerta.hayAlgoVacio();
            }else if(Usuario.validarExistencia(listaUsuarios, usuario.getText())){
                Alerta.usuarioYaExiste();
                
            }else{
                Usuario u=new Usuario(nombre.getText(),apellido.getText(),usuario.getText(),contra.getText());
                listaUsuarios.addLast(u);
                Usuario.escribirLista(listaUsuarios);
                Alerta.creacionCuentaExitoso();
                App.scene.setRoot(panel);

            }
        });
        
        bVolver.addEventHandler(ActionEvent.ACTION, (ActionEvent t) -> {
            try {
                App.setRoot("primary");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }
    
    public static Usuario devolverUsuario(){
        return usuarioPrincipal;
    }

    
}
