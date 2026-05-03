package br.com.exemplo;

import java.util.List;
import java.util.LinkedList;

public class Farmacia {
    private List<br.com.exemplo.Medicamento> medicamentos = new LinkedList<>();

    public void cadastrarMedicamento(br.com.exemplo.Medicamento medicamento) {
        medicamentos.add(medicamento);
    }

    public List<br.com.exemplo.Medicamento> buscarMedicamentoPorNome(String nome) {
        List<br.com.exemplo.Medicamento> encontrados = new LinkedList<>();
        for (br.com.exemplo.Medicamento medicamento : medicamentos) {
            if (medicamento.getNome().equalsIgnoreCase(nome)) {
                encontrados.add(medicamento);
            }
        }
        return encontrados;
    }
}