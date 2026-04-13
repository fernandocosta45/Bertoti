package br.com.exemplo;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        br.com.exemplo.Farmacia farmacia = new br.com.exemplo.Farmacia();

        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Paracetamol", "Genérico", 5.50, 100));
        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Ibuprofeno", "Farmacêutica X", 12.00, 50));
        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Paracetamol", "Farmacêutica Y", 6.00, 80));

        System.out.println("Buscando medicamentos com nome 'Paracetamol':");
        List<br.com.exemplo.Medicamento> lista = farmacia.buscarMedicamentoPorNome("Paracetamol");

        for (br.com.exemplo.Medicamento medicamento : lista) {
            System.out.println(medicamento);
        }

        System.out.println("\nReduzindo estoque de Ibuprofeno em 10 unidades...");

        List<br.com.exemplo.Medicamento> ibus = farmacia.buscarMedicamentoPorNome("Ibuprofeno");

        if (!ibus.isEmpty()) {
            br.com.exemplo.Medicamento ibuprofeno = ibus.get(0);
            ibuprofeno.reduzirEstoque(10);
            System.out.println("Novo estoque: " + ibuprofeno.getQuantidade());
        } else {
            System.out.println("Ibuprofeno não encontrado!");
        }
    }
}