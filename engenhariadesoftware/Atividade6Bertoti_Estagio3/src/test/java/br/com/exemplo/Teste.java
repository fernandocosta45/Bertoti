package br.com.exemplo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class Teste {

    @Test
    void testCadastroEBuscaMedicamento() {
        Farmacia farmacia = new Farmacia();

        farmacia.cadastrarMedicamento(
                new Medicamento("Paracetamol", "Genérico", 5.50, 100)
        );

        List<Medicamento> encontrados =
                farmacia.buscarMedicamentoPorNome("Paracetamol");

        assertEquals(1, encontrados.size());
        assertEquals("Genérico", encontrados.get(0).getFabricante());
        assertEquals(5.50, encontrados.get(0).getPreco(), 0.001);
        assertEquals(100, encontrados.get(0).getQuantidade());
    }

    @Test
    void testReducaoEstoque() {
        Medicamento ibuprofeno =
                new Medicamento("Ibuprofeno", "Farmacêutica X", 12.00, 50);

        ibuprofeno.reduzirEstoque(10);

        assertEquals(40, ibuprofeno.getQuantidade());
    }
}