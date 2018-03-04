import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Hemant Singh Bisht
 */
class Task implements Runnable
{
	private int count;
	
	Task(){
	}
	
	Task(int count)
	{
		this.count = count;
	}
	
	public void run() {
		
		for(int i=1;i<=count;i++)
		{
			System.out.println(i);
		}
	}
}

public class ExecutorServiceBasicProgram
{
	public static void main(String[] args) throws InterruptedException {
		
		/**newFixedThreadPool(3) : Creates a Pool for 3 Threads which will execute the task parallely and achieve 
		 * asynchronous behavior, if multiple Task assigned to ExecutorService then it will wait for one of the threads to 
		 * finish and execute it.**/
		
		ExecutorService executor = Executors.newFixedThreadPool(3);  
		
		/** newCachedThreadPool() : It takes no parameter and whenever the new task assigned to executor first it will check
		 * whether any thread is free in threadPool or not, if any thread is free task will be allocated to that thread else
		 * create a new thread in threaPool and assigned the task to newly created thread. **/
		
		//ExecutorService executor = Executors.newCachedThreadPool();
		
		
		/** newSingleThreadExecutor() : It also does not take any parameter, Only one thread will be assigned to
		 * a task at a time, no matter how many tasks you assigned to executor, next task will be assigned to that thread only after
		 * completion of first task**/
		
		//ExecutorService executor = Executors.newSingleThreadExecutor();
		
		
		Task t1 = new Task(5); // Runnable Task1
		Task t2 = new Task(5); // Runnable Task2
		Task t3 = new Task(5); // Runnable Task3
		
		executor.execute(t1); // Assigned Task1 to executor and now it resides in the ThreadPool of 3 threads.
		executor.execute(t2); // Assigned Task2 to executor and now it resides in the ThreadPool of 3 threads.
		executor.execute(t3); // Assigned Task3 to executor and now it resides in the ThreadPool of 3 threads.
		
		executor.shutdown(); // Initiates shutdown for executor, after this no new task can be assigned to the executor.
		
		executor.awaitTermination(1, TimeUnit.SECONDS); // just like a join(), below line would not execute until all above task finish its execution.
        System.out.println("Finished all threads");
	}
}