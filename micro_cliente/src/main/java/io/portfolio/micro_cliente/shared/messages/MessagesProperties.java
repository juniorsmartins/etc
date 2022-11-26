package io.portfolio.micro_cliente.shared.messages;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "message.user")
@Getter
@Setter
public class MessagesProperties {
    private String resourceNotFound;
    private String businessRuleViolated;
}
