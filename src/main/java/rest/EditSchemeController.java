package rest;

import controllers.ErrorsController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.response.BasicResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static rest.GetSchemesController.PATH_TO_SCHEMES_FOLDER;

@RestController
public class EditSchemeController {
    ErrorsController errorsController = new ErrorsController();

    /**
     * @param name
     * @param requestObject
     * @return response with the result of adding/editing the scheme
     * @throws IOException
     */
    @PostMapping("/add")
    public BasicResponse editscheme(@RequestParam(value="name") String name, @RequestBody String requestObject) throws IOException {
        return editOrCreateFile(name,requestObject);
    }

    /**
     * @param name
     * @param str
     * @return result of adding/editing the scheme
     */
    public BasicResponse editOrCreateFile(String name, String str){
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(PATH_TO_SCHEMES_FOLDER + name + ".json"));
            writer.write(str);
            writer.close();
            return new BasicResponse(true,"File edited/created successfully");

        } catch (IOException e) {
            return new BasicResponse(false, errorsController.getStacktrace(e));
        }
    }

}
