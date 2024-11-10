package dao;

import java.util.*;

public interface OperacionesCRUD<T> {
	
	public long insertar(T elemento);
	
	public Collection<T> verTodos();

}