package br.com.estudos.apiclientes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente cadastrar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente editar(Long id, Cliente dados) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dados.getNome());
        cliente.setCpf(dados.getCpf());
        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        repository.delete(cliente);
    }

    private Cliente buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }
}
