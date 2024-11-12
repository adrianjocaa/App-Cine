package AppCine.maven;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    private Cine cine = new Cine();
    private TextArea salaTextArea = new TextArea();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sistema de Cinema");
        
        VBox mainLayout = new VBox(10);

       
        Button btnVerificar = new Button("Verificar poltronas disponíveis");
        btnVerificar.setOnAction(e -> verificarPoltronas());


        Button btnComprar = new Button("Comprar Ingressos");
        btnComprar.setOnAction(e -> comprarIngresso());

        Button btnHistorico = new Button("Visualizar Compra");
        btnHistorico.setOnAction(e -> exibirHistoricoCompras());

        mainLayout.getChildren().addAll(btnVerificar, btnComprar, btnHistorico, salaTextArea);  // Adiciona o TextArea ao layout

        Scene scene = new Scene(mainLayout, 700, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void verificarPoltronas() {
        salaTextArea.setText(cine.imprimirSala());
        for (int i = 1; i <= cine.getNUMERO_FILAS(); i++) {
            for (int j = 1; j <= cine.getNUMERO_COLUNAS(); j++) {
                Button seatButton = new Button("D");
                seatButton.setStyle("-fx-background-color: #00CED1;"); 
                int fila = i, coluna = j;
            }

        }
    }

    private void comprarIngresso() {
        Stage compraStage = new Stage();
        compraStage.setTitle("Comprar Ingresso");

        VBox compraLayout = new VBox(10);

        TextField nomeClienteField = new TextField();
        nomeClienteField.setPromptText("Digite seu nome");

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);

        for (int i = 1; i <= cine.getNUMERO_FILAS(); i++) {
            for (int j = 1; j <= cine.getNUMERO_COLUNAS(); j++) {
                Button seatButton = new Button("D");
                seatButton.setStyle("-fx-background-color: #00CED1;"); 
                int fila = i, coluna = j;
                seatButton.setOnAction(e -> {
                    String nomeCliente = nomeClienteField.getText().trim();
                    if (nomeCliente.isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Por favor, insira o nome do cliente antes de selecionar o assento.");
                        alert.show();
                    } else {
                        boolean disponivel = cine.verificarPoltrona(fila, coluna);
                        if (disponivel) {
                            cine.comprarIngresso(fila, coluna, nomeCliente);
                            seatButton.setStyle("-fx-background-color: #FFD700;");
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Assento já ocupado!");
                            alert.show();
                        }
                    }
                });
                grid.add(seatButton, j, i);
            }
        }

        Button btnFinalizar = new Button("Finalizar Compra");
        btnFinalizar.setOnAction(e -> compraStage.close());

        compraLayout.getChildren().addAll(new Label("Nome do Cliente:"), nomeClienteField, grid, btnFinalizar);

        Scene scene = new Scene(compraLayout, 400, 450);
        compraStage.setScene(scene);
        compraStage.show();
    }

    private void exibirHistoricoCompras() {
        Stage historicoStage = new Stage();
        historicoStage.setTitle("Histórico de Compras");

        VBox historicoLayout = new VBox(10);
        TextArea historicoArea = new TextArea();
        historicoArea.setEditable(false); 
        historicoArea.setText(cine.getHistoricoCompras());


        Button btnFechar = new Button("Fechar");
        btnFechar.setOnAction(e -> historicoStage.close());

        historicoLayout.getChildren().addAll(new Label("Histórico de Compras:"), historicoArea, btnFechar);

        
        Scene scene = new Scene(historicoLayout, 400, 300);
        historicoStage.setScene(scene);
        historicoStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}