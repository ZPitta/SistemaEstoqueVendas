package model;

import util.DocumentoUtils;

import java.time.LocalDate;

public class Cliente {
    private String nome;
    private String documento;
    private TipoDocumento tipoDocumento;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;

    public Cliente(String nome, String documento, String email, String telefone, LocalDate dataNascimento) {
        setNome(nome);
        setDocumento(documento);
        setEmail(email);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    // Setters
    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O campo do nome não pode ser vazio.");
        } else if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
            throw new IllegalArgumentException("O nome deve conter apenas letras e espaços.");
        }
        this.nome = nome.trim();
    }

    public void setDocumento(String documento) {
        if (DocumentoUtils.isValidCPF(documento)) {
            this.documento = documento;
            this.tipoDocumento = TipoDocumento.CPF;
        } else if (DocumentoUtils.isValidCNPJ(documento)) {
            this.documento = documento;
            this.tipoDocumento = TipoDocumento.CNPJ;
        } else {
            throw new IllegalArgumentException("Documento inválido: CPF ou CNPJ incorreto ou mal formatado.\n" +
                    "Documento inserido: " + documento);
        }
    }

    public void setEmail(String email) {
        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        this.email = email.trim();
    }


    public void setTelefone(String telefone) {
        if (telefone == null || telefone.isBlank()) {
            this.telefone = null;
        } else if (telefone.matches("\\(\\d{2}\\) \\d{4,5}-\\d{4}")) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone inválido. Formato esperado: (XX) XXXXX-XXXX.\n" +
                    "Formato inserido: " + telefone);
        }
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida: " + dataNascimento);
        }
        if (dataNascimento.isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("Cliente deve ter pelo menos 18 anos.");
        }
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone=" + telefone +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
