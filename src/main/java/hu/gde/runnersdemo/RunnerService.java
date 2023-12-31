package hu.gde.runnersdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Comparator;
import java.util.Collections;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;

    @Autowired
    public RunnerService(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    public double getAverageLaptime(Long runnerId) {
        RunnerEntity runner = runnerRepository.findById(runnerId).orElse(null);
        if (runner != null) {
            List<LapTimeEntity> laptimes = runner.getLaptimes();
            int totalTime = 0;
            for (LapTimeEntity laptime : laptimes) {
                totalTime += laptime.getTimeSeconds();
            }
            return (double) totalTime / laptimes.size();
        } else {
            return -1.0;
        }
    }
    public RunnerEntity getMaxShoeSizeRunner() {
        List<RunnerEntity> runners = runnerRepository.findAll();
        return Collections.max(runners, Comparator.comparing(runner -> runner.getShoeSize()));
    }
    public double getAveragePaceTime()
    {
        List<RunnerEntity> runners = runnerRepository.findAll();
        double sumPace = 0;
        for(RunnerEntity runner : runners) {
            sumPace += runner.getPace();
        }
        return sumPace / runners.size();
    }
}
