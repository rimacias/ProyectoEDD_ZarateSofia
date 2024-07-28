/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import com.mycompany.grupo_06.App;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.Buffer;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import tdas.LCD;

/**
 *
 * @author USER
 */
public class Seleccionador {
     private FileChooser fileChooser;
     private DirectoryChooser directory;
     
     public Seleccionador(){
         fileChooser = new FileChooser();
         directory = new DirectoryChooser();
     }
     
     public void seleccionarComponente(LCD<ImageView> lista){
         fileChooser.setTitle("Seleccionar un componente");
         ExtensionFilter extensionFilter = new ExtensionFilter("Archivos PNG", "*.png");
         fileChooser.getExtensionFilters().add(extensionFilter);
         
         File selectedFile = fileChooser.showOpenDialog(App.getScene().getWindow());
         if (selectedFile != null) {
            try{
                System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
                Image img = new Image(selectedFile.toURI().toString(), 45,45,false,true);
                ImageView imgv = new ImageView(img);
                lista.addLast(imgv);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
              
        }
         
     }
     
     public Emoji cargarEmoji(){
         Emoji em = null;
         fileChooser.setTitle("Seleccionar una proyecto Emoji");
         ExtensionFilter extensionFilter = new ExtensionFilter("Archivos SER", "*.ser");
         fileChooser.getExtensionFilters().add(extensionFilter);
         
         File selectedFile = fileChooser.showOpenDialog(App.getScene().getWindow());
         
         if(selectedFile != null){
             try(ObjectInputStream objinput=new ObjectInputStream(new FileInputStream(selectedFile));){
            em = (Emoji)objinput.readObject();
            }catch(ClassNotFoundException ex){
                 System.out.println("No se encontró la clase");
            }
            catch(IOException e){
                System.out.println(e.getMessage());
            }
         }
         
         
         return em;
     }
     
    
     public void guardarEmoji(Emoji em){
         directory.setTitle("Seleccionar una carpeta para guardar el emoji");
         File carpetaDestino = directory.showDialog(App.getScene().getWindow());
         
         if(carpetaDestino != null){
             String nombre = "Emoji.ser";
             File archivoDestino = new File(carpetaDestino, nombre);
             try(FileOutputStream archivoSalida = new FileOutputStream(archivoDestino);
                     ObjectOutputStream objetoSalida = new ObjectOutputStream(archivoSalida)
                     ){
                 objetoSalida.writeObject(em);
                 
             }catch(IOException e){
                 System.out.println(e.getCause());
             }
         }
         
     }
     
     public void exportarEmoji(StackPane emoji){
         WritableImage image = emoji.snapshot(new SnapshotParameters(), null);
         BufferedImage bufferedImage = fxImage(image);
         directory.setTitle("Exportar a PNG");
         File carpetaDestino = directory.showDialog(App.getScene().getWindow());
         if(carpetaDestino != null){
             try{
                 File file = new File(carpetaDestino, "emoji.png");
                 ImageIO.write(bufferedImage, "png", file);
             }catch(IOException e){
                 System.out.println("No entro");
             }
             
         }
     }
     
     private BufferedImage fxImage(WritableImage fxImage){
         int width = (int) fxImage.getWidth();
        int height = (int) fxImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                image.setRGB(x, y, fxImage.getPixelReader().getArgb(x, y));
            }
        }
        return image;
     }
     
   
}
