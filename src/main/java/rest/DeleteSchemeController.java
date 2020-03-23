package rest;

import controllers.ErrorsController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.response.BasicResponse;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static rest.GetSchemesController.PATH_TO_SCHEMES_FOLDER;

@RestController
public class DeleteSchemeController {
    ErrorsController errorsController = new ErrorsController();

    /**
     * @param scheme
     * @return response with result of delete file
     * @throws IOException
     */
    @DeleteMapping("/delete")
    public BasicResponse deleteScheme(@RequestParam(value="name") String scheme) throws IOException {
        return deleteFile(scheme);
    }

    /**
     * @param name
     * @return response with result of delete file
     * @throws IOException
     */
    public BasicResponse deleteFile(String name) throws IOException {

        try {
            Boolean isFileDeleted = Files.deleteIfExists(Paths.get(PATH_TO_SCHEMES_FOLDER + name + ".json"));

            if (isFileDeleted) {
                return new BasicResponse(true,"File deleted successfully");
            } else {
                return new BasicResponse(false,"No such file exists");
            }
        } catch (DirectoryNotEmptyException e) {
            return new BasicResponse(false,"Directory is not empty.");

        } catch (IOException e) {
            return new BasicResponse(false, errorsController.getStacktrace(e));
        }
    }
}
