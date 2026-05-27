package org.example; // mesmo pacote das classes principais

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FarmaciaTest {

    @Test
    public void testCadastroMedicamento() {
        Farmacia farmacia = new Farmacia();
        Medicamento med = new Medicamento("Dipirona", "Genérico", 5.0, 10);

        farmacia.cadastrarMedicamento(med);

        // Verifica se o medicamento foi cadastrado corretamente
        assertEquals(1, farmacia.buscarMedicamentoPorNome("Dipirona").size());
    }
}