package org.ohnlp.n3c;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.SerializationUtils;
import org.json.simple.JSONObject;
import org.ohnlp.medtagger.ie.util.ResourceUtilManager;
import org.ohnlp.util.ZipUtil;
import org.ohnlp.web.JSONAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class N3CNLPEngine {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String rulesPackaged;
    private final RestTemplate restClient = new RestTemplate();
    private String restEndpoint ;

    public N3CNLPEngine(){
        this("fh");
    }

    public N3CNLPEngine(String ruleDir) {
        this(ruleDir, "http://localhost:8080/");
    }

    public N3CNLPEngine(String ruleDir, String endpoint) {
        initUIMAModel(ruleDir);
        this.restEndpoint = endpoint;
    }

    private void initUIMAModel(String ruleDir){
        Path ruleDirPath = Paths.get(ruleDir);
        logger.info("IE Rules:\t" + ruleDirPath.toAbsolutePath());
        this.rulesPackaged = Base64.getEncoder().encodeToString(ZipUtil.getZippedResourcesFromPath(ruleDirPath));
    }

    public HashMap<String, Collection> getResultMap(String docText) {
        throw new UnsupportedOperationException("Deprecated Functionality");
    }

    public JSONAnnotation getJSONAnnotation(String docText) {
        ObjectNode req = JsonNodeFactory.instance.objectNode();
        req.put("streamName", "n3c");
        req.put("metadata", this.rulesPackaged);
        req.put("document", docText);
        req.set("serializers", JsonNodeFactory.instance.arrayNode().add("medtagger"));
        ServerResponse resp = restClient.postForObject(this.restEndpoint, req, ServerResponse.class);
        List<MedTaggerRESTPluginResponseAnnotation> anns = null;
        try {
            anns = new ObjectMapper().readValue(resp.getContent().get("medtagger").toString()
                    , new TypeReference<List<MedTaggerRESTPluginResponseAnnotation>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            anns = Collections.emptyList();
        }
        return JSONAnnotation.generateConceptMentionBratJson(anns);

    }

    public JSONObject getResultJSON(String docText) {
        ObjectNode req = JsonNodeFactory.instance.objectNode();
        req.put("streamName", "n3c");
        req.put("metadata", this.rulesPackaged);
        req.put("document", docText);
        req.set("serializers", JsonNodeFactory.instance.arrayNode().add("medtagger"));
        ServerResponse resp = restClient.postForObject(this.restEndpoint, req, ServerResponse.class);
        List<MedTaggerRESTPluginResponseAnnotation> anns = null;
        try {
            anns = new ObjectMapper().readValue(resp.getContent().get("medtagger").toString()
                    , new TypeReference<List<MedTaggerRESTPluginResponseAnnotation>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
            anns = Collections.emptyList();
        }
        JSONAnnotation jsAnnot = JSONAnnotation.generateConceptMentionBratJson(anns);

        // build the output data
        JSONObject data = new JSONObject();
        data.put("attributes", jsAnnot.getAttribList());
        data.put("entities", jsAnnot.getCmList());
        data.put("text", docText);

        // build the output json
        JSONObject ret = new JSONObject();
        ret.put("data", data);
        ret.put("success", true);
        ret.put("msg", "text is parsed.");

        return ret;
    }

    /**
     * A Server Response to a NLP Request     */
    public static class ServerResponse {
        private long jobDuration = 0;
        private String metadata = null;
        private String message = null;
        private Map<String, JsonNode> content = null;

        public ServerResponse() {}

        public ServerResponse(long jobDuration, String metadata, String message, Map<String, JsonNode> content) {
            this.jobDuration = jobDuration;
            this.metadata = metadata;
            this.message = message;
            this.content = content;
        }

        public long getJobDuration() {
            return jobDuration;
        }

        public String getMetadata() {
            return metadata;
        }

        public String getMessage() {
            return message;
        }

        public Map<String, JsonNode> getContent() {
            return content;
        }

        public void setJobDuration(long jobDuration) {
            this.jobDuration = jobDuration;
        }

        public void setMetadata(String metadata) {
            this.metadata = metadata;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public void setContent(Map<String, JsonNode> content) {
            this.content = content;
        }
    }

    public static class MedTaggerRESTPluginResponseAnnotation {
        String concept_code;
        String status;
        String certainty;
        String experiencer;
        int match_start;
        int match_end;
        String matched_text;
        String sentence_containing_match;

        public String getConcept_code() {
            return concept_code;
        }

        public void setConcept_code(String concept_code) {
            this.concept_code = concept_code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCertainty() {
            return certainty;
        }

        public void setCertainty(String certainty) {
            this.certainty = certainty;
        }

        public String getExperiencer() {
            return experiencer;
        }

        public void setExperiencer(String experiencer) {
            this.experiencer = experiencer;
        }

        public int getMatch_start() {
            return match_start;
        }

        public void setMatch_start(int match_start) {
            this.match_start = match_start;
        }

        public int getMatch_end() {
            return match_end;
        }

        public void setMatch_end(int match_end) {
            this.match_end = match_end;
        }

        public String getMatched_text() {
            return matched_text;
        }

        public void setMatched_text(String matched_text) {
            this.matched_text = matched_text;
        }

        public String getSentence_containing_match() {
            return sentence_containing_match;
        }

        public void setSentence_containing_match(String sentence_containing_match) {
            this.sentence_containing_match = sentence_containing_match;
        }
    }
}
