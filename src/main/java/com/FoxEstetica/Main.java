package com.FoxEstetica;

import com.FoxEstetica.model.*;
import com.FoxEstetica.service.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClienteService clienteService = new ClienteService();
        ServicoService servicoService = new ServicoService();
        AgendamentoService agendamentoService = new AgendamentoService();
        TransacaoService transacaoService = new TransacaoService();

        while (true) {
            System.out.println("==== Fox Estética ====");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Cadastrar Serviço");
            System.out.println("4. Listar Serviços");
            System.out.println("5. Agendar Serviço");
            System.out.println("6. Listar Agendamentos por data");
            System.out.println("7. Cadastrar Transação Financeira");
            System.out.println("8. Listar Transações Financeiras");
            System.out.println("9. Remover Transação Financeira");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) {
                break;
            }

            switch (opcao) {
                case 1: {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("Placa do carro: ");
                    String placa = scanner.nextLine();
                    Cliente cliente = new Cliente(nome, telefone, placa);
                    clienteService.cadatrarClientes(cliente);
                    break;
                }
                case 2: {
                    List<Cliente> clientes = clienteService.listarClientes();
                    for (Cliente c : clientes) {
                        System.out.println(c);
                    }
                    break;
                }
                case 3: {
                    System.out.print("Nome do serviço: ");
                    String nome = scanner.nextLine();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();
                    Servico servico = new Servico(nome, preco);
                    servicoService.cadatrarServico(servico);
                    break;
                }
                case 4: {
                    List<Servico> servicos = servicoService.listarServico();
                    for (Servico s : servicos) {
                        System.out.println(s);
                    }
                    break;
                }
                case 5: {
                    System.out.print("ID do cliente: ");
                    int clienteId = scanner.nextInt();
                    System.out.print("ID do serviço: ");
                    int servicoId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Data (AAAA-MM-DD): ");
                    LocalDate data = LocalDate.parse(scanner.nextLine());
                    System.out.print("Hora (HH:MM): ");
                    LocalTime hora = LocalTime.parse(scanner.nextLine());

                    Cliente cliente = new Cliente(clienteId);
                    Servico servico = new Servico(servicoId);
                    Agendamento agendamento = new Agendamento(0, cliente, servico, data, hora);
                    agendamentoService.agendarServico(agendamento);
                    break;
                }
                case 6: {
                    System.out.print("Data dos agendamentos (AAAA-MM-DD): ");
                    LocalDate data = LocalDate.parse(scanner.nextLine());
                    List<Agendamento> agendamentos = agendamentoService.listarAgendamentosPorData(data);
                    for (Agendamento a : agendamentos) {
                        System.out.println("ID: " + a.getId() + ", Cliente ID: " + a.getCliente().getId() +
                                ", Serviço ID: " + a.getServico().getId() + ", Data: " + a.getData() + ", Hora: " + a.getHora());
                    }
                    break;
                }
                case 7: {
                    System.out.print("Data da transação (AAAA-MM-DD): ");
                    LocalDate data = LocalDate.parse(scanner.nextLine());
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Tipo (ENTRADA/SAIDA): ");
                    String tipoStr = scanner.nextLine().toUpperCase();
                    TipoTransacao tipo;
                    try {
                        tipo = TipoTransacao.valueOf(tipoStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo inválido! Use ENTRADA ou SAIDA.");
                        break;
                    }
                    TransacaoFinanceira transacao = new TransacaoFinanceira(0, data, descricao, valor, tipo);
                    transacaoService.cadastrarTransacao(transacao);
                    break;
                }
                case 8: {
                    List<TransacaoFinanceira> transacoes = transacaoService.listarTransacoes();
                    for (TransacaoFinanceira t : transacoes) {
                        System.out.println(t);
                    }
                    break;
                }
                case 9: {
                    System.out.print("ID da transação a remover: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    transacaoService.removerTransacao(id);
                    break;
                }
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println();
        }
        scanner.close();
        System.out.println("Saindo...");
    }
}