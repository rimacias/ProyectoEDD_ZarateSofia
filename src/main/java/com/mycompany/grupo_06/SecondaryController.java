package com.mycompany.grupo_06;

import Modelos.Alerta;
import Modelos.Emoji;
import Modelos.Seleccionador;
import Modelos.Usuario;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tdas.LCD;


public class SecondaryController implements Initializable{
    
    @FXML
    StackPane panelEmoji;
    @FXML
    Button RandomButton;
    int imagenMax = 15;
    
    @FXML
    HBox listado;
    @FXML
    Button btCara;
    @FXML
    Button btBoca;
    @FXML
    Button btOjos;
    @FXML
    Button btAgregar;

    LCD<ImageView> Caras;
    LCD<ImageView> Bocas;
    LCD<ImageView> Ojos;
    LCD<ImageView> Accesorios;
    LCD<ImageView> Cejas;
    
    Emoji emoji;
    
    int indiceCara;
    int indiceBoca;
    int indiceOjos;
    int indiceAccesorios;
    int indiceCejas;
    
    @FXML
    ImageView imgvFdere;
    @FXML
    ImageView imgvFizq;
    @FXML
    HBox hBoxBotones;
    @FXML
    HBox hBoxPartesCuerpo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emoji = BienvenidaController.devolverEmoji();
        
        Caras = cargarCaras();
        Bocas = cargarBocas();
        Ojos = cargarOjos();

        indiceBoca = indexOfPart(emoji.getBoca(), Bocas);
        indiceOjos = indexOfPart(emoji.getOjos(), Ojos);
        indiceCara = indexOfPart(emoji.getCuerpo(), Caras);
        
        
        mostrarPartes(Bocas, indiceBoca, listado, 2);
        mostrarPartes(Ojos, indiceOjos, listado, 3);
        mostrarPartes(Caras, indiceCara, listado, 1);
        
        btCara.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {
            mostrarPartes(Caras, indiceCara, listado, 1);           
        });
        
        btBoca.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {
            mostrarPartes(Bocas, indiceBoca, listado, 2);    
        });
        
        btOjos.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {
            mostrarPartes(Ojos, indiceOjos, listado, 3);             
        });
        
        
    }
    
    public void agregarBotones(String cadena,String cadena2, LCD<ImageView> lista, int indice, HBox hb, int parte){
        hBoxBotones.getChildren().clear();
        Button btAgregarElemento=new Button(cadena);
        btAgregarElemento.setPrefSize(163, 36);
        btAgregarElemento.setStyle("-fx-font-weight: bold; -fx-font-size:14; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        Button btEliminarElemento=new Button(cadena2);
        btEliminarElemento.setPrefSize(163, 36);
        btEliminarElemento.setStyle("-fx-font-weight: bold; -fx-font-size:14; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        
        hBoxBotones.getChildren().addAll(btAgregarElemento,btEliminarElemento);
        hBoxBotones.setSpacing(20);
        hBoxBotones.setAlignment(Pos.CENTER);
        btAgregarElemento.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {
            Seleccionador sc = new Seleccionador();
            sc.seleccionarComponente(lista);
            listado.getChildren().clear();
            mostrarPartes(lista, indice, hb, parte);
        });
        
        btEliminarElemento.addEventHandler(ActionEvent.ACTION, (ActionEvent e)-> {
            if(lista.size() == 5){
                Alerta.imposibleEliminar();
            }else{
                lista.remove(indice);
                actualizarIndice(indice);
                listado.getChildren().clear();
                mostrarPartes(lista, indice, hb, parte);
            }
            
        });
    }

    public LCD<ImageView> cargarCaras(){
        Caras=new LCD<>();
        String rutaCaras = "src/main/resources/faces/";
        System.out.println(rutaCaras);
        File folder = new File(rutaCaras);
        File listFile[]= folder.listFiles();
        if(listFile!=null){
            for (int i=0;i<imagenMax;i++) {
                String imagePath = listFile[i].toURI().toString();
                Image image = new Image(imagePath,45,45,false,true);
                ImageView imgv=new ImageView();
                imgv.setImage(image);
                System.out.println(Caras.addLast(imgv));
                System.out.println(listFile[i].getPath());
            }
        }

        return Caras;
    }   
    
    public LCD<ImageView> cargarBocas(){
        Bocas=new LCD<>();
        String rutaBocas = "src/main/resources/mouth/";
        System.out.println(rutaBocas);
        File folder = new File(rutaBocas);
        File listFile[]= folder.listFiles();
        if(listFile!=null){
            for (int i=0;i<imagenMax;i++) {
                String imagePath = listFile[i].toURI().toString();
                Image image = new Image(imagePath,45,45,false,true);
                ImageView imgv=new ImageView();
                imgv.setImage(image);
                System.out.println(Bocas.addLast(imgv));
            }
        }

        return Bocas;
    }
    
    public LCD<ImageView> cargarOjos(){
        Ojos=new LCD<>();
        String rutaOjos = "src/main/resources/eyes/";
        System.out.println(rutaOjos);
        File folder = new File(rutaOjos);
        File listFile[]= folder.listFiles();
        if(listFile!=null){
            for (int i=0;i<imagenMax;i++) {
                String imagePath = listFile[i].toURI().toString();
                Image image = new Image(imagePath,45,45,true,false);
                ImageView imgv=new ImageView();
                imgv.setImage(image);
                System.out.println(Ojos.addLast(imgv));
            }
        }

        return Ojos;
    }
    
    public LCD<ImageView> cargarAccesorios(){
        Accesorios=new LCD<>();
        String rutaAccesorios = "src/main/resources/accessories/";
        System.out.println(rutaAccesorios);
        File folder = new File(rutaAccesorios);
        File listFile[]= folder.listFiles();
        if(listFile!=null){
            for (int i=0;i<imagenMax;i++) {
                String imagePath = listFile[i].toURI().toString();
                Image image = new Image(imagePath,45,45,false,true);
                ImageView imgv=new ImageView();
                imgv.setImage(image);
                System.out.println(Accesorios.addLast(imgv));
            }
        }

        return Accesorios;
    }
    
    public LCD<ImageView> cargarCejas(){
        Cejas=new LCD<>();
        String rutaCejas = "src/main/resources/eyebrows/";
        System.out.println(rutaCejas);
        File folder = new File(rutaCejas);
        File listFile[]= folder.listFiles();
        if(listFile!=null){
            for (int i=0;i<imagenMax;i++) {
                String imagePath = listFile[i].toURI().toString();
                Image image = new Image(imagePath,45,45,false,true);
                ImageView imgv=new ImageView();
                imgv.setImage(image);
                System.out.println(Cejas.addLast(imgv));
            }
        }

        return Cejas;
    }
    
    public ImageView generarFlechas(boolean Der, String ruta, ImageView iv, int indice, LCD<ImageView> lista, HBox hb, int parte){
        Image img = new Image(ruta, 50, 50, true, false);
        iv = new ImageView(img);
        EventHandler<MouseEvent> evento = new EventHandler(){
            @Override
            public void handle(Event t) {
                if(Der){
                    nextElement(indice, lista, hb, parte);
                } else{
                    prevElement(indice, lista, hb, parte);
                }   
            }   
        };
        iv.setOnMouseClicked(evento);        
        return iv;
    }
    
    public ImageView generarPartes(LCD<ImageView> lista, int index, int pos){
        ImageView iv;
        switch (pos) {
            case -2:
                iv = lista.getPrevTooElement(index);
                break;
            case -1:
                iv = lista.getPrevElement(index);
                break;
            case 1:
                iv = lista.getNextElement(index);
                break;
            default:
                iv = lista.getNextTooElement(index);
                break;
        }
        return iv;
    }
    
    public void setIndice(int indice, int x){
        indice = x;
    }
    
    public int alterarIndice(int indice, int pos, int length, int parte){
        int x = indice + pos;
        
        if(x<0){
            x = length + x;
            setIndice(indice, x);
            actualizarIndices(parte, x);
            return x;
        } else if(x>=length){
            x = x - length;
            actualizarIndices(parte, x);
            setIndice(indice, x);
            return x;
        }
        
        else{
            setIndice(indice, x);
            return x;
        }
    }
    
    public ImageView eventosPartes(int pos, int indice, LCD<ImageView> lista, HBox hb, int parte){
        EventHandler<MouseEvent> evento;
        ImageView iv = generarPartes(lista, indice, pos);
        
        evento = new EventHandler(){
            
            public int index;
            
            @Override
            public void handle(Event t) {
                int length = lista.size();
                int x = alterarIndice(indice, pos, length, parte);
                hb.getChildren().clear();
                mostrarPartes(lista, x, hb, parte);
            }
            
        };
        iv.setOnMouseClicked(evento);
        
        return iv;
    }
    
    private void construirEmoji(){
        panelEmoji.getChildren().clear();
        
        ImageView ivCuerpo = emoji.setImageCuerpo();
        ImageView ivBoca = emoji.setImageBoca();
        ImageView ivOjos = emoji.setImageOjos();
        ImageView ivAccesorios=emoji.setImageAccesorios();
        ImageView ivCejas=emoji.setImageCejas();
        
        ivOjos.setTranslateY(-10);
        ivBoca.setTranslateY(20);
        ivAccesorios.setTranslateY(-10);
        ivCejas.setTranslateY(-40);
        
        panelEmoji.getChildren().add(ivCuerpo);
        panelEmoji.getChildren().add(ivBoca);
        panelEmoji.getChildren().add(ivOjos);
        panelEmoji.getChildren().add(ivAccesorios);
        panelEmoji.getChildren().add(ivCejas);
        
        
        ivCuerpo.setOnScroll(
        new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoomFactor = 1.05;
                double deltaY = event.getDeltaY();

                if (deltaY < 0){
                    zoomFactor = 0.95;
                }
                ivCuerpo.setScaleX(ivCuerpo.getScaleX() * zoomFactor);
                ivCuerpo.setScaleY(ivCuerpo.getScaleY() * zoomFactor);
                
                event.consume();
            }
        });
        
        ivBoca.setOnScroll(
        new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoomFactor = 1.05;
                double deltaY = event.getDeltaY();

                if (deltaY < 0){
                    zoomFactor = 0.95;
                }
                ivBoca.setScaleX(ivBoca.getScaleX() * zoomFactor);
                ivBoca.setScaleY(ivBoca.getScaleY() * zoomFactor);
                event.consume();
            }
        });
        
        ivOjos.setOnScroll(
        new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoomFactor = 1.05;
                double deltaY = event.getDeltaY();

                if (deltaY < 0){
                    zoomFactor = 0.95;
                }
                ivOjos.setScaleX(ivOjos.getScaleX() * zoomFactor);
                ivOjos.setScaleY(ivOjos.getScaleY() * zoomFactor);
                event.consume();
            }
        });
        
        ivAccesorios.setOnScroll(
        new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoomFactor = 1.05;
                double deltaY = event.getDeltaY();

                if (deltaY < 0){
                    zoomFactor = 0.95;
                }
                ivAccesorios.setScaleX(ivAccesorios.getScaleX() * zoomFactor);
                ivAccesorios.setScaleY(ivAccesorios.getScaleY() * zoomFactor);
                event.consume();
            }
        });

        ivCejas.setOnScroll(
        new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                double zoomFactor = 1.05;
                double deltaY = event.getDeltaY();

                if (deltaY < 0){
                    zoomFactor = 0.95;
                }
                ivCejas.setScaleX(ivCejas.getScaleX() * zoomFactor);
                ivCejas.setScaleY(ivCejas.getScaleY() * zoomFactor);
                event.consume();
            }
        });
        
     
    }
  
    public void mostrarPartes(LCD<ImageView> lista, int indice, HBox hb, int parte){
       listado.getChildren().clear();
        if(indice == lista.size()){
            indice = 0;
        } else if (indice == -1){
            indice = lista.size();
        }
        
        //Flecha Derecha
        File rutaFile = new File("src/main/resources/source/flechaDerecha.png") ;
        String ruta = rutaFile.toURI().toString();
        ImageView derecha = generarFlechas(true, ruta,imgvFdere, indice, lista,hb, parte);
        
        //Flecha Izquierda
        rutaFile = new File("src/main/resources/source/flechaIzquierda.png");
        ruta = rutaFile.toURI().toString();
        ImageView izquierda = generarFlechas(false, ruta,imgvFizq, indice, lista, hb, parte);
        
        ImageView img1 = eventosPartes(-2, indice, lista, hb, parte);
        ImageView img2 = eventosPartes(-1, indice, lista, hb, parte);
        ImageView img3 = eventosPartes(1, indice, lista, hb, parte);
        ImageView img4 = eventosPartes(2, indice, lista, hb, parte);
        ImageView imgPrincipal = lista.get(indice);

        hb.getChildren().add(izquierda);
        hb.getChildren().add(img1);
        hb.getChildren().add(img2);
        hb.getChildren().add(imgPrincipal);
        hb.getChildren().add(img3);
        hb.getChildren().add(img4);
        hb.getChildren().add(derecha);
        
        actualizarIndices(parte, indice);
        emoji.settearPartes(imgPrincipal.getImage().getUrl(), parte);
        construirEmoji();
        
        String cadena="";
        String cadena2="";
        switch (parte) {
            case 1:
                cadena="Agregar cara";
                cadena2="Eliminar cara";
                break;
            case 2:
                cadena="Agregar boca";
                cadena2="Eliminar boca";
                break;
            case 3:
                cadena="Agregar ojos";
                cadena2="Eliminar ojos";
                break;
            case 4:
                cadena="Agregar accesorio";
                cadena2="Eliminar accesorio";
                break;
            case 5:
                cadena="Agregar ceja";
                cadena2="Eliminar ceja";
                break;
            default:
                break;
        }
        
        agregarBotones(cadena,cadena2,lista,indice,hb,parte);
    }
    
    private void nextElement(int indice, LCD<ImageView> lista, HBox hb, int parte){
        indice++;
        hb.getChildren().clear();
        mostrarPartes(lista, indice, hb, parte);

    }
    
    private void prevElement(int indice, LCD<ImageView> lista, HBox hb, int parte){
        indice--;
        hb.getChildren().clear();
        mostrarPartes(lista, indice, hb, parte);
    }
    
    private int indexOfPart(String parte, LCD<ImageView> listaImagenes){
        int index = 0;
        for(ImageView i: listaImagenes){
            String x = i.getImage().getUrl();
            if(parte.equals(x)){
                return index;
            }
            index++;
        }
        return 0;
    }
    
    private void actualizarIndices(int parte, int cantidad){
        switch (parte) {
            case 1:
                indiceCara = cantidad;
                break;
            case 2:
                indiceBoca = cantidad;
                break;
            case 3:
                indiceOjos = cantidad;
                break;
            case 4:
                indiceAccesorios=cantidad;
                break;
            default:
                indiceCejas=cantidad;
                break;
                
        }
    }
    
    private void actualizarIndice(int indice){
        indice=0;
    }
    
    @FXML
    private void guardarEmoji() throws IOException{
        
        VBox popup=new VBox();
        
        HBox hbLabel=new HBox();
        Label l=new Label("¿Cómo desea guardar?");
        l.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: black");
        hbLabel.getChildren().add(l);
        hbLabel.setAlignment(Pos.CENTER);

        HBox hb1=new HBox();
        Button bProyecto=new Button("Proyecto");
        bProyecto.setPrefSize(101, 35);
        bProyecto.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        hb1.getChildren().add(bProyecto);
        hb1.setAlignment(Pos.CENTER);
        
        HBox hb2=new HBox();
        Button bHistorial=new Button("Historial");
        bHistorial.setPrefSize(101, 35);
        bHistorial.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        hb2.getChildren().add(bHistorial);
        hb2.setAlignment(Pos.CENTER);
        
        HBox hb3=new HBox();
        Button bPNG=new Button("PNG");
        bPNG.setPrefSize(101, 35);
        bPNG.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        hb3.getChildren().add(bPNG);
        hb3.setAlignment(Pos.CENTER);
        
        popup.getChildren().addAll(hbLabel,hb1,hb2, hb3);
        popup.setSpacing(20);
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("-fx-background-color: white");

        Scene scene2=new Scene(popup,322,200);
        Stage stage2=new Stage();
        stage2.setScene(scene2);
        stage2.show();
        
        bProyecto.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {

            stage2.close();
            
            Seleccionador sc = new Seleccionador();
            sc.guardarEmoji(emoji);
 
        });
        
        bHistorial.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {

            stage2.close();
            
            Usuario u = PrimaryController.devolverUsuario();
            u.agregarEmoji(emoji);
            Usuario.escribirLista(PrimaryController.listaUsuarios);
            try {
                App.setRoot("Bienvenida");
            } catch (IOException ex) {
                ex.printStackTrace();
            }  
            
        });
        
        bPNG.addEventHandler(ActionEvent.ACTION, (ActionEvent t) ->{
            stage2.close();
            Seleccionador sc = new Seleccionador();
            sc.exportarEmoji(panelEmoji);
            });
        
    }
    
    @FXML
    private void emojiRandom() throws IOException{
        Random rand = new Random();
        indiceCara = rand.nextInt(Caras.size());
        indiceBoca = rand.nextInt(Bocas.size());
        indiceOjos = rand.nextInt(Ojos.size());        
        listado.getChildren().clear();
        
        mostrarPartes(Caras, indiceCara, listado, 1);
        mostrarPartes(Bocas, indiceBoca, listado, 2);
        mostrarPartes(Ojos, indiceOjos, listado,3);
    }  
    
    
    @FXML
    private void agregarComponentes() throws IOException{
        VBox popup=new VBox();
        
        HBox hbLabel=new HBox();
        Label l=new Label("¿Qué desea agregar?");
        l.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: black");
        hbLabel.getChildren().add(l);
        hbLabel.setAlignment(Pos.CENTER);

        HBox hb1=new HBox();
        Button btAcc=new Button("Accesorios");
        btAcc.setPrefSize(101, 35);
        btAcc.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        hb1.getChildren().add(btAcc);
        hb1.setAlignment(Pos.CENTER);
        
        HBox hb2=new HBox();
        Button btCe=new Button("Cejas");
        btCe.setPrefSize(101, 35);
        btCe.setStyle("-fx-font-weight: bold; -fx-font-size:13; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
        hb2.getChildren().add(btCe);
        hb2.setAlignment(Pos.CENTER);
        
        popup.getChildren().addAll(hbLabel,hb1,hb2);
        popup.setSpacing(20);
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("-fx-background-color: white");

        Scene scene2=new Scene(popup,322,168);
        Stage stage2=new Stage();
        stage2.setScene(scene2);
        stage2.show();
        
        Button btAccesorios=new Button("Accesorios");
        btAccesorios.setId("b1");
        Button btCejas=new Button("Cejas");
        btCejas.setId("b2");
        
        for (Node child : hBoxPartesCuerpo.getChildren()) {
            if(child.getId().equalsIgnoreCase("b1")){
                btAcc.setDisable(true);
                System.out.println(btAcc.isDisabled());
            }    
            else if(child.getId().equalsIgnoreCase("b2")){
                btCe.setDisable(true);
                System.out.println(btCe.isDisabled());
            }
        }
        
        btAcc.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {
            
            btAccesorios.setPrefSize(106, 36);
            btAccesorios.setStyle("-fx-font-weight: bold; -fx-font-size:14; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
            hBoxPartesCuerpo.getChildren().add(btAccesorios);
            
            stage2.close();
            
            btAccesorios.addEventHandler(ActionEvent.ACTION, (ActionEvent e)-> {
                Accesorios=cargarAccesorios();
                indiceAccesorios = indexOfPart(emoji.getAccesorios(), Accesorios);
                mostrarPartes(Accesorios,indiceAccesorios, listado, 4);
            });
              
        });
        
        btCe.addEventHandler(ActionEvent.ACTION, (ActionEvent t)-> {
            
            btCejas.setPrefSize(106, 36);
            btCejas.setStyle("-fx-font-weight: bold; -fx-font-size:14; -fx-font-family: System; -fx-text-fill: white; -fx-background-color: black; -fx-background-radius:70");
            hBoxPartesCuerpo.getChildren().add(btCejas);
            
            stage2.close();
            
            btCejas.addEventHandler(ActionEvent.ACTION, (ActionEvent e)-> {
                Cejas=cargarCejas();
                indiceCejas=indexOfPart(emoji.getCejas(),Cejas);                
                mostrarPartes(Cejas,indiceCejas, listado, 5);
            });
              
        });
        
    }
    
    @FXML
    private void volver() throws IOException{
        App.setRoot("Bienvenida");
    }
}