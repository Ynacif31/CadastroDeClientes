package br.com.ygor;

import br.com.ygor.dao.ClienteMapDAO;
import br.com.ygor.dao.IClienteDAO;
import br.com.ygor.domain.Cliente;

import javax.swing.*;
import java.util.Collection;

public class App {

    private static IClienteDAO iClienteDAO;

    public static void main(String[] args) {
        iClienteDAO = new ClienteMapDAO();

        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração, 5 para sair, 6 para buscar todos os clientes ",
                "Cadastro", JOptionPane.INFORMATION_MESSAGE);

        while (!isOpcaoValida(opcao)) {
            if (opcao == null || "".equals(opcao)) {
                sair();
            }
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida. Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração, 5 para sair, 6 para buscar todos os clientes",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }

        while (isOpcaoValida(opcao)) {
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme exemplo: Nome, CPF, Tel, Endereço, Número, Cidade, Estado",
                        "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                cadastrar(dados);
            } else if (isConsultar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF",
                        "Consultar", JOptionPane.INFORMATION_MESSAGE);

                consultar(dados);
            } else if (isExcluir(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF do cliente que você deseja excluir",
                        "Excluir",
                        JOptionPane.INFORMATION_MESSAGE);

                excluir(dados);
            } else if (isAlterar(opcao)) {
                String dados = JOptionPane.showInputDialog(null,
                        "Digite o CPF da pessoa que você deseja alterar o cadastro",
                        "Alterar",
                        JOptionPane.INFORMATION_MESSAGE);

                alterar(dados);

            } else if (isOpcaoBuscar(opcao)) {
                buscarTodos(null);

            }
            // Atualiza a opção após cada operação
            opcao = JOptionPane.showInputDialog(null,
                    "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração, 5 para sair, 6 para buscar todos os clientes ",
                    "Cadastro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isAlterar(String opcao) {
        return "4".equals(opcao);
    }
    private static void alterar(String dados){
        try {
            Long cpf = Long.parseLong(dados);
            Cliente cliente = iClienteDAO.consultar(cpf);

            if(cliente != null){
                String novosDados = JOptionPane.showInputDialog(null,
                        "Digite os novos dados separados por vírgula, conforme exemplo: Nome, Tel, Endereço, Número, Cidade, Estado",
                        "Alterar",
                        JOptionPane.INFORMATION_MESSAGE);

                String[] dadosSeparados = novosDados.split(",");
                if (dadosSeparados.length != 6){
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cliente.setNome(dadosSeparados[0]);
                cliente.setTel(Long.valueOf(dadosSeparados[1].trim()));
                cliente.setEnd(dadosSeparados[2].trim());
                cliente.setNumero(Integer.valueOf(dadosSeparados[3].trim()));
                cliente.setCidade(dadosSeparados[4].trim());
                cliente.setEstado(dadosSeparados[5].trim());

                iClienteDAO.alterar(cliente);
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }


        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null,
                    "CPF invalido",
                    "ERROR",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isExcluir(String opcao) {
        return "3".equals(opcao);
    }
    private static void excluir(String dados){
        try {
            Cliente cliente = iClienteDAO.excluir(Long.parseLong(dados));
            if(cliente != null){
                JOptionPane.showMessageDialog(null,
                        "Cliente excluido com sucesso",
                        "Excluir",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,
                    "CPF invalido",
                    "ERROR",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isConsultar(String opcao) {
        return "2".equals(opcao);
    }

    private static void consultar(String dados) {
        try {
            // Validar se foi passado somente o CPF
            Cliente cliente = iClienteDAO.consultar(Long.parseLong(dados));
            if (cliente != null) {
                JOptionPane.showMessageDialog(null, "Cliente encontrado: " + cliente.toString(), "Consulta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de CPF inválido. Por favor, digite um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void cadastrar(String dados) {
        String[] dadosSeparados = dados.split(",");
        // Tentar validar se todos os campos estão preenchidos
        if (dadosSeparados.length != 7) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Cliente cliente = new Cliente(dadosSeparados[1], dadosSeparados[0], dadosSeparados[2],
                dadosSeparados[3], dadosSeparados[4], dadosSeparados[5], dadosSeparados[6]);
        boolean isCadastrado = iClienteDAO.cadastrar(cliente);
        if (isCadastrado) {
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cliente já se encontra cadastrado", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static boolean isCadastro(String opcao) {
        return "1".equals(opcao);
    }

    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    private static boolean isOpcaoValida(String opcao) {
        return "1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao) || "4".equals(opcao) || "5".equals(opcao) || "6".equals(opcao);
    }

    private static boolean isOpcaoSair(String opcao) {
        return "5".equals(opcao);
    }
    private static boolean isOpcaoBuscar(String opcao){
        return "6".equals(opcao);
    }
    private static void buscarTodos(String dados) {
        Collection<Cliente> clientes = iClienteDAO.buscarTodos();

        if (clientes.isEmpty()){
            JOptionPane.showMessageDialog(null,
                    "Nenhum cliente cadastrado.",
                    "Lista de Clientes",
                    JOptionPane.INFORMATION_MESSAGE);
        }else {
            StringBuilder sb = new StringBuilder("Clientes Cadastrados");
            for (Cliente cliente : clientes){
                sb.append(cliente.toString()).append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString(), "Lista de Clientes", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
