package tn.conge.core.sms.api;


import tn.conge.core.sms.SmsMessage;

public interface SmsAPI {
    void commitSms(SmsMessage smsMessage);
}
