import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author Hemant Singh Bisht
 */
class Increement implements Runnable
{
	/**
	 * AtomicInteger : It is a savior from Data Inconsistency in multithreading environment,
	 * synchronization block can be used instead of that but again it is much more elegant, improves the
	 * readability and chance of errors are reduced. 
	 */
	private static AtomicInteger count = new AtomicInteger(0); 
	
	Increement(){
	}
	
	public void run() {
		
		for(int i=1;i<=10;i++)
		{
			System.out.print(count.getAndIncrement() + " ");
		}
	}
}

public class ConcurrentAtomicInteger
{
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executor = Executors.newFixedThreadPool(3);  
		
		Increement t1 = new Increement(); // Runnable Task1
		Increement t2 = new Increement(); // Runnable Task2
		
		executor.execute(t1); // Assigned Task1 to executor and now it resides in the ThreadPool of 3 threads.
		executor.execute(t2); // Assigned Task2 to executor and now it resides in the ThreadPool of 3 threads.
		
		executor.shutdown(); // Initiates shutdown for executor, after this no new task can be assigned to the executor.
	}
}