package tn.conge.domain.embeddables;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Getter
@Setter
public class DatePeriod implements Serializable {

    @Column
    private Date startDate;

    @Column
    private Date endDate;

}
