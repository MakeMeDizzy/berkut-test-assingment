package kz.berkut.testing.demoservice.repo;

import kz.berkut.testing.demoservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    boolean existsByLogin(String login);
}
