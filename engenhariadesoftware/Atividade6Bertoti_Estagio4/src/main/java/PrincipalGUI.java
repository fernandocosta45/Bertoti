package br.com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PrincipalGUI extends JFrame {
    private br.com.exemplo.Farmacia farmacia;
    private JTextArea areaResultado;
    private JTextField campoNome, campoFabricante, campoPreco, campoQuantidade, campoBusca;

    public PrincipalGUI() {
        farmacia = new br.com.exemplo.Farmacia();
        setTitle("Sistema de Farmácia");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Painel de cadastro
        JPanel painelCadastro = new JPanel(new GridLayout(5, 2));
        campoNome = new JTextField();
        campoFabricante = new JTextField();
        campoPreco = new JTextField();
        campoQuantidade = new JTextField();

        painelCadastro.add(new JLabel("Nome:"));
        painelCadastro.add(campoNome);
        painelCadastro.add(new JLabel("Fabricante:"));
        painelCadastro.add(campoFabricante);
        painelCadastro.add(new JLabel("Preço:"));
        painelCadastro.add(campoPreco);
        painelCadastro.add(new JLabel("Quantidade:"));
        painelCadastro.add(campoQuantidade);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(this::cadastrarMedicamento);
        painelCadastro.add(botaoCadastrar);

        // Painel de busca
        JPanel painelBusca = new JPanel(new FlowLayout());
        campoBusca = new JTextField(15);
        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.addActionListener(this::buscarMedicamento);
        painelBusca.add(new JLabel("Buscar por nome:"));
        painelBusca.add(campoBusca);
        painelBusca.add(botaoBuscar);

        // Área de resultado
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaResultado);

        // Organização correta no BorderLayout
        add(painelCadastro, BorderLayout.NORTH);
        add(painelBusca, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void cadastrarMedicamento(ActionEvent e) {
        try {
            String nome = campoNome.getText();
            String fabricante = campoFabricante.getText();
            double preco = Double.parseDouble(campoPreco.getText());
            int quantidade = Integer.parseInt(campoQuantidade.getText());

            br.com.exemplo.Medicamento medicamento = new br.com.exemplo.Medicamento(nome, fabricante, preco, quantidade);
            farmacia.cadastrarMedicamento(medicamento);

            areaResultado.append("Cadastrado: " + medicamento + "\n");
            System.out.println("Cadastrado: " + medicamento); // também no console
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser numéricos!");
        }
    }

    private void buscarMedicamento(ActionEvent e) {
        String nomeBusca = campoBusca.getText();
        List<br.com.exemplo.Medicamento> encontrados = farmacia.buscarMedicamentoPorNome(nomeBusca);

        areaResultado.append("Resultado da busca por '" + nomeBusca + "':\n");
        if (encontrados.isEmpty()) {
            areaResultado.append("Nenhum medicamento encontrado.\n");
            System.out.println("Nenhum medicamento encontrado.");
        } else {
            for (br.com.exemplo.Medicamento m : encontrados) {
                areaResultado.append(m.toString() + "\n");
                System.out.println(m); // também no console
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PrincipalGUI gui = new PrincipalGUI();
            gui.setVisible(true);
        });
    }
}