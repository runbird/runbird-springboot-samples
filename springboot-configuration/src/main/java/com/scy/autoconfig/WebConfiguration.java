package com.scy.autoconfig;

import org.springframework.context.annotation.Configuration;
/*import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;*/


/**
 * 类名： WebConfiguration <br>
 * 描述：TODO <br>
 * 创建日期： 2019/5/30 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Configuration
public class WebConfiguration {

/*    @Bean
    public RouterFunction<ServerResponse> helloWorld2() {
        return route(GET("hello-world"),
                request -> ok().body(Mono.just("HelloWorld"), String.class)
        );
    }

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.printf("当前 WebServer 实现类为 %s", event.getWebServer().getClass().getName());
    }*/

}
