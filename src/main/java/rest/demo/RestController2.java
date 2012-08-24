package rest.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: Joseph Vartuli
 * Date: 25/08/12
 *
 * @since:
 */
@Controller
@RequestMapping("/apiv2")
public class RestController2 {

    @RequestMapping("/json/{id}")
    public @ResponseBody JsonRestObject getJsonResponse(@PathVariable Integer id) {
        id += 200;
        return new JsonRestObject(id);
    }

}
