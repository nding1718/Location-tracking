package com.nding.backend.runninginformationanalysisservice.rest;

import com.nding.backend.runninginformationanalysisservice.domain.RunningInformation;
import com.nding.backend.runninginformationanalysisservice.service.RunningInformationService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RunningInformationQueryController {

    private final String defaultPage= "0";
    private final String defaultItemPerPage = "35";

    @Autowired
    private RunningInformationService runningInformationService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformationList) {
        runningInformationService.saveRunningInformation(runningInformationList);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        runningInformationService.deleteAll();
    }


    @RequestMapping(value = "/heartRate/{heartRate}", method = RequestMethod.GET)
    public Page<RunningInformation> findByHeartRate (
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = defaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = defaultItemPerPage) Integer size) {
        return runningInformationService.findByHeartRate(heartRate, new PageRequest(page, size));
    }



    @RequestMapping(value = "/heartRateGreaterThan/{heartRate}", method = RequestMethod.GET)
    public ResponseEntity<?> findByHeartRateGreaterThan(
            @PathVariable Integer heartRate,
            @RequestParam(name = "page", required = false, defaultValue = defaultPage) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = defaultItemPerPage) Integer size) {
        Page<RunningInformation> rawResults = runningInformationService.findByHeartRateGreaterThan(heartRate, new PageRequest(page, size));
        List<RunningInformation> content = rawResults.getContent();
        // Thansform runningInformation to customized JSON objects
        // JSON? we need a new dependence: simple json is one of the choice
        List<JSONObject> results = new ArrayList<>();
        for (RunningInformation item : content) {
            JSONObject info = new JSONObject();
            info.put("runningId", item.getRunningId());
            info.put("totalRunningTime", item.getTotalRunningTime());
            info.put("heartRate", item.getHeartRate());
            info.put("userId", item.getId());
            info.put("userName", item.getUserInfo().getUsername());
            info.put("userAddress", item.getUserInfo().getAddress());
            info.put("healthWarningLevel", item.getHealthWarningLevel());
            results.add(info);
        }
        return new ResponseEntity<List<JSONObject>>(results, HttpStatus.OK);
    }

}
