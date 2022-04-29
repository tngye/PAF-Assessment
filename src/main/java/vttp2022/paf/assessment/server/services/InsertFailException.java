package vttp2022.paf.assessment.server.services;

public class InsertFailException extends Exception {
    
    private String reason;

    public InsertFailException(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
