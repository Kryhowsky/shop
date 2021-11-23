package com.kryhowsky.shop.flyweight.generic.strategy.email;

import com.kryhowsky.shop.flyweight.generic.strategy.GenericStrategy;
import com.kryhowsky.shop.flyweight.model.EmailType;

public interface EmailGeneratorStrategy extends GenericStrategy<EmailType> {

    byte[] generateEmail();

}
