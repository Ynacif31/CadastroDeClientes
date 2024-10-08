package br.com.ygor.dao;

import br.com.ygor.domain.Cliente;

import java.util.Collection;

public interface IClienteDAO {

    public boolean cadastrar(Cliente cliente);
    public Cliente excluir(Long cpf);
    public void alterar(Cliente cliente);
    public Cliente consultar (Long cpf);
    public Collection<Cliente> buscarTodos();

}
