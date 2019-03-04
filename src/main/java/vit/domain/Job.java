package vit.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vit.domain.enums.JobType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zadolnyi on 17.01.2019.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "nvision_schema")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private Long outerId;
    private JobType type;
    @Column(name = "username")
    private String user;
    private String device;
    private int amount;
    private Date dateCreated;

}