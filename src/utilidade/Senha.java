package utilidade;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.crypto.generators.SCrypt;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Base64;

public class Senha {
    public static final String geraSalt() {
	try {
	   
	    final byte[] salt = new byte[16];
	    SecureRandom secureRandom = null;
	    try {
		secureRandom = SecureRandom.getInstanceStrong();

		secureRandom.nextBytes(salt);

		

	    } catch (NoSuchAlgorithmException e) {
		Alerta.alertaErro(e.getMessage());
	    }
	    return new String(Base64.encode(salt));
	} finally {
	    System.gc();
	}
    }

    public static final String criaSenha(final String senha, final String salt) {
	final var senhachar = senha.toCharArray();
	final var saltbyte = salt.getBytes();
	try {
	    final var scry = SCrypt.generate(Strings.toByteArray(senhachar), saltbyte, 128, 128, 128, 512);
	    Arrays.fill(senhachar, Character.MIN_VALUE);
	    
	    
	    return new String(Base64.encode(scry));
	} finally {
	    System.gc();
	}

    }

    public static final boolean senhaCorreta(final String senha, final String senhaHash, final String salt) {
	final var senhahashbyte = senhaHash.toCharArray();
	
	try {
	
	return MessageDigest.isEqual(Strings.toByteArray(senhahashbyte), criaSenha(senha, salt).getBytes());
	}finally {
	    Arrays.fill(senhahashbyte, Character.MIN_VALUE);	  
	    System.gc();
	}
    }
}
