package com.example.practicahibernate1n_eduardodominguez.Controller;

import com.example.practicahibernate1n_eduardodominguez.App;
import com.example.practicahibernate1n_eduardodominguez.DAO.CocheDAO;
import com.example.practicahibernate1n_eduardodominguez.Model.Coche;
import com.example.practicahibernate1n_eduardodominguez.Util.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CocheController implements Initializable {

    @FXML
    private TableView<Coche> CochesTv;
    @FXML
    private TextField marcaTf;
    @FXML
    private TextField matriculaTf;
    @FXML
    private TextField modeloTf;
    @FXML
    private ComboBox<String> tipoCB;
    @FXML
    private TableColumn<Coche, String> matriculaCol;
    @FXML
    private TableColumn<Coche, String> marcaCol;
    @FXML
    private TableColumn<Coche, String> modeloCol;
    @FXML
    private TableColumn<Coche, String> tipoCol;
    @FXML
    private Button verMultasBtn;

    ObservableList<Coche> listadoCoches;
    CocheDAO cocheDAO = new CocheDAO();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> tiposCoche = FXCollections.observableArrayList("Hibrido", "Gasolina", "Diésel", "Eléctrico", "GLP"); // Creo una lista observable con los tipos de coche
        tipoCB.setItems(tiposCoche); // Establezco la lista de tipos de coche en el ComboBox

        // Cargo los datos del TableView asignando cada propiedad a la columna
        matriculaCol.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        marcaCol.setCellValueFactory(new PropertyValueFactory<>("marca"));
        modeloCol.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        List<Coche> listarCoches = cocheDAO.obtenerCoche(); // Obtengo la lista de coches de la base de datos
        listadoCoches = FXCollections.observableArrayList(listarCoches); // Creo una lista observable con los coches
        CochesTv.setItems(listadoCoches); // Establezco la lista de coches en el TableView
    }

    @FXML
    void onInsertarClic(ActionEvent event) {
        // Inserto los datos de los textfield y combobox en variables
        String matricula = matriculaTf.getText();
        String marca = marcaTf.getText();
        String modelo = modeloTf.getText();
        String tipo = tipoCB.getSelectionModel().getSelectedItem();

        // Compruebo que todos los datos están rellenados
        if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || tipo.isEmpty()) {
            Alerta.mostrarError("Tienes que rellenar todos los datos"); // Muestra un mensaje de error si falta algún datoç
            return;
        }
        // Compruebo que la matrícula no se encuentre en la base de datos
        if (cocheDAO.verificarExisteMatricula(matricula)) {
            Alerta.mostrarError("La matricula coincide con un vehiculo de nuestra base de datos!"); // Muestra un mensaje de error si la matrícula ya existe
            return; // Salgo si la matrícula ya existe
        }

        // Si los datos son válidos y la matrícula no existe, procedo con la inserción
        // Creo el nuevo objeto con los datos introducidos por el usuario
        Coche coche1 = new Coche(matricula, marca, modelo, tipo);
        // Inserto en mi base de datos el nuevo coche
        if (cocheDAO.insertarCoche(coche1)) {
            actualizarTabla(); // Llamo al método para que actualice los datos en la tabla
            onLimpiarClic(event); // Llamo al método para que limpie los datos
            Alerta.mostrarInformacion("Coche insertado correctamente"); // Muestra un mensaje de éxito si la inserción fue correcta
        } else {
            Alerta.mostrarError("No se ha podido insertar el coche."); // Muestra un mensaje de error si la inserción no se ha podido realizar
        }
    }

    // Método para actualizar los datos de un coche
    @FXML
    void onActualizarClic(ActionEvent event) {
        // Almaceno en una variable el coche que ha seleccionado el usuario
        Coche cocheSeleccionado = CochesTv.getSelectionModel().getSelectedItem();

        // En el caso de que no haya seleccionado nada muestro una alerta
        if (cocheSeleccionado == null) {
            Alerta.mostrarError("Debes seleccionar un coche"); // Muestra un mensaje de error si no se selecciona un coche
            return;
        }
        // Almaceno los datos de mis textfield en unas variables
        String matricula = matriculaTf.getText();
        String marca = marcaTf.getText();
        String modelo = modeloTf.getText();
        String tipo = tipoCB.getSelectionModel().getSelectedItem();

        // Verifico que no se haya modificado la matricula que se ha introducido
        if (!cocheSeleccionado.getMatricula().equals(matricula)) {
            Alerta.mostrarError("No se puede cambiar la matrícula");
            return;
        }
        // Modifico los datos del coche
        cocheSeleccionado.setMarca(marca);
        cocheSeleccionado.setModelo(modelo);
        cocheSeleccionado.setTipo(tipo);

        // Actualizo en la base de datos y muestro mensaje
        if (cocheDAO.actualizarCoche(cocheSeleccionado)) {
            Alerta.mostrarInformacion("Coche actualizado correctamente"); // Muestra mensaje de éxito si la actualización se ha hecho correctamente
            actualizarTabla(); // Llamo al método para actualizar la tabla para que muestre los cambios
            onLimpiarClic(event);
        } else {
            Alerta.mostrarError("No se ha podido actualizar el coche"); // Muestro mensaje de error si la actualización falla
        }
    }

    // Método para borrar de nuestra base de datos un coche
    @FXML
    void onBorrarClic(ActionEvent event) {

        // Obtengo y almaceno en unas variables el coche seleccionado por el usuario
        Coche coche = CochesTv.getSelectionModel().getSelectedItem();

        // Verifico si no se ha seleccionado ningún coche
        if (coche == null) {
            Alerta.mostrarError("Debes seleccionar un coche"); // Muestra un mensaje de error si no se selecciona un coche
            return; // Salgo del método si no hay coche seleccionado
        }
        // Elimino el coche de la base de datos
        if (cocheDAO.borrarCoche(coche)) {
            actualizarTabla(); // Actualizo la tabla para reflejar los cambios
            Alerta.mostrarInformacion("Coche eliminado correctamente"); // Muestra un mensaje de éxito si la eliminación se ha hecho correctamente
        } else {
            Alerta.mostrarError("No se ha podido eliminar el coche"); // Muestra un mensaje de error si la eliminación no se ha podido realizar
        }
        // Llamo al método para limpiar los datos del textfield
        onLimpiarClic(event);
    }

    @FXML
    void onSeleccionCocheClic(MouseEvent event) {

        // Almaceno el coche que ha seleccionado el usuario
        Coche cocheSeleccion = CochesTv.getSelectionModel().getSelectedItem();

        if (cocheSeleccion != null) {
            // Si selecciona un coche añado los datos en los textfields correspondiente
            matriculaTf.setText(cocheSeleccion.getMatricula());
            marcaTf.setText(cocheSeleccion.getMarca());
            modeloTf.setText(cocheSeleccion.getModelo());
            tipoCB.getSelectionModel().select(cocheSeleccion.getTipo());
        }
    }

    void actualizarTabla() {
        // Método para actualizar los datos de la tabla trás hacer cambios
        List<Coche> listadoCoches = cocheDAO.obtenerCoche(); // Obtengo la lista de coches desde la base de datos
        CochesTv.getItems().setAll(listadoCoches); // Actualizo el TableView con la nueva lista de coches
    }

    // Método para limpiar los datos de los textfield
    @FXML
    void onLimpiarClic(ActionEvent event) {
        matriculaTf.clear();
        marcaTf.clear();
        modeloTf.clear();
        tipoCB.getSelectionModel().clearSelection();
    }

    public void onVerMultasClic(ActionEvent event) throws IOException {
        // Primero, obtengo y guardo en la varible 'cocheSeleccionado' el coche que el usuario ha seleccionado en el TableView
        Coche cocheSeleccionado = CochesTv.getSelectionModel().getSelectedItem();

        // Compruebo si efectivamente se seleccionó un coche.
        if (cocheSeleccionado != null) {
            try {
                // Cargo la interfaz de multas desde el archivo 'multas.fxml' usando un FXMLLoader.
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/ui/multas.fxml"));
                Parent root = fxmlLoader.load();

                // Obtengo el controlador asociado al archivo 'multas.fxml'.
                MultasController multasController = fxmlLoader.getController();

                // Llamo al método 'cargarCoche' en el controlador para pasarle el coche seleccionado.
                multasController.cargarCoche(cocheSeleccionado);

                // Creo una nueva escena con la interfaz cargada.
                Scene scene = new Scene(root);
                // Recupero la ventana actual desde el botón 'verMultasBtn' y cambio la escena a la nueva.
                Stage stage = (Stage) verMultasBtn.getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception e) {
                Alerta.mostrarError("ERROR" + e.getMessage());
            }
        } else {
            // Si el usuario no ha seleccionado ningún coche, muestro un error.
            Alerta.mostrarError("Tienes que seleccionar un coche para ver sus multas");
        }
    }

}