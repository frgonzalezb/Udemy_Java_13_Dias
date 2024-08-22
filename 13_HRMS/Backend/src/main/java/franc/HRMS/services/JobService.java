package franc.HRMS.services;

import franc.HRMS.models.Job;

import franc.HRMS.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService implements IJobService {
    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job getById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }
}
