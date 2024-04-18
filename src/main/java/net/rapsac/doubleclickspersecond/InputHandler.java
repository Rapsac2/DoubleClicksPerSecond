package net.rapsac.doubleclickspersecond;

import java.util.Queue;

import com.google.common.collect.Lists;
import java.util.LinkedList;

public class InputHandler {

    //private static final Queue<Long> clicks = Lists.newLinkedList();
	private static final LinkedList<Long> clicks = new LinkedList<>();
	public static boolean WasDouble = false;
	public static boolean IsDouble = false;
	

	public static void Clicking(){
		//DoubleClicksPerSecondClient.LOGGER.info("is:"+Boolean.toString(IsDouble)+" was:"+ Boolean.toString(WasDouble));
		SetDoubleClick(System.currentTimeMillis()+1000L);
		clicks.add(System.currentTimeMillis()+1000L);
	}

	//public static void SetDoubleClick(long newTime){
	//	if(!clicks.isEmpty() && newTime > clicks.peek()){
	//		if(!lastWasDouble && (newTime - clicks.peek()) < 100L){ //45L
	//			lastWasDouble = true;
	//		}else{
	//			lastWasDouble = false;
	//		}
	//	}
	//}

	public static void SetDoubleClick(long newTime){
		if(!clicks.isEmpty()){
			//DoubleClicksPerSecondClient.LOGGER.info(Long.toString(newTime - clicks.getLast()));
		}
		if(IsDouble){
			WasDouble = true;
			IsDouble = false;
			//DoubleClicksPerSecondClient.LOGGER.info("if isDouble");
			return;
		}
		if(WasDouble){
			//DoubleClicksPerSecondClient.LOGGER.info("if wasDouble");
			WasDouble = false;
		}	
		if(!clicks.isEmpty() && (newTime - clicks.getLast()) < 55L){
			//DoubleClicksPerSecondClient.LOGGER.info("set isDouble");
			IsDouble = true;
		}
	}

	public static int Cps(){
		while(!clicks.isEmpty() && clicks.peekFirst() < System.currentTimeMillis()){
			clicks.remove();
		}
		//if(!clicks.isEmpty()){
		//	for (Long long1 : clicks) {
		//		if(long1 < System.currentTimeMillis()){
		//			clicks.remove(long1);
		//		}
		//	}
		//}
		
		return clicks.size();
		
	}
	public static boolean WasLastDouble(){
		if(WasDouble || IsDouble){
			return true;
		}else{
			return false;
		}
	}
    
}
