/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelos;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author USER
 */
public class Alerta {
    
    public static void errorIniciarSesion(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Error al iniciar sesion");
        alerta.setContentText("Usuario o clave incorrectos");
        Optional<ButtonType> opciones = alerta.showAndWait();
    
    }
    
    public static void creacionCuentaExitoso(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Usuario nuevo");
        alert.setHeaderText("Creacion de usuario");
        alert.setContentText("Usuario creado con exito");
        alert.showAndWait();
    }
    
    public static void hayAlgoVacio(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Error al crear cuenta");
        alerta.setContentText("Alguno de los parametros se encuentra vacío");
        alerta.showAndWait();
    }
    
    public static void usuarioYaExiste(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("El usuario ya existe");
        alerta.setContentText("Elija otro nombre de usuario");
        alerta.showAndWait();
    }
    public static void imposibleEliminar(){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("Error al eliminar el componente");
        alerta.setContentText("Minimo debe existir 5 elementos");
        alerta.showAndWait();
    }
}
