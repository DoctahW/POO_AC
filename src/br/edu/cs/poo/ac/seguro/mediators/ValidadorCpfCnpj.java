package br.edu.cs.poo.ac.seguro.mediators;

public class ValidadorCpfCnpj {
    private static final int[] PESOS_CNPJ_DV1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] PESOS_CNPJ_DV2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static boolean ehCnpjValido(String cnpj) {
        if (cnpj == null || cnpj.length() != 14 || !StringUtils.temSomenteNumeros(cnpj)
                || todosDigitosIguais(cnpj)) {
            return false;
        }
        int dv1 = calcularDigitoCnpj(cnpj, PESOS_CNPJ_DV1);
        int dv2 = calcularDigitoCnpj(cnpj, PESOS_CNPJ_DV2);
        return dv1 == digito(cnpj, 12) && dv2 == digito(cnpj, 13);
    }

    public static boolean ehCpfValido(String cpf) {
        if (cpf == null || cpf.length() != 11 || !StringUtils.temSomenteNumeros(cpf)
                || todosDigitosIguais(cpf)) {
            return false;
        }
        int dv1 = calcularDigitoCpf(cpf, 9);
        int dv2 = calcularDigitoCpf(cpf, 10);
        return dv1 == digito(cpf, 9) && dv2 == digito(cpf, 10);
    }

    private static int calcularDigitoCpf(String cpf, int quantidade) {
        int soma = 0;
        for (int i = 0; i < quantidade; i++) {
            soma += digito(cpf, i) * (quantidade + 1 - i);
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    private static int calcularDigitoCnpj(String cnpj, int[] pesos) {
        int soma = 0;
        for (int i = 0; i < pesos.length; i++) {
            soma += digito(cnpj, i) * pesos[i];
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    private static int digito(String str, int indice) {
        return str.charAt(indice) - '0';
    }

    private static boolean todosDigitosIguais(String str) {
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(0)) {
                return false;
            }
        }
        return true;
    }
}
