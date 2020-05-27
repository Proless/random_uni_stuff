package solution_3;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.LinkedList;

import org.omg.PortableInterceptor.ACTIVE;

import main_3.AScheduler;
import main_3.ComputerSystem;
import main_3.Process;
import main_3.Process.pstate;

public class MLFQ_Scheduler extends AScheduler {

	protected ArrayList<LinkedList<Process>> queues = new ArrayList<LinkedList<Process>>();
	protected LinkedList<Process> prio0list = new LinkedList<Process>();
	protected LinkedList<Process> prio1list = new LinkedList<Process>();
	protected LinkedList<Process> prio2list = new LinkedList<Process>();
	protected LinkedList<Process> prio3list = new LinkedList<Process>();
	
	protected int[] quantums = {1, 4, 16, 128};
	
	
	public MLFQ_Scheduler() {
		super();
		queues.add(prio0list);
		queues.add(prio1list);
		queues.add(prio2list);
		queues.add(prio3list);
	}

	@Override
	public void admit(Process p) {
		// set priority
		p.setPrio(0);
		
		// set state
		p.setState(pstate.READY);
		

		prio0list.add(p);
	}

	@Override
	public void schedule(ComputerSystem c) {
		
		Process old = c.getCurrentP();
		if (old != null) {
			if (old.getState() == pstate.BLOCKED) {
				queues.get(old.getPrio()).add(old);
				old.setState(pstate.READY);
			} else if (old.getState() == pstate.READY) {
				// Sicherstellen dass keine Zuweisung zu Prioliste höher als 3 stattfindet
				int prio = Math.min(3, old.getPrio() + 1);
				queues.get(prio).add(old);
				old.setPrio(prio);
			} else if (old.getState() == pstate.TERMINATED) {
				System.out.println("Process terminated");
			}
		}
		
				
		Process p = null;
		int i = 0;

		for (i = 0; i <= 3; i++) {
			LinkedList<Process> relevantQueue = queues.get(i);
			if (!relevantQueue.isEmpty()) {
				p= relevantQueue.pop();
				c.setCurrentP(p);
				c.scheduleClockInt((quantums[i]));
				return;
			}			
		}
		c.setCurrentP(null);
		
	}

}
