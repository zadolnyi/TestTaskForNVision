package vit.repository;

/**
 * Created by zadolnyi on 17.01.2019.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vit.domain.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {}
