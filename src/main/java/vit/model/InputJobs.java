package vit.model;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by zadolnyi on 18.01.2019.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "jobs")
public class InputJobs {

    private List<InputJob> job;

    public List<InputJob> getJob() {
        return job;
    }
}