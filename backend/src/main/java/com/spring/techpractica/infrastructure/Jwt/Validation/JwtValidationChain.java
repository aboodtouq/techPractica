package com.spring.techpractica.infrastructure.Jwt.Validation;

import com.example.trackride.Infrastructures.DesginPatterns.Cor.Handler;
import org.springframework.stereotype.Component;

@Component
public class JwtValidationChain {

    private final JwtExpireDateValidator expireDateValidator;
    private final JwtUserIdValidator userIdValidator;

    private Handler<JwtValidationContext> chain;

    public JwtValidationChain(JwtExpireDateValidator expireDateValidator,
                              JwtUserIdValidator userIdValidator) {
        this.expireDateValidator = expireDateValidator;
        this.userIdValidator = userIdValidator;
        initChain();
    }

    private void initChain() {
        expireDateValidator.setNext(userIdValidator);
        chain = expireDateValidator;
    }

    public void validate(JwtValidationContext context) {
        chain.handle(context);
    }
}
