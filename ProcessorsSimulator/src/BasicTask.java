public class BasicTask implements Comparable<BasicTask> , Task{
    private final int creationTime;
    private int requestedTime;
    private int completionTime;
    private final String priority;
    private String state;

    public BasicTask(int creationTime, int requestedTime, String priority) {
        this.creationTime = creationTime;
        this.requestedTime = requestedTime;
        this.priority = priority;
        this.state = "Waiting";
    }

    public int getCreationTime() {
        return creationTime;
    }

    public int getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(int requestedTime) {
        this.requestedTime = requestedTime;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPriority() {
        return priority;
    }

    public  int getPriorityLevel(){
        int priorityLevel;
        if (this.getPriority().equals("high"))
            priorityLevel = 1;
        else priorityLevel = 0;

        return priorityLevel;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        return new BasicTask(getCreationTime(),getRequestedTime() , getPriority());
    }

    @Override
    public int compareTo(BasicTask task) {
        if (this.getPriorityLevel() > task.getPriorityLevel()) return +1;
        return 0;
    }
}
