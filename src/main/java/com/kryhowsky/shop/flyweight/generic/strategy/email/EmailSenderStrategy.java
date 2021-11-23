package com.kryhowsky.shop.flyweight.generic.strategy.email;

import com.kryhowsky.shop.flyweight.generic.strategy.GenericStrategy;
import com.kryhowsky.shop.flyweight.model.EmailType;

public interface EmailSenderStrategy extends GenericStrategy<EmailType> {

    void generateEmail();

}
