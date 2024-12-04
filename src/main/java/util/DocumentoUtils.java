package util;

import model.TipoDocumento;

/**
 * Classe utilitária para validação de documentos (CPF e CNPJ).
 */
public class DocumentoUtils {

    /**
     * Valida se um CPF informado é válido.
     *
     * @param cpf CPF no formato com ou sem pontuação.
     * @return true se for válido, false caso contrário.
     */
    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.isBlank() || isSequenciaRepetida(cpf, 11)) {
            return false;
        }
        cpf = cpf.replaceAll("[^\\d]", ""); // Normaliza o CPF
        try {
            int[] pesos1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

            int primeiroDigito = calcularDV(cpf.substring(0, 9), pesos1);
            int segundoDigito = calcularDV(cpf.substring(0, 9) + primeiroDigito, pesos2);

            return cpf.equals(cpf.substring(0, 9) + primeiroDigito + segundoDigito);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao validar CPF: " + e.getMessage());
            return false;
        }
    }

    /**
     * Valida se um CNPJ informado é válido.
     *
     * @param cnpj CNPJ no formato com ou sem pontuação.
     * @return true se for válido, false caso contrário.
     */
    public static boolean isValidCNPJ(String cnpj) {
        if (cnpj == null || cnpj.isBlank() || isSequenciaRepetida(cnpj, 14)) {
            return false;
        }
        cnpj = cnpj.replaceAll("[^\\d]", "");
        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int primeiroDigito = calcularDV(cnpj.substring(0, 12), pesos1);
            int segundoDigito = calcularDV(cnpj.substring(0, 12) + primeiroDigito, pesos2);

            return cnpj.equals(cnpj.substring(0, 12) + primeiroDigito + segundoDigito);
        } catch (NumberFormatException e) {
            System.err.println("Erro ao validar CNPJ: " + e.getMessage());
            return false;
        }
    }

    /**
     * Calcula o dígito verificador para um documento.
     *
     * @param base  Base numérica do documento (sem os dígitos verificadores).
     * @param pesos Pesos a serem utilizados no cálculo.
     * @return Dígito verificador calculado.
     */
    private static int calcularDV(String base, int[] pesos) {
        if (base == null || base.length() != pesos.length) {
            throw new IllegalArgumentException("A base deve ter o mesmo tamanho que o array de pesos.");
        }
        int soma = 0;
        for (int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            if (!Character.isDigit(c)) {
                throw new IllegalArgumentException("A base deve conter apenas números.");
            }
            soma += Character.getNumericValue(c) * pesos[i];
        }
        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }

    /**
     * Verifica se o documento é uma sequência repetida ou possui tamanho inválido.
     *
     * @param documento       Documento no formato com ou sem pontuação.
     * @param tamanhoEsperado Tamanho esperado do documento (11 para CPF, 14 para CNPJ).
     * @return true se for uma sequência repetida ou tamanho inválido, false caso contrário.
     */
    private static boolean isSequenciaRepetida(String documento, int tamanhoEsperado) {
        documento = documento.replaceAll("[^\\d]", ""); // Remove caracteres não numéricos
        return documento.length() != tamanhoEsperado || documento.matches("(\\d)\\1{" + (tamanhoEsperado - 1) + "}");
    }

    /**
     * Formata o documento recebido e retorna de acordo com a formatação indicada para
     * o tipo do documento.
     *
     * @param documento     Documento a ser formatado.
     * @param tipoDocumento Tipo do documento (CPF ou CNPJ).
     * @return String contendo o tipo do documento e o documento já formatado.
     */
    public static String formataDocumento(String documento, TipoDocumento tipoDocumento) {
        documento = documento.replaceAll("[^\\d]", "");
        return tipoDocumento == TipoDocumento.CPF ?
                "Tipo do documento: " + tipoDocumento + "- Número: " + documento.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4") :
                "Tipo do documento: " + tipoDocumento + "- Número: " + documento.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }
}
