package org.ohnlp.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.util.EntityUtils;

public class BioPortalAPI {

    public static final String BASE_URL_BIO_PORTAL = "http://data.bioontology.org";

    /**
     * Download the ontology data CSV file from BioPortal and slice
     * @param acronym the ontology name
     * @param apikey the apikey for BioPortal
     * @return string contains CSV data
     * @throws Exception
     */
    public static String downloadAndExtractOntology(String acronym, String apikey) throws Exception {
        String ontologyURL = BASE_URL_BIO_PORTAL + "/ontologies/" + acronym + "/download?download_format=csv&apikey="
                + apikey;
        URL url = new URL(ontologyURL);

        /*
         * Get the ontology data gzipped csv file from BioPortal
         */
        CloseableHttpClient httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();

        File dstFile = File.createTempFile(acronym + "-", ".csv.gz");
        System.out.println("* created temp file " + dstFile.getAbsolutePath());
        File downloadedFile = null;
        try {
            HttpGet request = new HttpGet(url.toURI()); // we're using GET but it could be via POST as well
            downloadedFile = httpclient.execute(request, new FileDownloadResponseHandler(dstFile));

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            IOUtils.closeQuietly(httpclient);
        }

        // downloaded, unzip the downloaded file
        String unzippedCSVFilePath = GZFileHelper.unzip(downloadedFile.getAbsolutePath());

        // create a reader for the unzipped CSV file
        Reader reader = Files.newBufferedReader(Paths.get(unzippedCSVFilePath));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withIgnoreHeaderCase()
                    .withTrim());

        // create a writer for the sliced CSV file as string for output
        StringWriter writer = new StringWriter();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("Class ID", "Preferred Label", "Synonyms", "Definitions", "Parents"));

        // slice the original CSV file
        for (CSVRecord csvRecord : csvParser) {
            // Accessing values by Header names
            String class_id = csvRecord.get("Class ID");
            String preferred_label = csvRecord.get("Preferred Label");
            String synonyms = csvRecord.get("Synonyms");
            String definitions = csvRecord.get("Definitions");
            String parents = csvRecord.get("Parents");

            csvPrinter.printRecord(class_id, preferred_label, synonyms, definitions, parents);
            csvPrinter.flush();    
        }
        csvPrinter.close();
        csvParser.close();

        return writer.toString();
    }

    /**
     * Get the Ontology Root node(s) of a specific ontology acronym
     * @param acronym the ontology name
     * @param apikey the apikey for BioPortal
     * @return the string contains JSON data
     * @throws Exception
     */
    public static String getOntologyRoot(String acronym, String apikey) throws Exception {
        // create the URL for the ontology
        String ontologyURL = BASE_URL_BIO_PORTAL + "/ontologies/" + acronym + "/classes/roots?apikey=" + apikey;

        // get content
        HttpGet request = new HttpGet(ontologyURL);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        // return it as a String
        String result = EntityUtils.toString(entity);
        return result;
    }

    /**
     * Get the children classes information from BioPortal
     * @param acronym the ontology name
     * @param classid the class id
     * @param apikey the apikey for BioPortal
     * @return the string contains JSON data
     * @throws Exception
     */
    public static String getOntologyChildren(String acronym, String classid, String apikey) throws Exception {
        // create the URL for the ontology
        String childrenURL = BASE_URL_BIO_PORTAL + "/ontologies/" + acronym + "/classes/" + classid
                + "/children?apikey=" + apikey;

        // get content
        HttpGet request = new HttpGet(childrenURL);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        // return it as a String
        String result = EntityUtils.toString(entity);
        return result;
    }

    /**
     * For downloading file
     */
    static class FileDownloadResponseHandler implements ResponseHandler<File> {

        private final File target;

        public FileDownloadResponseHandler(File target) {
            this.target = target;
        }

        @Override
        public File handleResponse(HttpResponse response) throws IOException {
			InputStream source = response.getEntity().getContent();
			FileUtils.copyInputStreamToFile(source, this.target);
			return this.target;
		}
		
	}
}