package com.saintsrobotics.util;

import java.util.List;

public class CircularQueue<T>{
	private CircularQueueHolder<T> last;
	private CircularQueueHolder<T> current;
	public T get(){
		if(current==null) return null;
		return current.object;
	}
	public void remove(){
		if(last == current){
			current = null;
			last = null;
		}else if (current.next == last){
			current = last;
			current.next = current;
			last = null;
		}else{
			last.next = current.next;
			current = last;
		}
	}
	public void add(T object){
		CircularQueueHolder<T> holder = new CircularQueueHolder<T>(object);
		if(current == null){
			current = holder;
			last = holder;
			current.next = current;
		}else{
			holder.next = current;
			last.next = holder;
		}
	}
	public T next(){
		if(current==null) return null;
		if(current.next == current){
			return current.object;
		}else{ 
			last = current;
			current = current.next;
			return current.object;
		}
	}
	private class CircularQueueHolder<T>{
		public T object; 
		public CircularQueueHolder<T> next;
		public CircularQueueHolder(T obj){
			object = obj;
		}
	}
}