package se.kry.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kry.entity.Endpoint;

@Repository
public interface EndPointRepository extends JpaRepository<Endpoint,Integer> {

 public Endpoint getByName(String name);
 public void deleteByName(String name);
}
