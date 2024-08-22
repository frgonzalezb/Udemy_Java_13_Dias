package franc.HRMS.services;

import franc.HRMS.models.Job;

import java.util.List;

public interface IJobService {
    public List<Job> getAll();

    public Job getById(Long id);
}
