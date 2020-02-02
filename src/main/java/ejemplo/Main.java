package ejemplo;

import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dao.InterfaceHibernateDAO;
import dao.impl.HibernateDAO;

public class Main {

	private static SessionFactory factory;
	private static ServiceRegistry serviceRegistry;

	public static void main(String[] args) {

		Configuration config = new Configuration();
		config.configure();
		config.addAnnotatedClass(Empleado.class);

		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		factory = config.buildSessionFactory(serviceRegistry);

		InterfaceHibernateDAO a = new HibernateDAO();

		Scanner s = new Scanner(System.in);

		int opcion;
		do {
			System.out.println("\n\n\t========================");
			System.out.println("\t|||| MENÚ PRINCIPAL ||||");
			System.out.println("\t========================\n");

			System.out.println("EMPLEADOS.");
			System.out.println("\t1. Listar empleados");
			System.out.println("\t2. Insertar empleados.");
			System.out.println("\t3. Borrar empleados.");
			System.out.println("\t4. Actualizar empleados.");

			System.out.println("\nDEPARTAMENTOS.");
			System.out.println("\t5. Listar departamentos");
			System.out.println("\t6. Insertar departamentos.");
			System.out.println("\t7. Borrar departamentos.");
			System.out.println("\t8. Actualizar departamentos.");

			System.out.println("\n\t9.Mostrar empleados de un departamentos por idDepartamento: ");

			System.out.println("\n\t0.Salir del programa");

			System.out.print("\nEscribe el número de la opción (0-9): ");

			opcion = s.nextInt();

			switch (opcion) {
			case 0:
				System.out.println("FIN DEL PROGRAMA.");
				break;
			case 1:
				List<Empleado> empleados = a.listEmpleado(factory);
				System.out.println("Total empleados: " + empleados.size());
				for (Empleado e : empleados) {
					System.out.println("Id empleado: " + e.getIdEmpleado());
					System.out.println("\tNombre empleado: " + e.getNombre());
					System.out.println("\tApellidos empleado: " + e.getApellidos());
					System.out.println("\tID dto: " + e.getDepartamento().getIdDepartamento() + "\n");
				}
				break;
			case 2:
				Scanner s2 = new Scanner(System.in);
				System.out.print("Nombre Empleado: ");
				String nombre = s2.nextLine();
				System.out.print("Apellidos Empleado: ");
				String apellidos = s2.nextLine();
				System.out.print("Id Departamento: ");
				int idDto = s2.nextInt();
				a.insertEmpleado(nombre, apellidos, idDto, factory);
				break;
			case 3:
				Scanner s3 = new Scanner(System.in);
				System.out.print("ID Empleado a borrar: ");
				int id = Integer.parseInt(s3.next());
				a.eliminarEmpleado(id, factory);
				break;
			case 4:
				Scanner s4 = new Scanner(System.in);
				System.out.print("Id Empleado a cambiar: ");
				int idEmp = Integer.parseInt(s4.next());
				System.out.print("Nombre Empleado: ");
				String nombreE = s4.next();
				System.out.print("Apellidos Empleado: ");
				String apellidosE = s4.next();
				System.out.print("Id Departamento: ");
				int idDtoE = Integer.parseInt(s.next());
				a.actualizarEmpleado(idEmp, nombreE, apellidosE, idDtoE, factory);
				break;
			case 5:
				List<Departamento> departamentos = a.listDepartamento(factory);
				System.out.println("Total Departamentos: " + departamentos.size());
				for (Departamento d : departamentos) {
					System.out.println("Id dto: " + d.getIdDepartamento());
					System.out.println("\tNombre dto: " + d.getNombre() + "\n");
				}
				break;
			case 6:
				Scanner s5 = new Scanner(System.in);
				System.out.print("Nombre Departamento: ");
				String nombreD = s5.nextLine();
				a.insertDepartamento(nombreD, factory);
				break;
			case 7:
				Scanner s6 = new Scanner(System.in);
				System.out.print("ID Departamento a borrar: ");
				int idD = Integer.parseInt(s6.next());
				a.eliminarDepartamento(idD, factory);
				break;
			case 8:
				Scanner s7 = new Scanner(System.in);
				System.out.print("Id Departamento a cambiar: ");
				int idDto1 = Integer.parseInt(s7.next());
				System.out.print("Nombre Departamento: ");
				String nombreDto = s7.next();
				a.actualizarDepartamento(idDto1, nombreDto, factory);
				break;
			case 9:
				List<Departamento> departamentos1 = a.listDepartamento(factory);
				for (Departamento d : departamentos1) {
					System.out.println("Id dto: " + d.getIdDepartamento());
					System.out.println("\tNombre dto: " + d.getNombre() + "\n");
				}
				Scanner s8 = new Scanner(System.in);
				System.out.print("Id Departamento: ");
				int idDep = Integer.parseInt(s8.next());

				List<Empleado> em = a.listEmpleadosDeUnDepartamento(idDep, factory);
				String resultado = "";
				for (Empleado d : em) {
					resultado += "Nombre empleado: " + d.getNombre() + "\n";
				}
				if (resultado != null) {
					JOptionPane.showMessageDialog(null, resultado);
				}

				break;
			default:
				System.out.println("OPCIÓN NO VÁLIDA");
				break;
			}

		} while (opcion != 0);

	}

}
