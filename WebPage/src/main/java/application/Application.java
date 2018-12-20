package application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {
    @Autowired
    private SimpMessagingTemplate websocketMessageSender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping(value = "/status/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStatus(@RequestBody String json) {
        websocketMessageSender.convertAndSend("/topic/greetings", json);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
}
