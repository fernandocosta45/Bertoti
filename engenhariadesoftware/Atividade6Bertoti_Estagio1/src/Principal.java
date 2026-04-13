public class Principal {
    public static void main(String[] args) {
        Farmacia farmacia = new Farmacia();

        // Cadastrando alguns medicamentos
        farmacia.cadastrarMedicamento(new Medicamento("Paracetamol", "Genérico"));
        farmacia.cadastrarMedicamento(new Medicamento("Ibuprofeno", "Farmacêutica X"));
        farmacia.cadastrarMedicamento(new Medicamento("Paracetamol", "Farmacêutica Y")); // mesmo nome, fabricante diferente

        // Buscando por nome "Paracetamol"
        System.out.println("Buscando medicamentos com nome 'Paracetamol':");
        for (Medicamento medicamento : farmacia.buscarMedicamentoPorNome("Paracetamol")) {
            System.out.println("Nome: " + medicamento.getNome() + " | Fabricante: " + medicamento.getFabricante());
        }

        // Buscando por outro nome
        System.out.println("\nBuscando medicamentos com nome 'Ibuprofeno':");
        for (Medicamento medicamento : farmacia.buscarMedicamentoPorNome("Ibuprofeno")) {
            System.out.println("Nome: " + medicamento.getNome() + " | Fabricante: " + medicamento.getFabricante());
        }
    }
}