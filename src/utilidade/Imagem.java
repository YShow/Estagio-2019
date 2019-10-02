package utilidade;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class Imagem {

	public enum IMAGEM {
		FUNDO("apresentacao/cantagalo.png");

		private final String imagem;

		public String getImagem() {
			return imagem;
		}

		private IMAGEM(final String pegaImagem) {
			this.imagem = pegaImagem;
		}

	}

	public static Background colocaImagemFundo(final IMAGEM imagem) {

		final var image = new Image(imagem.getImagem(), Double.MAX_VALUE, Double.MAX_VALUE, false, true);

		final var backgroundSize = new BackgroundSize(100, 100, true, true, true, true);

		final var backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,
				BackgroundPosition.CENTER, backgroundSize);

		return new Background(backgroundImage);
	}
}