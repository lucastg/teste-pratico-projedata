import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    static List<Funcionario> funcionarios = new ArrayList<>();
    static NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("pt", "BR"));

    public static void main(String[] args) {

        //3.1 - Inserir todos os funcionários:
        inserirFuncionarios();

        // 3.2 - Remover o funcionário “João” da lista:
        removerFuncionarioJoao();

        // 3.3 - Imprimir todos os funcionários:
        imprimirFuncionariosFormatado();

        // 3.4 - Aumentar salário em 10%:
        aumentoSalario();

        // 3.5 - Agrupar funcionários por função em um Map && Imprimir funcionários agrupados por função:
        funcionariosAgrupadosPorFuncao();

        // 3.8 - Imprimir funcionários que fazem aniversário nos meses 10 e 12:
        imprimirAniversariantes();

        // 3.9 - Encontrar funcionário com a maior idade:
        funcionarioMaisVelho();

        // 3.10 - Imprimir lista de funcionários por ordem alfabética:
        funcionariosPorOrdemAlfabetica();

        // 3.11 - Imprimir o total dos salários dos funcionários:
        somaTodosSalarios();

        // 3.12 - Calcular salários em salários mínimos:
        qauntidadeSalariosMinimos();
    }

    private static void inserirFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }

    private static void removerFuncionarioJoao() {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));
    }

    private static void imprimirFuncionariosFormatado() {
        System.out.println("Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            String salarioFormatado = numberFormat.format(funcionario.getSalario());
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + salarioFormatado);
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("----------------------------------------------");
        }
    }

    private static void aumentoSalario() {
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        }
    }

    private static void funcionariosAgrupadosPorFuncao() {

        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        System.out.println("Funcionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            System.out.println("Funcionários:");
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("- " + funcionario.getNome());
            }
            System.out.println("----------------------------------------------");
        }
    }

    private static void imprimirAniversariantes() {
        System.out.println("Funcionários que fazem aniversário nos meses 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("----------------------------------------------");
            }
        }
    }

    private static void funcionarioMaisVelho() {
        Funcionario funcionarioMaisVelho = null;
        int idadeMaisVelho = 0;
        for (Funcionario funcionario : funcionarios) {
            int idade = LocalDate.now().getYear() - funcionario.getDataNascimento().getYear();
            if (idade > idadeMaisVelho) {
                idadeMaisVelho = idade;
                funcionarioMaisVelho = funcionario;
            }
        }
        System.out.println("Funcionário com a maior idade:");
        assert funcionarioMaisVelho != null;
        System.out.println("Nome: " + funcionarioMaisVelho.getNome());
        System.out.println("Idade: " + idadeMaisVelho);
        System.out.println("----------------------------------------------");
    }

    private static void funcionariosPorOrdemAlfabetica() {
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        System.out.println("Lista de funcionários por ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
        }
    }

    public static void somaTodosSalarios() {
        System.out.println("----------------------------------------------");
        System.out.println("Total de salário de todos os funcionários:");
        BigDecimal somaSalarios = BigDecimal.valueOf(0);
        for (Funcionario funcionario : funcionarios) {
            somaSalarios = somaSalarios.add(funcionario.getSalario());
        }

        String somaSalariosFormatado = numberFormat.format(somaSalarios);
        System.out.println(somaSalariosFormatado);
        System.out.println("----------------------------------------------");
    }

    public static void qauntidadeSalariosMinimos() {
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salários em salários mínimos:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salarioMinimoGanho = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.DOWN);
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Salários mínimos ganhos: " + salarioMinimoGanho);
            System.out.println("----------------------------------------------");
        }
    }

}