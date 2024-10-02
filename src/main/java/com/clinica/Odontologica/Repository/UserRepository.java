package com.clinica.Odontologica.Repository;

import java.util.Optional;

import com.clinica.Odontologica.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // Aquí puedes definir métodos adicionales si es necesario, por ejemplo:
   Optional<UserEntity> findByUsername(String username);
}
