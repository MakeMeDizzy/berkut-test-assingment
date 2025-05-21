package kz.berkut.testing.demoservice.repo;

import kz.berkut.testing.demoservice.entity.Message;
import kz.berkut.testing.demoservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByUserOrderByCreatedAtDesc(User user);
}
