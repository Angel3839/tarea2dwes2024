package dao;

import java.util.*;

public interface OperacionesCRUD<T> {
	//CU3   CU4A    CU5A
	public long insertar(T elemento);
	//CU5B    CU5C
	public T buscarPorID(long id);
	//CU1   CU5C
	public Collection<T> verTodos();
	//CU4B
	public boolean modificar(T elemento);
	
	public boolean eliminar(T elemento);

}