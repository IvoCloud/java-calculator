package app;

import java.util.Map;

public class RequestFormatter {
    String method;
    String url;
    Map<String, String>  params;
    public RequestFormatter(String reqString) {
        int delimiter1 = reqString.indexOf(' ');
        int delimiter2 = reqString.indexOf(' ', delimiter1+1);
        method = reqString.substring(0,delimiter1).trim();
        url = reqString.substring(delimiter1,delimiter2).trim();

    }
}
