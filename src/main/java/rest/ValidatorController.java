package rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.response.ValidatorResponse;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class ValidatorController {
    private SchemeValidator schemeValidator = new SchemeValidator();
    private final AtomicLong counter = new AtomicLong();

    /**
     * @param name
     * @param requestObject
     * @return response with result of validation
     */
    @PostMapping("/validate")
    public ValidatorResponse validator(@RequestParam(value="name") String name, @RequestBody String requestObject) {
        SchemeValidator result = schemeValidator.validate(requestObject,name);
        return new ValidatorResponse(counter.incrementAndGet(), result.status, result.details);
    }
}
