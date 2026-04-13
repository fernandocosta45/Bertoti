import java.util.List;
import java.util.LinkedList;

public class Farmacia {
    private List<Medicamento> medicamentos = new LinkedList<>();

    public void cadastrarMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }

    public List<Medicamento> buscarMedicamentoPorNome(String nome) {
        List<Medicamento> encontrados = new LinkedList<>();
        for (Medicamento medicamento : medicamentos) {
            if (medicamento.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(medicamento);
            }
        }
        return encontrados;
    }
}