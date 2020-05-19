package sw2.lab5.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sw2.lab5.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
