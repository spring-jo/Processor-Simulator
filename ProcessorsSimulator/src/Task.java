public interface Task {
    public void setRequestedTime(int requestedTime);
    public int getRequestedTime();
    public int getCreationTime();
    public void setCompletionTime(int completionTime);
    public int getCompletionTime();
    public void setState(String state);
    public String getState();
    public String getPriority();


}
