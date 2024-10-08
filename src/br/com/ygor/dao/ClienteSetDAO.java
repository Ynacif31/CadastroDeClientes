package br.com.ygor.dao;

import br.com.ygor.domain.Cliente;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClienteSetDAO implements IClienteDAO {

    private Set<Cliente> set;

    public ClienteSetDAO(){
        this.set = new HashSet<>();
    }

    @Override
    public boolean cadastrar(Cliente cliente) {
        return this.set.add(cliente);
    }

    @Override
    public Cliente excluir(Long cpf) {
        Cliente clienteEncontrado = null;
        for (Cliente cliente : this.set){
            if (cliente.getCpf().equals(cpf)){
                clienteEncontrado = cliente;
                break;
            }
        }
        if (clienteEncontrado != null){
            this.set.remove(clienteEncontrado);
        }
        return clienteEncontrado;
    }

    public void alterar(Cliente cliente) {
        for (Cliente clienteCadastrado : this.set) {
            if (clienteCadastrado.equals(cliente)) {
                clienteCadastrado.setNome(cliente.getNome());
                clienteCadastrado.setTel(cliente.getTel());
                clienteCadastrado.setNumero(cliente.getNumero());
                clienteCadastrado.setEnd(cliente.getEnd());
                clienteCadastrado.setCidade(cliente.getCidade());
                clienteCadastrado.setEstado(cliente.getEstado());
                break;
            }
        }
    }


    @Override
    public Cliente consultar(Long cpf) {
        for (Cliente clienteCadastrado : this.set){
            if (clienteCadastrado.getCpf().equals(cpf)){
                return clienteCadastrado;
            }
        }
        return null;
    }

    @Override
    public Collection<Cliente> buscarTodos() {
        return new HashSet<>(this.set);
    }


}
