package api;

import pojo.SMSResponse;

public interface ISMS {
    SMSResponse sendCodeMessage(String phone, String code);
}
