import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void insertTaskIntoScheduler(Scanner scanner, NodeQueue<BasicTask> scheduler, int timer) throws NoSuchFieldError{
        int requestedTime;
        String priority;
        try {
            requestedTime = scanner.nextInt();
            priority = scanner.next();
        } catch (NoSuchFieldError a){
            throw new NoSuchFieldError();
        }
        scheduler.enqueue(new BasicTask (timer, requestedTime , priority), priority );
    }
    public static void main(String[] args) throws FileNotFoundException {
        NodeQueue<BasicTask> scheduler = new NodeQueue<>();
        int timer = 0;
        Scanner menu = new Scanner(System.in);
        String Filename;
        System.out.println("Please enter file path followed by file name followed by .txt: ");
        Filename = menu.nextLine();
        menu.close();
        menu = null;

        int processorsNum = 0;
        int taskNum = 0;

        try {

            File myObj = new File(Filename);
            menu = new Scanner(myObj);
            processorsNum = menu.nextInt();
            taskNum = menu.nextInt();
        }
        catch (FileNotFoundException e){
            System.out.println("Error: The file "+Filename+" does not exist! ");
            e.printStackTrace();
        }
        BasicTask[] finishedBasicTasks = new BasicTask[taskNum];
        int numOfFinishedTasks = 0;
        Processor[] processor = new Processor[processorsNum];
        for (int i = 0 ; i < processorsNum; i++){
            processor[i] = new Processor(null, 0);
        }
        int creationTimeLastLineReached = 0;
            int busyProcessors = 0;
            while (menu.hasNextLine() || scheduler.size() > 0 || busyProcessors > 0 ){
                timer++;
                if (creationTimeLastLineReached < timer && menu.hasNextInt())
                    creationTimeLastLineReached = menu.nextInt(); // to reserve the last creation time checked, so I would only read it once and wait till cycles reaches it
                while (creationTimeLastLineReached == timer){
                    insertTaskIntoScheduler(menu, scheduler, timer);
                    if (menu.hasNextLine()) menu.nextLine(); //to ignore the rest of the line if there's any rubbish values
                    if (menu.hasNext())
                        creationTimeLastLineReached =menu.nextInt();
                    else creationTimeLastLineReached = 0;
                }
                for (int i = 0 ; i < processor.length; i++) { //fill the processors if idle or replace with higher priority if so.
                    if (processor[i].isBusy() && scheduler.size() != 0)
                        if ((processor[i].getBasicTask().getPriority().equals("low")) && (scheduler.getFirstNodePriority() > 0)) {
                            BasicTask basicTask1 = processor[i].removeTask();
                            scheduler.enqueue(basicTask1, basicTask1.getPriority());
                            System.out.println("Tasks with low priority replaced with " +basicTask1.getRequestedTime()+ " cycles left at cycle " +timer);
                            busyProcessors--;
                        }
                    if (!(processor[i].isBusy()) && scheduler.size() > 0) {
                        BasicTask basicTask1 = scheduler.dequeue();
                        processor[i] = new Processor(basicTask1, basicTask1.getRequestedTime());
                        busyProcessors++;
                    }

                    if (processor[i].isBusy()) {
                        processor[i].setCyclesLeft(processor[i].getCyclesLeft() - 1);
                        if (processor[i].getCyclesLeft() < 1){
                            BasicTask basicTask1 = processor[i].removeTask();
                            basicTask1.setState("Completed");
                            basicTask1.setCompletionTime(timer);
                            finishedBasicTasks[numOfFinishedTasks++] = basicTask1;
                            busyProcessors--;
                        }

                    }
                }

            }
            System.out.println("----------------------------------------------");
            for (int i =0 ; i < finishedBasicTasks.length; i++){
                System.out.println("Task " + (i+1) + " with " + finishedBasicTasks[i].getPriority() +" priority ended at cycle "+finishedBasicTasks[i].getCompletionTime() + " " );
            }

        }
}