package com.example.unitestdemo.mockito;

import javax.inject.Inject;

/**
 * Created by xiaochuang on 4/30/16.
 */
public class PasswordValidator {
    @Inject
    public PasswordValidator() {
    }

    public boolean verifyPassword(String password) {
        //假设这个方法需要联网
        return "fksourcecode".equals(password);
    }
}
