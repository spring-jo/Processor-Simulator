public class Processor  {
    private boolean busy ;
    private int cyclesLeft;
    private BasicTask basicTask;

    public Processor(BasicTask basicTask, int cyclesLeft) {
        this.busy = false;
        this.cyclesLeft = cyclesLeft;
        this.basicTask = basicTask;
        if (basicTask != null)
            this.basicTask.setState("Executing");
    }

    public boolean isBusy() {
        if (getCyclesLeft() > 0)
            busy = true;
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getCyclesLeft() {
        return cyclesLeft;
    }

    public void setCyclesLeft(int cyclesLeft) {
        if (cyclesLeft < 0)
            this.cyclesLeft = 0;
        else
            this.cyclesLeft = cyclesLeft;
        this.basicTask.setRequestedTime(cyclesLeft);
        if (cyclesLeft <= 0)
            setBusy(false);
    }

    public BasicTask removeTask(){
        basicTask.setRequestedTime(getCyclesLeft());
        try  {
            BasicTask basicTask1 = (BasicTask)this.basicTask.clone();
            if (basicTask1.getRequestedTime() <=0)
                basicTask1.setState("Completed");
            else basicTask1.setState("Waiting");
            setBusy(false);
            setCyclesLeft(0);
            return basicTask1;
        }
        catch (Exception e) {
            return null;
        }
    }

    public BasicTask getBasicTask() {
        try {
            BasicTask basicTask1 = (BasicTask)this.basicTask.clone();
            if (basicTask1.getRequestedTime() <=0)
                basicTask1.setState("Completed");
            else basicTask1.setState("Waiting");
            return basicTask1;
        }catch (Exception e){
            return null;
        }
    }

}
