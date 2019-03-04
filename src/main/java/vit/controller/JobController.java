package vit.controller;

/**
 * Created by zadolnyi on 18.01.2019.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vit.domain.Job;
import vit.domain.enums.JobType;
import vit.model.InputJob;
import vit.model.InputJobs;
import vit.repository.JobRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class JobController {

    @Autowired
    JobRepository jobRepository;

    @PersistenceContext
    private EntityManager em;

    @PostMapping(value = "/job", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> addJobs(@RequestBody InputJobs xmlJobs){
        xmlJobs.getJob().forEach(inputJob -> {
            Job entity = new Job(
                null,
                new Long(inputJob.getId()),
                JobType.valueOf(inputJob.getType().toUpperCase()),
                inputJob.getUser(),
                inputJob.getDevice(),
                inputJob.getAmount(),
                new Date()
            );
            jobRepository.save(entity);
        });
        Map<String, Integer> responce = xmlJobs.getJob().stream().collect(
                Collectors.groupingBy(InputJob::getUser, Collectors.summingInt(InputJob::getAmount)));
        return responce;
    }

    @GetMapping(value = "/statistics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Job> search(@RequestParam Map<String, String> requestParams){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Job> cq = cb.createQuery(Job.class);
        Root<Job> root = cq.from(Job.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        requestParams.forEach((paramName, paramValue) -> {
            if (paramName.equals("timeFrom") || paramName.equals("timeTo")) {
                Date dateValue = new Date();
                try {
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    dateValue = format.parse(paramValue);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (paramName.equals("timeFrom")) {
                    predicates.add(cb.greaterThan(root.get("dateCreated"), dateValue));
                } else {
                    predicates.add(cb.lessThan(root.get("dateCreated"), dateValue));
                }
            } else if (paramName.equals("type")) {
                Integer typeIntValue = JobType.getOrdinal(paramValue);
                predicates.add(cb.equal(root.get(paramName), typeIntValue));
            } else {
                predicates.add(cb.equal(root.get(paramName), paramValue));
            }
        });
        if (predicates.size() > 0) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }
        cq.orderBy(cb.asc(root.get("dateCreated")));
        TypedQuery<Job> query = em.createQuery(cq);
        List<Job> result = query.getResultList();
        return result;
    }
}