package acessoBD.MariaDB;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import objeto.Caixa;
import objeto.Cidade;
import objeto.Cliente;
import objeto.Funcionario;

public class HibernateUtil {

	private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());

	private HibernateUtil() {
		throw new IllegalStateException("Classe Utilidade");
	}

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {

			try {
				final Logger log = Logger.getLogger("org.hibernate");
				log.setLevel(Level.SEVERE);
				final Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties

				final Properties settings = new Properties();

				settings.put(AvailableSettings.URL, "jdbc:mariadb://localhost:3306/cantagalo");

				settings.put(AvailableSettings.USER, "usuario");

				settings.put(AvailableSettings.PASS, "1234");

				settings.put(AvailableSettings.SHOW_SQL, "true");

				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(Caixa.class);
				configuration.addAnnotatedClass(Cidade.class);
				configuration.addAnnotatedClass(Cliente.class);
				configuration.addAnnotatedClass(Funcionario.class);

				final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()

						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			} catch (final HibernateException e) {
				logger.severe(() -> "Erro na conexão: " + e.getMessage());

			}

		}

		return sessionFactory;

	}
}
