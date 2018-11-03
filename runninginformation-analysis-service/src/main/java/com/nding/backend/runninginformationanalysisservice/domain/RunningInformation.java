package com.nding.backend.runninginformationanalysisservice.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@Data
@Entity
@Table(name = "RUNNING_INFORMATION")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RunningInformation {

    public enum HealthWarningLevel{
        LOW, NORMAL, HIGH;
        // we can also put the determine method in this enum;
    }

    @Id
    @GeneratedValue
    private Long id;

    //Embed UserInfo
    @Embedded
    private final UserInfo userInfo;

    private String runningId;
    private double latitude;
    private double longitude;

    private double runningDistance;
    private double totalRunningTime;

    private int heartRate;

    private HealthWarningLevel healthWarningLevel;

    private Date timesstamp = new Date();

    public RunningInformation() {
        this.userInfo = null;
    }

    public RunningInformation(String username, String address) {
        this.userInfo = new UserInfo(username, address);
    }

    @JsonCreator
    public RunningInformation(@JsonProperty("userInfo") UserInfo userInfo,
                              @JsonProperty("runningId") String runningId,
                              @JsonProperty("latitude") String latitude,
                              @JsonProperty("longitude") String longitude,
                              @JsonProperty("runningDistance") String runningDistance,
                              @JsonProperty("totalRunningTime") String totalRunningTime,
                              @JsonProperty("heartRate") String heartRate,
                              @JsonProperty("timestamp") String timesstamp) {
        this.userInfo = userInfo;
        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.timesstamp = new Date();
        this.heartRate = getRandomHeartRate(60, 200);

        if (this.heartRate > 120) {
            this.healthWarningLevel = HealthWarningLevel.HIGH;
        } else if (this.heartRate > 75) {
            this.healthWarningLevel = HealthWarningLevel.NORMAL;
        } else if (this.heartRate >= 60) {
            this.healthWarningLevel = HealthWarningLevel.LOW;
        } else {
            // option 1 : DANGER
            // option 2 : intetionally left blank
            // option 3: Exception
            // option 4: print Warnning
        }
    }

    public RunningInformation(final UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getUsername() {
        return this.userInfo == null ? null : this.userInfo.getUsername();
    }

    public String getAddress() {
        return this.userInfo == null ? null : this.userInfo.getAddress();
    }

    private int getRandomHeartRate(int min, int max) {
        Random rn = new Random();
        return min + rn.nextInt(max - min + 1);
    }
}
