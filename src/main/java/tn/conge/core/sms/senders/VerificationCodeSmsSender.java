package tn.conge.core.sms.senders;

import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import tn.conge.core.fetcher.FetchedBean;
import tn.conge.core.fetcher.UseFetchedBeans;
import tn.conge.core.properties.CongeProperties;
import tn.conge.core.sms.SmsMessage;
import tn.conge.core.utils.MessageSourceUtils;
import tn.conge.domain.enums.Locale;


@NoArgsConstructor
@UseFetchedBeans
public class VerificationCodeSmsSender extends SmsSender {

    @FetchedBean
    private static CongeProperties congeProperties;
    private int code;
    private String phone;
    private static final Locale locale = Locale.FR;

    public VerificationCodeSmsSender(String phone, int code) {
        this.code = code;
        this.phone = phone;
    }

    @Override
    @Async
    @SneakyThrows
    public void send() {
        String message = MessageSourceUtils.fetchMessage("sms.code", locale, new Object[]{code});
        SmsMessage sms = new SmsMessage(phone, message + " " + congeProperties.getSms().getAppIdentifier());
        this.commitSms(sms);
    }
}
