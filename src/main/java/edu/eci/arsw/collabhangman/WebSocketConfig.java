/*
 * Copyright (C) 2016 Pivotal Software, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.arsw.collabhangman;

/**
 *
 * @author hcadavid
 */
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    
    @Value("${relayHost}")
    private String relayHost;
    
    @Value("${relayPort}")
    private String relayPort;
    
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        /*CONFIGURACION ACTIVEMQ
        config.enableStompBrokerRelay("/topic").setRelayHost(relayHost).setRelayPort(Integer.parseInt(relayPort));*/
        /*CONFIGURACION RABBITMQ*/
        config.enableStompBrokerRelay("/topic/").setRelayHost(relayHost).setRelayPort(Integer.parseInt(relayPort)).
                setClientLogin("dwmvuoia").
                setClientPasscode("sNnf7ADSvIHcU3CgfUlDK7OOBlIQrk_9").
                setSystemLogin("dwmvuoia").
                setSystemPasscode("sNnf7ADSvIHcU3CgfUlDK7OOBlIQrk_9").
                setVirtualHost("dwmvuoia");
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stompendpoint").setAllowedOrigins("*").withSockJS();
    }
}
