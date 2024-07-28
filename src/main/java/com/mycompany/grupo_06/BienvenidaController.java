/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.grupo_06;

import Modelos.Emoji;
import Modelos.Seleccionador;
import Modelos.Usuario;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author DELL
 */
public class BienvenidaController implements Initializable {
    
    Usuario u;
    static Emoji emoji;
    @FXML
    Label lbBienvenida;
    @FXML
    Button btCrearEmoji;
    @FXML
    Button btCargarEmoji;
    @FXML
    Button btLimpiarHistorial;
    @FXML
    ImageView imgv;
    @FXML
    ScrollPane panelHistorial;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        u=PrimaryController.devolverUsuario();
        lbBienvenida.setText("Bienvenid@ "+u.getNombre());
        lbBienvenida.setStyle("-fx-font-weight: bold; -fx-font-size:40; -fx-font-family: Segoe UI Black; -fx-text-fill: black ");
        try(FileInputStream input=new FileInputStream("src/main/resources/source/emojiSaludando.gif")){
            Image image=new Image(input,108, 86,false,false);
            imgv.setImage(image);
        }catch(IOException e){
            System.out.println("Archivo no encontrado");            
        }
        emoji = new Emoji();
        generarHistorial(u);
    }
    
    @FXML
    private void cambiarSecondaryController() throws IOException {
        App.setRoot("secondary");
    }
    
    private void generarHistorial(Usuario usuario){
        HBox hb = new HBox();
        VBox vb = new VBox();
        int cont = 0;
        
        hb.setSpacing(30);
        hb.setAlignment(Pos.CENTER_LEFT);
        vb.setSpacing(20);
        
        
        for(Map.Entry<UUID, Emoji> uwu: usuario.getListaEmojis().entrySet()){
            System.out.println("Clave:  "+ uwu.getKey() + " Valor: " + uwu.getValue().getCuerpo());
            StackPane panel = new StackPane();
            UUID id = uwu.getKey();
            Emoji em = usuario.getListaEmojis().get(id);
            ImageView ivCuerpo = em.setImageCuerpo();
            ImageView ivBoca = em.setImageBoca();
            ImageView ivOjos = em.setImageOjos();
            ImageView ivAccesorios = em.setImageAccesorios();
            ImageView ivCejas = em.setImageCejas();
            
            ivOjos.setTranslateY(-10);
            ivBoca.setTranslateY(20);
            ivAccesorios.setTranslateY(-10);
            ivCejas.setTranslateY(-40);
            
            panel.getChildren().add(ivCuerpo);
            panel.getChildren().add(ivBoca);
            panel.getChildren().add(ivOjos);
            panel.getChildren().add(ivAccesorios);
            panel.getChildren().add(ivCejas);
            
            panel.setOnMouseClicked(e -> {
                
                try {
                    emoji = em;
                    App.setRoot("secondary");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            hb.getChildren().add(panel);
            
            cont++;
            
            if(cont == 4){
                cont = 0;
                vb.getChildren().add(hb);
                hb = new HBox();
                hb.setSpacing(30);
                hb.setAlignment(Pos.CENTER_LEFT);
            }
        }
        
        if(!hb.getChildren().isEmpty()){
            vb.getChildren().add(hb);
        }
        panelHistorial.setContent(vb);
        
      }
    
    @FXML
    private void limpiarHistorial() throws IOException{
        u.getListaEmojis().clear();
        Usuario.escribirLista(PrimaryController.listaUsuarios);
        panelHistorial.setContent(new HBox());
    }
    
    @FXML
    private void cargarEmoji() throws IOException{
        Seleccionador sc = new Seleccionador();
        emoji = sc.cargarEmoji();
        if(emoji != null){
            App.setRoot("secondary");
        }
        
    }
    
    public static Emoji devolverEmoji(){
        if(emoji == null){
            return new Emoji();
        }else{
            return emoji;
        }
        
    }
    
    @FXML
    private void cerrarSesion() throws IOException{
        App.setRoot("primary");
        u=null;
    }
    
}
