package tn.conge.domain.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.time.DateUtils;
import org.checkerframework.checker.index.qual.Positive;
import tn.conge.core.fetcher.FetchedBean;
import tn.conge.core.fetcher.UseFetchedBeans;
import tn.conge.core.properties.CongeProperties;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@MappedSuperclass
@Getter
@Setter
@RequiredArgsConstructor
@UseFetchedBeans
public abstract class VerificationCode extends BaseEntity {


    @FetchedBean
    private static CongeProperties congeProperties;
    @Column(name = "code")
    @Positive
    protected Integer code;
    @Column(name = "attempts_number")
    protected Integer attemptsNumber = 0;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    protected ValidationCodeStatus status = ValidationCodeStatus.ACTIVE;

    public static int generateRandomKey() {
        int min = Integer.parseInt("1" + "0".repeat(Math.max(0, congeProperties.getVerificationCode().getCodeDigits() - 1)));
        return ThreadLocalRandom.current().nextInt(min, min * 10);
    }

    public boolean isUsed() {
        return this.getStatus() == ValidationCodeStatus.USED;
    }

    public boolean isAttemptsNumberPassedMax() {

        return this.attemptsNumber > congeProperties.getVerificationCode().getMaxAttempts();
    }

    public boolean isExpired() {
        Date expirationDate = DateUtils.addMinutes(this.getCreatedAt(), congeProperties.getVerificationCode().getExpirationDuration());
        Date now = new Date();
        return this.getStatus() == ValidationCodeStatus.EXPIRED || expirationDate.before(now);
    }

    public void incrementAttemptsNumber() {
        this.attemptsNumber++;
    }
}
