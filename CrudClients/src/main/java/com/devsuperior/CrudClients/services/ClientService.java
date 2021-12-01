package com.devsuperior.CrudClients.services;

import com.devsuperior.CrudClients.dto.ClientDTO;
import com.devsuperior.CrudClients.entities.Client;
import com.devsuperior.CrudClients.repositories.ClientRepository;
import org.hibernate.dialect.Cache71Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
        Page<Client> list = clientRepository.findAll(pageRequest);
        return list.map(x -> new ClientDTO(x));
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return new ClientDTO(client.get());
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client client = new Client();
        DtoToEntity(clientDTO, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.getOne(id);
        DtoToEntity(clientDTO, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    private void DtoToEntity(ClientDTO clientDTO, Client client) {
        client.setName(clientDTO.getName());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
    }
}
