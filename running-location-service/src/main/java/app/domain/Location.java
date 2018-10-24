package app.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by colt on 12/1/17.
 *
 * The reason why we use the JsonInclude annotation is because the data we received from front end is in the format of
 * json String, it havs to be transfered to List of Location to be able to saved by our method. Usually it will be handled
 * automatically, but in case we want to make some customize.
 *
 * And the non_null means if we have some fields whose value is null, we will not have it.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
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

    public Location() {
        this.unitInfo = null;
    }

    /**
     * The annotation JsonCreator means when transfer the data from json string to objects, we should use this constructor
     * And the JsonProperty explicitily tell spring that when you see a json string's running id, you should aline this two
     * @param runningId
     */
    @JsonCreator
    public Location(@JsonProperty("runningId") String runningId) {
        this.unitInfo = new UnitInfo(runningId);
    }

    public String getRunningId() {
        return this.unitInfo == null ? null : this.unitInfo.getRunningId();
    }

}
