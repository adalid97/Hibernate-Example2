package dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import dao.InterfaceHibernateDAO;
import ejemplo.Departamento;
import ejemplo.Empleado;
import ejemplo.Main;

public class HibernateDAO implements InterfaceHibernateDAO {
	// INSERTAR

	@Override
	public boolean insertEmpleado(String nombre, String apellidos, int idDto, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;

		Departamento d = new Departamento(idDto, null);

		Empleado e = new Empleado(1, nombre, apellidos, d);

		try {
			tx = session.beginTransaction();
			session.save(e);
			tx.commit();
			resultado = false;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public boolean insertDepartamento(String nombre, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;

		Departamento d = new Departamento(0, nombre);
		try {
			tx = session.beginTransaction();
			session.save(d);
			tx.commit();
			resultado = false;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	// SELECT
	@Override
	public List<Empleado> listEmpleado(SessionFactory factory) {

		Session sesn = factory.openSession();
		Transaction tx = null;
		List<Empleado> empleados = new ArrayList();

		try {
			tx = sesn.beginTransaction();
			empleados = sesn.createQuery("FROM Empleado").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		return empleados;
	}

	@Override
	public List<Empleado> listEmpleadoPorID(SessionFactory factory) {

		Session sesn = factory.openSession();
		Transaction tx = null;
		List<Empleado> empleados = new ArrayList();

		try {
			tx = sesn.beginTransaction();
			empleados = sesn.createQuery("FROM Empleado WHERE nombre").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		return empleados;
	}

	@Override
	public List<Empleado> listEmpleadosDeUnDepartamento(int idDep, SessionFactory factory) {

		Session sesn = factory.openSession();
		Transaction tx = null;
		List<Empleado> empleados = new ArrayList();

		InterfaceHibernateDAO a = new HibernateDAO();

		Main main = new Main();

		try {
			tx = sesn.beginTransaction();
			empleados = sesn.createQuery("FROM Empleado WHERE idDepartamento = " + idDep).list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		return empleados;
	}

	@Override
	public List<Departamento> listDepartamento(SessionFactory factory) {

		Session sesn = factory.openSession();
		Transaction tx = null;
		List<Departamento> departamentos = new ArrayList();

		try {
			tx = sesn.beginTransaction();
			departamentos = sesn.createQuery("FROM Departamento").list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			sesn.close();
		}
		return departamentos;
	}

	// UPDATE
	@Override
	public boolean actualizarEmpleado(int idEmp, String nombre, String apellidos, int idDto, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;

		Departamento d = new Departamento(idDto, null);

		Empleado u = new Empleado(idEmp, nombre, apellidos, d);
		try {
			tx = session.beginTransaction();
			session.update(u);
			tx.commit();
			resultado = true;
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public boolean actualizarDepartamento(int id, String nombre, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;

		Departamento d = new Departamento(id, nombre);

		try {
			tx = session.beginTransaction();
			session.update(d);
			tx.commit();
			resultado = true;
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	// DELETE
	@Override
	public boolean eliminarEmpleado(int id, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;

		Empleado e = new Empleado(id, null, null, null);

		try {
			tx = session.beginTransaction();
			session.delete(e);
			tx.commit();
			resultado = true;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return resultado;
	}

	@Override
	public boolean eliminarDepartamento(int id, SessionFactory factory) {
		boolean resultado = false;
		Session session = factory.openSession();
		Transaction tx = null;

		Departamento e = new Departamento(id, null);

		try {
			tx = session.beginTransaction();
			session.delete(e);
			tx.commit();
			resultado = true;
		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("No se puede borrar este departamento, ya que contiene empleado/s");
		} finally {
			session.close();
		}
		return resultado;
	}

}
