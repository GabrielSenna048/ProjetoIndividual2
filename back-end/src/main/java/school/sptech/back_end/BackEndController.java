
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
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mentalista")

public class BackEndController {


    private final JdbcTemplate jdbcTemplate;

    public BackEndController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




    @GetMapping("/ranking")
    public ResponseEntity<List<BackEnd>> obterRanking() {


        String sql = """
            SELECT u.nome, CAST(r.pontuacao_final AS CHAR) AS q1_input 
            FROM quiz_respostas r 
            JOIN usuarios u ON r.usuario_id = u.id 
            ORDER BY r.pontuacao_final DESC
            """;

        List<BackEnd> ranking = jdbcTemplate.query(
                sql,
                new BeanPropertyRowMapper<>(BackEnd.class)
        );

        if (ranking.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(ranking);
    }




    // Cadastrar um novo usuário
    @PostMapping
    public ResponseEntity<BackEnd> criar(@RequestBody BackEnd cadastro) {


        if (cadastro.getNome() == null ||
                cadastro.getNome().isBlank()) {

            return ResponseEntity.status(400).build();
        }



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
    @PostMapping("/validar-quiz")
    public ResponseEntity<Integer> validarQuiz(@RequestBody BackEnd respostas) {
        int acertos = 0;


        if (respostas.getQ1Input() != null &&
                (respostas.getQ1Input().trim().toLowerCase().equals("patrick jane") || respostas.getQ1Input().trim().toLowerCase().equals("patrick"))) {
            acertos++;
        }
        if ("mccallister".equals(respostas.getQ2Checkbox())) {
            acertos++;
        }
        if (respostas.getQ3Input() != null &&
                (respostas.getQ3Input().trim().toLowerCase().equals("red john") || respostas.getQ3Input().trim().toLowerCase().equals("redjohn"))) {
            acertos++;
        }
        if (respostas.getQ4Input() != null &&
                (respostas.getQ4Input().trim().toLowerCase().equals("associação blake") || respostas.getQ4Input().trim().toLowerCase().equals("associacao blake") || respostas.getQ4Input().trim().toLowerCase().equals("blake association"))) {
            acertos++;
        }
        if (respostas.getQ5Input() != null &&
                (respostas.getQ5Input().trim().toLowerCase().equals("simon baker") || respostas.getQ5Input().trim().toLowerCase().equals("simon"))) {
            acertos++;
        }
        if (respostas.getQ6Input() != null && respostas.getQ6Input() == 5) {
            acertos++;
        }
        if ("cha".equals(respostas.getQ7Select())) {
            acertos++;
        }


        if (respostas.getId() != null) {
            String sqlInsert = """
                INSERT INTO quiz_respostas (usuario_id, q1_input, q2_checkbox, q3_input, q4_input, q5_input, q6_input, q7_select, pontuacao_final)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

            jdbcTemplate.update(
                    sqlInsert,
                    respostas.getId(),
                    respostas.getQ1Input(),
                    respostas.getQ2Checkbox(),
                    respostas.getQ3Input(),
                    respostas.getQ4Input(),
                    respostas.getQ5Input(),
                    respostas.getQ6Input(),
                    respostas.getQ7Select(),
                    acertos
            );
        }

        return ResponseEntity.status(200).body(acertos);
    }








}

