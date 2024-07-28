/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.io.Serializable;
import java.util.UUID;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author USER
 */
public class Emoji implements Serializable{
    private final UUID id;
    private String cuerpo;
    private String ojos;
    private String boca;
    private String accesorios;
    private String cejas;
    
    public Emoji() {
        this.id = UUID.randomUUID();
        this.cuerpo = "";
        this.ojos = "";
        this.boca = "";
        this.accesorios="";
        this.cejas="";
    }

    public UUID getId() {
        return id;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getOjos() {
        return ojos;
    }

    public void setOjos(String ojos) {
        this.ojos = ojos;
    }

    public String getBoca() {
        return boca;
    }

    public void setBoca(String boca) {
        this.boca = boca;
    }

    public String getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(String accesorios) {
        this.accesorios = accesorios;
    }

    public String getCejas() {
        return cejas;
    }

    public void setCejas(String cejas) {
        this.cejas = cejas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public String toString() {
        return "Emoji{" + "id=" + id + '}';
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emoji other = (Emoji) obj;
        return this.id == other.id;
    }
    
    public void settearPartes(String img, int parte){
        switch (parte) {
            case 1:
                setCuerpo(img);
                break;
            case 2:
                setBoca(img);
                break;
            case 3:
                setOjos(img);
                break;
            case 4:
                setAccesorios(img);
                break;
            default:
                setCejas(img);
                break;
        }
    }
    
    public ImageView setImageCuerpo(){
        ImageView img = new ImageView();
        if(!cuerpo.equals("")){
            Image imagen = new Image(cuerpo);
            img.setImage(imagen);
            img.setFitWidth(110);
            img.setFitHeight(110);
        }
        
        return img;
    }
    
    public ImageView setImageOjos(){
        ImageView img = new ImageView();
        
        if(!ojos.equals("")){
            Image imagen = new Image(ojos);
            img.setImage(imagen);
            img.setFitWidth(70);
            img.setFitHeight(70);
            return img;
        }
        return img;
    }
    
    public ImageView setImageBoca(){
        ImageView img = new ImageView();
        
        if(!boca.equals("")){
            Image imagen = new Image(boca);
            img.setImage(imagen);
            img.setFitWidth(50);
            img.setFitHeight(50);
            return img;
        }
        return img;
    }
    
    public ImageView setImageAccesorios(){
        ImageView img = new ImageView();
        
        if(!accesorios.equals("")){
            Image imagen = new Image(accesorios);
            img.setImage(imagen);
            img.setFitWidth(70);
            img.setFitHeight(70);
            return img;
        }
        return img;
    }
    
    public ImageView setImageCejas(){
        ImageView img = new ImageView();
         
        if(!cejas.equals("")){
            Image imagen = new Image(cejas);
            img.setImage(imagen);
            img.setFitWidth(70);
            img.setFitHeight(70);
            return img;
        }
        return img;
    }
    
}
