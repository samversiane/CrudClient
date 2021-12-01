package com.devsuperior.CrudClients.repositories;

import com.devsuperior.CrudClients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
