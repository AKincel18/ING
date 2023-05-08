package ing.contest.atmservice.model;

public enum RequestType {
    FAILURE_RESTART,
    PRIORITY,
    SIGNAL_LOW,
    STANDARD;

    public boolean isHighPriority(RequestType requestType) {
        return this.ordinal() > requestType.ordinal();
    }
}
