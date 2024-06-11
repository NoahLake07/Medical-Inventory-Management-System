package mims.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.StringReader;

public class APICommunicator {

    private static final String DRUG_API_URL = "https://api.fda.gov/drug/ndc.json?search=product_ndc:";
    private static final String DRUG_NAME_SEARCH_API_URL = "https://api.fda.gov/drug/label.json?search=openfda.generic_name:";
    private static final String DRUG_LABEL_API_URL = "https://api.fda.gov/drug/label.json?search=product_ndc:";
    private static final String DRUG_RECALL_API_URL = "https://api.fda.gov/drug/enforcement.json?search=product_ndc:";
    private static final String DEVICE_API_URL_JSON = "https://accessgudid.nlm.nih.gov/api/v2/devices/lookup.json?di=";
    private static final String DEVICE_API_URL_XML = "https://accessgudid.nlm.nih.gov/api/v2/devices/lookup.xml?udi=";
    private static final String DEVICE_RECALL_API_URL = "https://api.fda.gov/device/enforcement.json?search=device_id:";
    private static final String SEARCH_API_URL = "https://api.fda.gov/drug/label.json?search=";

    // Method to lookup drug information by NDC
    public String lookupDrugByNDC(String ndc) {
        String apiUrl = DRUG_API_URL + ndc;
        return sendGetRequest(apiUrl);
    }

    // Method to search drugs by generic name
    public String searchDrugByName(String name) {
        String apiUrl = DRUG_NAME_SEARCH_API_URL + name;
        return sendGetRequest(apiUrl);
    }

    // Method to retrieve drug label information by NDC
    public String getDrugLabelByNDC(String ndc) {
        String apiUrl = DRUG_LABEL_API_URL + ndc;
        return sendGetRequest(apiUrl);
    }

    // Method to check drug recalls by NDC
    public String checkDrugRecallByNDC(String ndc) {
        String apiUrl = DRUG_RECALL_API_URL + ndc;
        return sendGetRequest(apiUrl);
    }

    // Method to lookup device information by DI
    public String lookupDeviceByDI(String di) {
        String apiUrl = DEVICE_API_URL_JSON + di;
        return sendGetRequest(apiUrl);
    }

    // Method to decode UDI barcode and get device information
    public String decodeUDI(String udi) {
        String apiUrl = DEVICE_API_URL_XML + udi;
        return sendGetRequest(apiUrl);
    }

    // Method to check device recalls by device ID
    public String checkDeviceRecallByID(String deviceId) {
        String apiUrl = DEVICE_RECALL_API_URL + deviceId;
        return sendGetRequest(apiUrl);
    }

    // Method to search by query and return an array of SearchResult
    public SearchResult[] searchByQuery(String query) {
        String apiUrl = SEARCH_API_URL + query;
        String response = sendGetRequest(apiUrl);
        return parseSearchResults(response);
    }

    // Helper method to parse search results and return an array of SearchResult
    private SearchResult[] parseSearchResults(String jsonResponse) {
        List<SearchResult> results = new ArrayList<>();
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(jsonResponse);
            JsonNode resultsNode = rootNode.path("results");

            for (JsonNode node : resultsNode) {
                String name = node.path("openfda").path("generic_name").asText();
                String id = node.path("id").asText();
                results.add(new SearchResult(name, id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results.toArray(new SearchResult[0]);
    }

    // Helper method to send GET request to the API
    private String sendGetRequest(String apiUrl) {
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result.toString();
    }

    // Method to parse JSON response for drug information
    public JsonNode parseDrugResponse(String jsonResponse) {
        return parseJsonResponse(jsonResponse);
    }

    // Method to parse JSON response for device information
    public JsonNode parseDeviceResponse(String jsonResponse) {
        return parseJsonResponse(jsonResponse);
    }

    // Helper method to parse JSON response
    private JsonNode parseJsonResponse(String jsonResponse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readTree(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to parse XML response for device information
    public Document parseXmlResponse(String xmlResponse) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlResponse));
            return builder.parse(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        APICommunicator apiCommunicator = new APICommunicator();

        // Example usage for drug lookup
        String drugResponse = apiCommunicator.lookupDrugByNDC("66715-6434");
        JsonNode drugInfo = apiCommunicator.parseDrugResponse(drugResponse);
        System.out.println("Drug Information: " + drugInfo);

        // Example usage for searching drug by name
        String drugSearchResponse = apiCommunicator.searchDrugByName("ibuprofen");
        JsonNode drugSearchInfo = apiCommunicator.parseDrugResponse(drugSearchResponse);
        System.out.println("Drug Search Information: " + drugSearchInfo);

        // Example usage for device lookup by DI
        String deviceResponse = apiCommunicator.lookupDeviceByDI("00810020083842");
        JsonNode deviceInfo = apiCommunicator.parseDeviceResponse(deviceResponse);
        System.out.println("Device Information (JSON): " + deviceInfo);

        // Example usage for decoding UDI barcode
        String udiResponse = apiCommunicator.decodeUDI("%3D%2F08717648200274%3D%2C000025%3DA99971312345600%3D%3E014032%3D%7D013032%26%2C1000000000000XYZ123");
        Document udiInfo = apiCommunicator.parseXmlResponse(udiResponse);
        System.out.println("Device Information (XML): " + udiInfo.getDocumentElement().getTextContent());

        // Example usage for search by query
        SearchResult[] searchResults = apiCommunicator.searchByQuery("ibuprofen");
        for (SearchResult result : searchResults) {
            System.out.println(result);
        }
    }
}
