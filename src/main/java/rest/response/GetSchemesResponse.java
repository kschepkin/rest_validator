package rest.response;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "schemes"
})
public class GetSchemesResponse {

    @JsonProperty("schemes")
    private List<String> schemes = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("schemes")
    public List<String> getSchemes() {
        return schemes;
    }

    @JsonProperty("schemes")
    public void setSchemes(List<String> schemes) {
        this.schemes = schemes;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
