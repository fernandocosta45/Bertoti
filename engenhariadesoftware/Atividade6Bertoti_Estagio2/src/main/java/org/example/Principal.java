package br.com.exemplo;

public class Principal {
    public static void main(String[] args) {
        br.com.exemplo.Farmacia farmacia = new br.com.exemplo.Farmacia();

        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Paracetamol", "Genérico"));
        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Ibuprofeno", "Farmacêutica X"));
        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Paracetamol", "Farmacêutica Y"));

        System.out.println("Buscando medicamentos com nome 'Paracetamol':");
        for (br.com.exemplo.Medicamento medicamento : farmacia.buscarMedicamentoPorNome("Paracetamol")) {
            System.out.println("Nome: " + medicamento.getNome() + " | Fabricante: " + medicamento.getFabricante());
        }

        System.out.println("\nBuscando medicamentos com nome 'Ibuprofeno':");
        for (br.com.exemplo.Medicamento medicamento : farmacia.buscarMedicamentoPorNome("Ibuprofeno")) {
            System.out.println("Nome: " + medicamento.getNome() + " | Fabricante: " + medicamento.getFabricante());
        }
    }
}