package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.util.List;

@Controller
@RequestMapping("/carros")
public class CarroController {

    private final JdbcTemplate jdbcTemplate;

    public CarroController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // =========================================================
    // PÁGINA HTML - LISTAGEM
    // =========================================================

    @GetMapping("/tela")
    public String abrirTela(Model model) {

        String sql = """
                SELECT ID, FABRICANTE, MODELO, ANO_FABRICACAO
                FROM CARRO
                ORDER BY ID
                """;

        List<Carro> carros = jdbcTemplate.query(sql, (rs, rowNum) -> {

            Integer ano = rs.getObject("ANO_FABRICACAO") != null
                    ? rs.getInt("ANO_FABRICACAO")
                    : null;

            return new Carro(
                    rs.getLong("ID"),
                    rs.getString("FABRICANTE"),
                    rs.getString("MODELO"),
                    ano
            );
        });

        model.addAttribute("carros", carros);

        return "carros";
    }

    // =========================================================
    // PÁGINA HTML - CADASTRAR
    // =========================================================

    @PostMapping("/cadastrar")
    public String cadastrarPelaTela(
            @RequestParam String fabricante,
            @RequestParam String modelo,
            @RequestParam Integer anoFabricacao) {

        String sql = """
                INSERT INTO CARRO
                (FABRICANTE, MODELO, ANO_FABRICACAO)
                VALUES (?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                fabricante,
                modelo,
                anoFabricacao
        );

        return "redirect:/carros/tela";
    }

    // =========================================================
    // PÁGINA HTML - FORMULÁRIO DE EDIÇÃO
    // =========================================================

    @GetMapping("/editar/{id}")
    public String abrirEdicao(
            @PathVariable Long id,
            Model model) {

        String sql = """
                SELECT ID, FABRICANTE, MODELO, ANO_FABRICACAO
                FROM CARRO
                WHERE ID = ?
                """;

        List<Carro> carros = jdbcTemplate.query(sql, (rs, rowNum) -> {

            Integer ano = rs.getObject("ANO_FABRICACAO") != null
                    ? rs.getInt("ANO_FABRICACAO")
                    : null;

            return new Carro(
                    rs.getLong("ID"),
                    rs.getString("FABRICANTE"),
                    rs.getString("MODELO"),
                    ano
            );
        }, id);

        if (carros.isEmpty()) {
            return "redirect:/carros/tela";
        }

        model.addAttribute("carro", carros.get(0));

        return "editar-carro";
    }

    // =========================================================
    // PÁGINA HTML - SALVAR EDIÇÃO
    // =========================================================

    @PostMapping("/editar/{id}")
    public String salvarEdicao(
            @PathVariable Long id,
            @RequestParam String fabricante,
            @RequestParam String modelo,
            @RequestParam Integer anoFabricacao) {

        String sql = """
                UPDATE CARRO
                SET FABRICANTE = ?,
                    MODELO = ?,
                    ANO_FABRICACAO = ?
                WHERE ID = ?
                """;

        jdbcTemplate.update(
                sql,
                fabricante,
                modelo,
                anoFabricacao,
                id
        );

        return "redirect:/carros/tela";
    }

    // =========================================================
    // PÁGINA HTML - EXCLUIR
    // =========================================================

    @PostMapping("/excluir/{id}")
    public String excluirPelaTela(
            @PathVariable Long id) {

        String sql = """
                DELETE FROM CARRO
                WHERE ID = ?
                """;

        jdbcTemplate.update(sql, id);

        return "redirect:/carros/tela";
    }

    // =========================================================
    // API REST - GET
    // =========================================================

    @GetMapping
    @ResponseBody
    public List<Carro> listarCarros() {

        String sql = """
                SELECT ID, FABRICANTE, MODELO, ANO_FABRICACAO
                FROM CARRO
                ORDER BY ID
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            Integer ano = rs.getObject("ANO_FABRICACAO") != null
                    ? rs.getInt("ANO_FABRICACAO")
                    : null;

            return new Carro(
                    rs.getLong("ID"),
                    rs.getString("FABRICANTE"),
                    rs.getString("MODELO"),
                    ano
            );
        });
    }

    // =========================================================
    // API REST - GET POR ID
    // =========================================================

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Carro> buscarCarro(
            @PathVariable Long id) {

        String sql = """
                SELECT ID, FABRICANTE, MODELO, ANO_FABRICACAO
                FROM CARRO
                WHERE ID = ?
                """;

        List<Carro> carros = jdbcTemplate.query(sql, (rs, rowNum) -> {

            Integer ano = rs.getObject("ANO_FABRICACAO") != null
                    ? rs.getInt("ANO_FABRICACAO")
                    : null;

            return new Carro(
                    rs.getLong("ID"),
                    rs.getString("FABRICANTE"),
                    rs.getString("MODELO"),
                    ano
            );
        }, id);

        if (carros.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(carros.get(0));
    }

    // =========================================================
    // API REST - POST
    // =========================================================

    @PostMapping
    @ResponseBody
    public ResponseEntity<Carro> criarCarro(
            @RequestBody Carro carro) {

        String sql = """
                INSERT INTO CARRO
                (FABRICANTE, MODELO, ANO_FABRICACAO)
                VALUES (?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {

            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    new String[]{"ID"}
            );

            ps.setString(1, carro.getFabricante());
            ps.setString(2, carro.getModelo());
            ps.setObject(3, carro.getAnoFabricacao());

            return ps;

        }, keyHolder);

        Number idGerado = keyHolder.getKey();

        if (idGerado != null) {
            carro.setId(idGerado.longValue());
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(carro);
    }

    // =========================================================
    // API REST - PUT
    // =========================================================

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Carro> atualizarCarro(
            @PathVariable Long id,
            @RequestBody Carro carro) {

        String sql = """
                UPDATE CARRO
                SET FABRICANTE = ?,
                    MODELO = ?,
                    ANO_FABRICACAO = ?
                WHERE ID = ?
                """;

        int linhasAlteradas = jdbcTemplate.update(
                sql,
                carro.getFabricante(),
                carro.getModelo(),
                carro.getAnoFabricacao(),
                id
        );

        if (linhasAlteradas == 0) {
            return ResponseEntity.notFound().build();
        }

        carro.setId(id);

        return ResponseEntity.ok(carro);
    }

    // =========================================================
    // API REST - DELETE
    // =========================================================

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> excluirCarro(
            @PathVariable Long id) {

        String sql = """
                DELETE FROM CARRO
                WHERE ID = ?
                """;

        int linhasExcluidas = jdbcTemplate.update(sql, id);

        if (linhasExcluidas == 0) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}