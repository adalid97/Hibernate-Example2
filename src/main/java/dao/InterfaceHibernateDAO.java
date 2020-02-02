package dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ejemplo.Departamento;
import ejemplo.Empleado;

public interface InterfaceHibernateDAO {
	// INSERT
	boolean insertEmpleado(String nombre, String apellidos, int idDto, SessionFactory factory);

	boolean insertDepartamento(String nombre, SessionFactory factory);

	// SELECT
	List<Empleado> listEmpleado(SessionFactory factory);

	List<Empleado> listEmpleadoPorID(SessionFactory factory);

	List<Empleado> listEmpleadosDeUnDepartamento(int idDep, SessionFactory factory);

	List<Departamento> listDepartamento(SessionFactory factory);

	// UPDATE
	boolean actualizarEmpleado(int idEmp, String nombre, String apellidos, int idDto, SessionFactory factory);

	boolean actualizarDepartamento(int id, String nombre, SessionFactory factory);

	// DELETE
	boolean eliminarEmpleado(int id, SessionFactory factory);

	boolean eliminarDepartamento(int id, SessionFactory factory);

}
