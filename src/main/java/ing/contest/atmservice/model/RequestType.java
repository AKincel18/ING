package ing.contest.atmservice.model;

//todo: consider adding field like 'hierarchy' to increase readability
public enum RequestType {
    FAILURE_RESTART,
    PRIORITY,
    SIGNAL_LOW,
    STANDARD;

    public boolean isHighPriority(RequestType requestType) {
        return this.ordinal() > requestType.ordinal();
    }
}
