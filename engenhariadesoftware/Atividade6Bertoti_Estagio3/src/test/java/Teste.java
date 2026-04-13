package br.com.exemplo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class Teste {

    @Test
    void testCadastroEBuscaMedicamento() {
        br.com.exemplo.Farmacia farmacia = new br.com.exemplo.Farmacia();
        farmacia.cadastrarMedicamento(new br.com.exemplo.Medicamento("Paracetamol", "Genérico", 5.50, 100));

        List<br.com.exemplo.Medicamento> encontrados = farmacia.buscarMedicamentoPorNome("Paracetamol");

        assertEquals(1, encontrados.size());
        assertEquals("Genérico", encontrados.get(0).getFabricante());
        assertEquals(5.50, encontrados.get(0).getPreco(), 0.001); // ⚠️ CORRIGIDO
        assertEquals(100, encontrados.get(0).getQuantidade());
    }

    @Test
    void testReducaoEstoque() {
        br.com.exemplo.Medicamento ibuprofeno = new br.com.exemplo.Medicamento("Ibuprofeno", "Farmacêutica X", 12.00, 50);
        ibuprofeno.reduzirEstoque(10);
        assertEquals(40, ibuprofeno.getQuantidade());
    }
}