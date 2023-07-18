import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - Inserir todos os funcionários
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

        // 3.2 - Remover o funcionário “João” da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        System.out.println("----------------------------------------------");
        System.out.println("Funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
            System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Salário: " + funcionario.getSalario().toString().replace(".", ","));
            System.out.println("Função: " + funcionario.getFuncao());
            System.out.println("----------------------------------------------");
        }

        // 3.4 - Aumentar salário em 10%
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
        }

        // 3.5 - Agrupar funcionários por função em um Map
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("Funcionários agrupados por função:");
        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            System.out.println("Funcionários:");
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("- " + funcionario.getNome());
            }
            System.out.println("----------------------------------------------");
        }

        // 3.8 - Imprimir funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("Funcionários que fazem aniversário nos meses 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome());
                System.out.println("Data de Nascimento: " + funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("----------------------------------------------");
            }
        }

        // 3.9 - Encontrar funcionário com a maior idade
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

        // Ordenar funcionários por ordem alfabética
        funcionarios.sort(Comparator.comparing(Funcionario::getNome));

        // 3.10 - Imprimir lista de funcionários por ordem alfabética
        System.out.println("Lista de funcionários por ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome());
        }
        System.out.println("----------------------------------------------");

        // 3.11 - Imprimir o total dos salários dos funcionários
        System.out.println("Total de salário de todos os funcionários:");
        BigDecimal somaSalarios = BigDecimal.valueOf(0);
        for (Funcionario funcionario : funcionarios){
            somaSalarios = somaSalarios.add(funcionario.getSalario());
        }
        System.out.println(somaSalarios);
        System.out.println("----------------------------------------------");

        // 3.12 - Calcular salários em salários mínimos
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