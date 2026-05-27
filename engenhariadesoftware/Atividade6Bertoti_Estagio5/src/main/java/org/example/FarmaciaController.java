package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FarmaciaController {

    @FXML private TextField nomeField;
    @FXML private TextField fabricanteField;
    @FXML private TextField precoField;
    @FXML private TextField quantidadeField;
    @FXML private TextField buscarField;

    @FXML private TableView<Medicamento> tabelaMedicamentos;
    @FXML private TableColumn<Medicamento, String> nomeCol;
    @FXML private TableColumn<Medicamento, String> fabricanteCol;
    @FXML private TableColumn<Medicamento, Double> precoCol;
    @FXML private TableColumn<Medicamento, Integer> quantidadeCol;

    private ObservableList<Medicamento> listaMedicamentos = FXCollections.observableArrayList();
    private MedicamentoDAO dao;

    @FXML
    public void initialize() {
        // Configura as colunas da tabela
        nomeCol.setCellValueFactory(new PropertyValueFactory<>("nome"));
        fabricanteCol.setCellValueFactory(new PropertyValueFactory<>("fabricante"));
        precoCol.setCellValueFactory(new PropertyValueFactory<>("preco"));
        quantidadeCol.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        try {
            // Conexão ajustada para PostgreSQL 17
            Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/farmacia",
                    "postgres",        // usuário padrão
                    "7p#M8T4@f"        // senha definida na instalação
            );
            dao = new MedicamentoDAO(conn);

            // Carrega todos os medicamentos já cadastrados
            listaMedicamentos.addAll(dao.listarTodos());
            tabelaMedicamentos.setItems(listaMedicamentos);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar no banco. Verifique se o banco 'farmacia' existe.");
            dao = null; // evita NullPointerException
        }
    }

    @FXML
    private void cadastrarMedicamento() {
        if (dao == null) {
            System.out.println("DAO não inicializado. Verifique a conexão com o banco.");
            return;
        }

        try {
            Medicamento med = new Medicamento(
                    nomeField.getText(),
                    fabricanteField.getText(),
                    Double.parseDouble(precoField.getText()),
                    Integer.parseInt(quantidadeField.getText())
            );
            dao.inserir(med);
            listaMedicamentos.add(med);
            tabelaMedicamentos.refresh();

            // Limpa os campos
            nomeField.clear();
            fabricanteField.clear();
            precoField.clear();
            quantidadeField.clear();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buscarMedicamento() {
        if (dao == null) {
            System.out.println("DAO não inicializado. Verifique a conexão com o banco.");
            return;
        }

        try {
            listaMedicamentos.setAll(dao.buscarPorNome(buscarField.getText()));
            tabelaMedicamentos.setItems(listaMedicamentos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}