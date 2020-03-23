package rest;

import controllers.ErrorsController;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.response.GetSchemesResponse;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RefreshScope
public class GetSchemesController {
    ErrorsController errorsController = new ErrorsController();

    public static final String PATH_TO_SCHEMES_FOLDER = "schemes/";

    /**
     * @return schemes list
     * @throws IOException
     */
    @RequestMapping("/getschemes")
    public GetSchemesResponse getschemes() throws IOException {
        List<String> filesFromPath = getResourceNames();
        GetSchemesResponse response = new GetSchemesResponse();
        response.setSchemes(filesFromPath);
        return response;
    }

    /**
     * @param name
     * @return scheme JSON
     * @throws IOException
     */
    @RequestMapping("/getscheme")
    public String getscheme(@RequestParam(value="name", defaultValue="all") String name) throws IOException {
        try {
            return new String(Files.readAllBytes(Paths.get(PATH_TO_SCHEMES_FOLDER + name + ".json")), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "{\"error\":\"" + errorsController.getStacktrace(e) + "\"}";
        }
    }

    /**
     * @return files list
     */
    private List<String> getResourceNames() {
        File folder = new File(PATH_TO_SCHEMES_FOLDER);
        File[] listOfFiles = folder.listFiles();
        List<String> names = new ArrayList<>();
        System.out.println("Generating files list:");

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                names.add(i,listOfFiles[i].getName());
            }
        }

        if (names.isEmpty()) {
            names.add("No files found.");
        }
        return names;
    }
}
