package pl.com.navcity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.navcity.model.User;

@Repository
public interface UserRepositoryImpl extends JpaRepository<User, String> {

    User findUserByUsername(String username);

}
