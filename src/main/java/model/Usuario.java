package model;

import util.DocumentoUtils;

import java.time.LocalDate;

import org.mindrot.jbcrypt.BCrypt;

public class Usuario {
    private String nome;
    private String documento;
    private TipoDocumento tipoDocumento;
    private String email;
    private String senha;
    private Permissao permissao;
    private LocalDate dataNascimento;

    public Usuario(String nome, String documento, String email, String senha, Permissao permissao, LocalDate dataNascimento) {
        setNome(nome);
        setDocumento(documento);
        setEmail(email);
        setSenha(senha);
        setPermissao(permissao);
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

    public String getSenha() {
        return senha;
    }

    public boolean verificarSenha(String senha) {
        return BCrypt.checkpw(senha, this.senha);
    }

    public Permissao getPermissao() {
        return permissao;
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


    public void setSenha(String senha) {
        if (senha != null && senha.length() >= 6) {
            this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
        } else {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.");
        }
    }

    public void setPermissao(Permissao permissao) {
        if (permissao == null) {
            throw new IllegalArgumentException("Permissão inválida: " + permissao);
        }
        this.permissao = permissao;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de nascimento inválida: " + dataNascimento);
        }
        if (dataNascimento.isAfter(LocalDate.now().minusYears(18))) {
            throw new IllegalArgumentException("Usuário deve ter pelo menos 18 anos.");
        }
        this.dataNascimento = dataNascimento;
    }

    //Métodos auxiliares
    public String exibeDocumento() {
        return DocumentoUtils.formataDocumento(documento, tipoDocumento);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", permissao=" + permissao +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
