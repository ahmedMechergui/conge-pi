package tn.conge.domain.api.dtos;

import lombok.Data;
import tn.conge.domain.embeddables.DatePeriod;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link DatePeriod} entity
 */
@Data
public class DatePeriodDto implements Serializable {
    private final Date startDate;
    private final Date endDate;
}