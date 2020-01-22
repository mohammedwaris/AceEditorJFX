package com.silverbrain.ace.events;

import java.util.function.Consumer;
import java.util.ArrayList;

public class EventDispatcher {

	private EventStore eventStore;
	
	public EventDispatcher() {
		this.eventStore = new EventStore();
	}
	/*
	public void addEventListener(String eventName, Consumer<IEvent> eventHandler) {
		eventStore.add(eventName, eventHandler);
	}
	
	public void removeEventListener(String eventName, Consumer<IEvent> eventHandler) {
		eventStore.remove(eventName, eventHandler);
	}
	
	public void removeAllEventListeners(String eventName) {
		eventStore.removeAll(eventName);
	}*/
	
	public boolean dispatchEvent(AceEvent event) {
		String eventType = event.getType();
		for(Consumer<AceEvent> eventHandler : eventStore.getEventHandlersByType(eventType))
			eventHandler.accept(event);
		return true;
	}
	
	public void on(String eventType, Consumer<AceEvent> eventHandler) {
		eventStore.add(eventType, eventHandler);
	}
	
	class EventStore {

		private ArrayList<String> eventTypes = new ArrayList<String>();
		private ArrayList<Consumer<AceEvent>> eventHandlers = new ArrayList<Consumer<AceEvent>>();
	
		public EventStore() {
		}
	
		public void add(String eventType, Consumer<AceEvent> eventHandler) {
			eventTypes.add(eventType);
			eventHandlers.add(eventHandler);
		}
		
		public void remove(String eventType, Consumer<AceEvent> eventHandler) {
			
			int pos = -1;
			for(int i=0;i<eventHandlers.size();i++){
				if(eventHandlers.get(i) == eventHandler) {
					pos = i;
					break;
				}
			}
			
			if(pos >= 0) {
				eventTypes.remove(pos);
				eventHandlers.remove(pos);
			}
			
		}
		
		public void removeAll(String eventType) {
			
			
			
		}
	
		public ArrayList<Consumer<AceEvent>> getEventHandlersByType(String eventType) {
		
			ArrayList<Consumer<AceEvent>> eHandlers = new ArrayList<Consumer<AceEvent>>();
			for(int i=0;i<eventTypes.size();i++) {
				if(eventTypes.get(i).equalsIgnoreCase(eventType))
				eHandlers.add(eventHandlers.get(i));
			}
			return eHandlers;
		}

	}//end of internal EventStore class

}