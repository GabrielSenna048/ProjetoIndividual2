package school.sptech.back_end;

public class BackEnd {
    private String nome;
    private String email;
    private String q1Input;       // Nome do protagonista
    private String q2Checkbox;    // Resposta selecionada no checkbox (Red John)
    private String q3Input;       // Assassino em série
    private String q4Input;       // Nome da organização
    private String q5Input;       // Nome do ator
    private Integer q6Input;      // Quantidade de membros (campo tipo number)
    private String q7Select;     // Opção selecionada no select (Bebida)

    public BackEnd(String nome, String email, String q1Input, String q2Checkbox, String q3Input, String q4Input, String q5Input, Integer q6Input, String q7Select) {
        this.nome = nome;
        this.email = email;
        this.q1Input = q1Input;
        this.q2Checkbox = q2Checkbox;
        this.q3Input = q3Input;
        this.q4Input = q4Input;
        this.q5Input = q5Input;
        this.q6Input = q6Input;
        this.q7Select = q7Select;
    }

    public BackEnd() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQ1Input() {
        return q1Input;
    }

    public void setQ1Input(String q1Input) {
        this.q1Input = q1Input;
    }

    public String getQ2Checkbox() {
        return q2Checkbox;
    }

    public void setQ2Checkbox(String q2Checkbox) {
        this.q2Checkbox = q2Checkbox;
    }

    public String getQ3Input() {
        return q3Input;
    }

    public void setQ3Input(String q3Input) {
        this.q3Input = q3Input;
    }

    public String getQ4Input() {
        return q4Input;
    }

    public void setQ4Input(String q4Input) {
        this.q4Input = q4Input;
    }

    public String getQ5Input() {
        return q5Input;
    }

    public void setQ5Input(String q5Input) {
        this.q5Input = q5Input;
    }

    public Integer getQ6Input() {
        return q6Input;
    }

    public void setQ6Input(Integer q6Input) {
        this.q6Input = q6Input;
    }

    public String getQ7Select() {
        return q7Select;
    }

    public void setQ7Select(String q7Select) {
        this.q7Select = q7Select;
    }
}
