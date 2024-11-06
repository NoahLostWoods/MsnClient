package client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MSNMainWindow extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MSN Messenger 2007");

        // Sezione di stato personale
        HBox personalStatusBox = new HBox(10);
        personalStatusBox.setPadding(new Insets(10));

        ImageView avatar = new ImageView(new Image("file:C:\\Users\\valer\\Downloads\\web.png")); // Immagine profilo
        avatar.setFitHeight(50);
        avatar.setFitWidth(50);

        Label usernameLabel = new Label("Il tuo nome utente"); // Nome utente
        ComboBox<String> statusComboBox = new ComboBox<>(); // Stato
        statusComboBox.getItems().addAll("Disponibile", "Occupato", "Invisibile");
        statusComboBox.setValue("Disponibile");

        TextField statusMessage = new TextField("Imposta un messaggio personale..."); // Messaggio personale

        personalStatusBox.getChildren().addAll(avatar, usernameLabel, statusComboBox, statusMessage);

        // Lista contatti
        ListView<HBox> contactListView = new ListView<>();
        contactListView.getItems().addAll(
                createContactItem("Alice", "Disponibile", "file:C:\\Users\\valer\\Downloads\\web.png"),
                createContactItem("Bob", "Occupato", "file:C:\\Users\\valer\\Downloads\\web.png"),
                createContactItem("Charlie", "Offline", "file:C:\\Users\\valer\\Downloads\\web.png")
        );

        // Layout principale
        VBox mainLayout = new VBox(personalStatusBox, contactListView);
        mainLayout.setSpacing(10);

        Scene scene = new Scene(mainLayout, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Metodo per creare un contatto nella lista
    private HBox createContactItem(String name, String status, String avatarPath) {
        HBox contactItem = new HBox(10);
        contactItem.setPadding(new Insets(5));

        ImageView contactAvatar = new ImageView(new Image(avatarPath));
        contactAvatar.setFitHeight(40);
        contactAvatar.setFitWidth(40);

        VBox contactInfo = new VBox();
        Label contactName = new Label(name);
        Label contactStatus = new Label(status);

        contactInfo.getChildren().addAll(contactName, contactStatus);
        contactItem.getChildren().addAll(contactAvatar, contactInfo);

        return contactItem;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
