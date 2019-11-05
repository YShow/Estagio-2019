package negocio;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import acessoBD.MariaDB.AcessoBD;
import acessoBD.MariaDB.HibernateUtil;
import objeto.Caixa;

public final class NegCaixa {
	private final AcessoBD conexao = new AcessoBD();
	private static final Logger logger = Logger.getLogger(NegCaixa.class.getName());

	public final boolean inserir(final Caixa caixa) {
		final var comeco = Instant.now();
		try (final var session = HibernateUtil.getSessionFactory().openSession()) {
			final var transaction = session.beginTransaction();
			session.save(caixa);

			transaction.commit();

			return session.getTransaction().getStatus() == TransactionStatus.COMMITTED;
		} finally {
			logger.log(Level.INFO,
					() -> "Inserir caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}

	}

	public final List<Caixa> consultar(final LocalDate data) {
		final var comeco = Instant.now();

		try (final var session = HibernateUtil.getSessionFactory().openSession()) {
			final var builder = session.getCriteriaBuilder();
			final var criteria = builder.createQuery(Caixa.class);
			final var caixa = criteria.from(Caixa.class);
			criteria.select(caixa);
			criteria.where(builder.equal(caixa.get("data"), data));

			return session.createQuery(criteria).getResultList();
		} finally {
			logger.log(Level.INFO,
					() -> "Consultar caixa demorou: " + Duration.between(comeco, Instant.now()).toMillis() + " ms");
		}
	}

}
