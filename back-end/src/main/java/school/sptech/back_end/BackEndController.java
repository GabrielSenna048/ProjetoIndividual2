
package school.sptech.back_end;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
@CrossOrigin(origins = "*") // 👈 ADICIONE ESTA LINHA AQUI
@RestController
@RequestMapping("/mentalista")

public class BackEndController {


    private final JdbcTemplate jdbcTemplate;

    public BackEndController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // GET - lista todos os cadastros
    @GetMapping
    public ResponseEntity<List<BackEnd>> listar() {

        String sql = "SELECT * FROM usuarios";

        List<BackEnd> cadastros = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(BackEnd.class)
        );

        return ResponseEntity.status(200).body(cadastros);
    }


    // Listar o nome por Id
    @GetMapping("/{id}")
    public ResponseEntity<BackEnd> listarPorId(@PathVariable Integer id) {

        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try {

            BackEnd cadastro = jdbcTemplate.queryForObject(
                    sql,
                    new BeanPropertyRowMapper<>(BackEnd.class),
                    id
            );

            return ResponseEntity.status(200).body(cadastro);

        } catch (EmptyResultDataAccessException e) {

            return ResponseEntity.status(404).build();
        }
    }


    // POST - cadastra um novo usuário
    @PostMapping
    public ResponseEntity<BackEnd> criar(@RequestBody BackEnd cadastro) {

        // Validação do nome
        if (cadastro.getNome() == null ||
                cadastro.getNome().isBlank()) {

            return ResponseEntity.status(400).build();
        }


        // Validação do e-mail
        if (cadastro.getEmail() == null ||
                cadastro.getEmail().isBlank() ||
                !cadastro.getEmail().contains("@")) {

            return ResponseEntity.status(400).build();
        }




        String sql = """
                INSERT INTO usuarios(nome, email) VALUES (?, ?)
                """;


        KeyHolder keyHolder = new GeneratedKeyHolder();


        jdbcTemplate.update(con -> {

            PreparedStatement ps = con.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, cadastro.getNome());
            ps.setString(2, cadastro.getEmail());


            return ps;

        }, keyHolder);


        Integer idGerado = keyHolder.getKeyAs(Integer.class);

        cadastro.setId(idGerado);

        return ResponseEntity.status(201).body(cadastro);
    }



}

