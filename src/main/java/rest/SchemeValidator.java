package rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonValidator;
import controllers.ErrorsController;
import controllers.RegexMatcher;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static rest.GetSchemesController.PATH_TO_SCHEMES_FOLDER;

public class SchemeValidator {
    public boolean status;
    public String details;

    RegexMatcher regexMatcher = new RegexMatcher();
    ErrorsController errorsController = new ErrorsController();

    /**
     * @param requestData
     * @param schemaName
     * @return validation result
     */
    public SchemeValidator validate(String requestData, String schemaName) {
        SchemeValidator result = new SchemeValidator();
        result.status = false;

        try {
            String str = new String(Files.readAllBytes(Paths.get(PATH_TO_SCHEMES_FOLDER + schemaName + ".json")), StandardCharsets.UTF_8);
            final JsonNode schemaNode = JsonLoader.fromString(str);
            JsonNode requestDataJsonNode = com.github.fge.jackson.JsonLoader.fromString(requestData);

            final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
            JsonValidator validator = factory.getValidator();

            ProcessingReport processingReport = validator.validate(schemaNode, requestDataJsonNode);

            if (processingReport != null) {
                result.status = processingReport.isSuccess();
                result.details = regexMatcher.matcher(processingReport.toString(),"error:([^*]*)\\)");
            }
            return result;

        } catch (Exception e) {
            result.details = errorsController.getStacktrace(e);
            return result;
        }
    }
}
