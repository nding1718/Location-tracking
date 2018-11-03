package com.nding.backend.runninginformationanalysisservice.service.impl;

import com.nding.backend.runninginformationanalysisservice.domain.RunningInformation;
import com.nding.backend.runninginformationanalysisservice.domain.RunningInformationRepository;
import com.nding.backend.runninginformationanalysisservice.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class RunningInformationServiceImpl implements RunningInformationService {

    private RunningInformationRepository runningInformationRepository;

    // constructor dependency injection
    @Autowired
    public RunningInformationServiceImpl(RunningInformationRepository runningInformationRepository) {
        this.runningInformationRepository = runningInformationRepository;
    }

    @Override
    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList) {
        return null;
    }

    @Override
    public Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRate(heartRate, pageable);
    }

    @Override
    public Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRateGreaterThan(heartRate, pageable);
    }

    @Override
    public Page<RunningInformation> findAllRunningInformationOrderByHealthLevel(Pageable pageable) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
