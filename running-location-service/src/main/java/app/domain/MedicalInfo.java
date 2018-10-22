package app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by colt on 12/1/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Embeddable
@Data
@RequiredArgsConstructor
public class MedicalInfo {
    private final Long bfr;
    private final Long fmi;

//    public MedicalInfo() {
//    }
//
//    public MedicalInfo(Long bfr, Long fmi) {
//        this.bfr = bfr;
//        this.fmi = fmi;
//    }
}
