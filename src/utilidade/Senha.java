package utilidade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;

public class Senha {
    public static final String geraSalt() {
	try {
	    String hashSalt = null;
	    final byte[] salt = new byte[16];
	    SecureRandom secureRandom = null;
	    try {
		secureRandom = SecureRandom.getInstanceStrong();

		secureRandom.nextBytes(salt);

		hashSalt = new String(Base64.encode(salt));

	    } catch (NoSuchAlgorithmException e) {
		Alerta.alertaErro(e.getMessage());
	    }
	    return hashSalt;
	} finally {
	    System.gc();
	}
    }

    public static final String criaSenha(final String senha, final String salt) {

	try {
	    final var scry = SCrypt.generate(Strings.toByteArray(senha), Base64.decode(salt), 128, 128, 128, 512);

	    return new String(Base64.encode(scry));
	} finally {
	    System.gc();
	}

    }

    public static final boolean senhaCorreta(final String senha, final String senhaHash, final String salt) {

	return MessageDigest.isEqual(senhaHash.getBytes(), criaSenha(senha, salt).getBytes());
    }
}
