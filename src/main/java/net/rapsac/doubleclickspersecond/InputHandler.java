package net.rapsac.doubleclickspersecond;

import java.util.Queue;

import com.google.common.collect.Lists;

public class InputHandler {

    private static final Queue<Long> clicks = Lists.newLinkedList();
	public static boolean lastWasDouble = false;

	public static void Clicking(){
		DoubleClicksPerSecondClient.LOGGER.info(Long.toString(System.currentTimeMillis()));
		SetDoubleClick(System.currentTimeMillis()+1000L);
		clicks.add(System.currentTimeMillis()+1000L);
	}

	public static void SetDoubleClick(long newTime){
		if(!clicks.isEmpty() && newTime > clicks.peek()){
			if(!lastWasDouble && (newTime - clicks.peek()) < 45L){
				lastWasDouble = true;
			}else{
				lastWasDouble = false;
			}
		}
	}
	public static int Cps(){
		while(!clicks.isEmpty() && clicks.peek() < System.currentTimeMillis()){
			clicks.remove();
		}
		DoubleClicksPerSecondClient.LOGGER.info("----------------");
		DoubleClicksPerSecondClient.LOGGER.info(clicks.toString());
		DoubleClicksPerSecondClient.LOGGER.info("----------------");
		return clicks.size();
		
	}
	public static boolean WasLastDouble(){
		return lastWasDouble;
	}
    
}
