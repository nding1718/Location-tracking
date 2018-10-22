package app.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by colt on 12/1/17.
 */

@Entity
@Table(name = "LOCATIONS") // this one is used to customize table name
public class Location {
    enum GpsStatus{
        EXCELLENT, OK, UNRELIABLE, BAD, NOFIX, UNKNOWN
    }

    public enum RunnerMovementType {
        STOPPED, IN_MOTION;

        public boolean isMoving() {
             return this != STOPPED;
        }
    }

    @Id
    @GeneratedValue
    private Long id;

    private double longitude;
    private double latitude;

    private String heading;
    private double gpsSpeed;
    private GpsStatus gpsStatus;

    private double odometer;
    private double totalRunningTime;
    private double totalIdleTime;
    private double totalCalorieBurnt;
    private String address;
    private Date timestamp = new Date();
    private String gearProvider;
    private RunnerMovementType runnerMovementType = RunnerMovementType.STOPPED;
    private String serviceType;


    @Embedded // handle the class field we should have a corresponding annotation in the unitinfo class
    @AttributeOverride(name = "bandMake", column = @Column(name= "unit_band_make"))
    private final UnitInfo unitInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fmi", column = @Column(name = "medical_fmi")),
            @AttributeOverride(name = "bfr", column = @Column(name = "medical_bfr"))
    })
    private MedicalInfo medicalInfo;


    public Location(String runningId) {
        this.unitInfo = new UnitInfo(runningId);
    }

//    @JsonCreator
//    public Location(@JsonProperty("runningId") String runningId) {
//        this.unitInfo = new UnitInfo(runningId);
//    }

    public String getRunningId() {
        return this.unitInfo == null ? null : this.unitInfo.getRunningId();
    }

}
