package br.com.exemplo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class Teste {

    @Test
    void testCadastroEBuscaMedicamento() {
        br.com.exemplo.Farmacia farmacia = new br.com.exemplo.Farmacia();
        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Paracetamol", "Genérico"));

        assertEquals(1, farmacia.buscarMedicamentoPorNome("Paracetamol").size());

        java.util.List<br.com.exemplo.Medicamento> encontrados = farmacia.buscarMedicamentoPorNome("Paracetamol");
        assertEquals("Genérico", encontrados.get(0).getFabricante());
    }
}