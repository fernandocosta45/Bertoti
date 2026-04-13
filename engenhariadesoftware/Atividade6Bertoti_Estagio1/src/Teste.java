import java.util.List;

public class Teste {
    public static void main(String[] args) {
        Farmacia farmacia = new Farmacia();

        // Cadastra um medicamento
        farmacia.cadastrarMedicamento(new Medicamento("Paracetamol", "Genérico"));

        // Verifica se foi cadastrado
        if (farmacia.buscarMedicamentoPorNome("Paracetamol").size() == 1) {
            System.out.println("Teste 1 OK: Medicamento cadastrado com sucesso.");
        } else {
            System.out.println("Teste 1 FALHOU: Medicamento não cadastrado.");
        }

        // Busca pelo nome
        List<Medicamento> encontrados = farmacia.buscarMedicamentoPorNome("Paracetamol");
        if (!encontrados.isEmpty() && "Genérico".equals(encontrados.get(0).getFabricante())) {
            System.out.println("Teste 2 OK: Fabricante correto encontrado.");
        } else {
            System.out.println("Teste 2 FALHOU: Fabricante incorreto.");
        }
    }
}