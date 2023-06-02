package tn.conge.core.sms.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import tn.conge.core.fetcher.FetchedBean;
import tn.conge.core.fetcher.UseFetchedBeans;
import tn.conge.core.properties.CongeProperties;
import tn.conge.core.sms.PlusEncoderInterceptor;
import tn.conge.core.sms.SmsMessage;

/**
 * @apiNote Tunisie sms has a limit of 66 character per message
 */
@Slf4j
@UseFetchedBeans
public class TunisieSmsAPI implements SmsAPI {


    @FetchedBean
    private static CongeProperties congeProperties;

    @Override
    public void commitSms(SmsMessage smsMessage) {
        if (!congeProperties.getSms().isEnabled()) {
            log.warn("SMS DISABLED: SMS NOT SENT");
            return;
        }

        StringBuilder tunisieSmsUrlBuilder = new StringBuilder();
        tunisieSmsUrlBuilder.append(congeProperties.getSms().getApiUrl());
        tunisieSmsUrlBuilder.append("?fct=sms");
        tunisieSmsUrlBuilder.append("&key=").append(congeProperties.getSms().getApiKey());
        tunisieSmsUrlBuilder.append("&mobile=").append(smsMessage.getPhone());
        tunisieSmsUrlBuilder.append("&sms=").append(smsMessage.getContent());
        tunisieSmsUrlBuilder.append("&sender=").append(congeProperties.getSms().getSender());


        try {
            RestTemplate restTemplate = new RestTemplateBuilder().interceptors(new PlusEncoderInterceptor()).build();
            restTemplate.getForEntity(tunisieSmsUrlBuilder.toString(), String.class);
        } catch (Exception e) {
            log.error("Unable to send sms to phone number : " + smsMessage.getPhone());
        }
    }
}

