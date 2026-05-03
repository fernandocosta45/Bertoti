package br.com.exemplo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PrincipalGUI extends JFrame {

    private final Farmacia farmacia = new Farmacia();

    private JTextArea areaResultado;
    private JTextField campoNome, campoFabricante, campoPreco, campoQuantidade, campoBusca;

    public PrincipalGUI() {

        setTitle("Sistema de Farmácia");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout(10, 10));

        // =========================
        // CADASTRO
        // =========================
        JPanel painelCadastro = new JPanel(new GridLayout(5, 2, 5, 5));

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

        painelCadastro.add(new JLabel());
        painelCadastro.add(botaoCadastrar);

        // =========================
        // BUSCA
        // =========================
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));

        campoBusca = new JTextField(20);
        JButton botaoBuscar = new JButton("Buscar");

        botaoBuscar.addActionListener(this::buscarMedicamento);

        painelBusca.add(new JLabel("Buscar por nome:"));
        painelBusca.add(campoBusca);
        painelBusca.add(botaoBuscar);

        // =========================
        // RESULTADO (IMPORTANTE: CENTER)
        // =========================
        areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(areaResultado);

        // =========================
        // LAYOUT FINAL (CORRIGIDO)
        // =========================
        add(painelCadastro, BorderLayout.NORTH);
        add(painelBusca, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    // ==================================================
    // CADASTRO
    // ==================================================
    private void cadastrarMedicamento(ActionEvent e) {
        try {
            String nome = campoNome.getText().trim();
            String fabricante = campoFabricante.getText().trim();
            String precoTexto = campoPreco.getText().trim();
            String quantidadeTexto = campoQuantidade.getText().trim();

            if (nome.isEmpty() || fabricante.isEmpty()
                    || precoTexto.isEmpty() || quantidadeTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
                return;
            }

            double preco = Double.parseDouble(precoTexto);
            int quantidade = Integer.parseInt(quantidadeTexto);

            Medicamento medicamento = new Medicamento(nome, fabricante, preco, quantidade);
            farmacia.cadastrarMedicamento(medicamento);

            areaResultado.append("✔ Cadastrado: " + medicamento + "\n");

            limparCamposCadastro();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Preço e quantidade devem ser numéricos!");
        }
    }

    // ==================================================
    // BUSCA (CORRIGIDA + DEBUG VISUAL)
    // ==================================================
    private void buscarMedicamento(ActionEvent e) {

        String nomeBusca = campoBusca.getText().trim();

        List<Medicamento> encontrados = farmacia.buscarMedicamentoPorNome(nomeBusca);

        areaResultado.append("\n🔎 Busca: " + nomeBusca + "\n");

        System.out.println("BUSCA: " + nomeBusca);
        System.out.println("RESULTADOS: " + encontrados.size());

        if (encontrados.isEmpty()) {
            areaResultado.append("Nenhum medicamento encontrado.\n");
        } else {
            for (Medicamento m : encontrados) {
                areaResultado.append(
                        "Nome: " + m.getNome() +
                                " | Fabricante: " + m.getFabricante() +
                                " | Preço: " + m.getPreco() +
                                " | Estoque: " + m.getQuantidade() + "\n"
                );
            }
        }

        campoBusca.setText("");
    }

    // ==================================================
    // LIMPAR CAMPOS
    // ==================================================
    private void limparCamposCadastro() {
        campoNome.setText("");
        campoFabricante.setText("");
        campoPreco.setText("");
        campoQuantidade.setText("");

        campoNome.requestFocus();
    }

    // ==================================================
    // MAIN
    // ==================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PrincipalGUI::new);
    }
}