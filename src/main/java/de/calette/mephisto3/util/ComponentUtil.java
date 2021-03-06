package de.calette.mephisto3.util;

import callete.api.services.impl.music.google.AlbumCoverCache;
import callete.api.services.music.model.Album;
import callete.api.services.resources.ImageResource;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 */
public class ComponentUtil {
  private final static Logger LOG = LoggerFactory.getLogger(ComponentUtil.class);

  /**
   * Creates an image canvas with the given width and height.
   */
  public static Canvas createImageCanvas(String url, double width, double height) {
    ImageView img = new ImageView(new Image(url, width, height, false, true));
    final Canvas canvas = new Canvas(width, height);
    final GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.drawImage(img.getImage(), 0, 0);

    return canvas;
  }

  public static Label createLabel(String label, String cssClass, Pane parent) {
    Label l = new Label(label);
    l.getStyleClass().add("label-defaults");
    l.getStyleClass().add(cssClass);
    parent.getChildren().add(l);
    return l;
  }

  public static Label createCustomLabel(String label, String cssClass, Pane parent) {
    Label l = new Label(label);
    l.getStyleClass().add(cssClass);
    parent.getChildren().add(l);
    return l;
  }

  public static Text createText(String label, String cssClass, Pane parent, Object userData) {
    Text l = new Text(label);
    l.getStyleClass().add("text-defaults");
    l.getStyleClass().add(cssClass);
    parent.getChildren().add(l);
    l.setUserData(userData);
    return l;
  }

  public static Text createText(String label, String cssClass, Pane parent) {
    return createText(label, cssClass, parent, null);
  }

  public static ImageView loadAlbumCover(Album album, int width, int height) {
    String url = AlbumCoverCache.loadCover(album);
    return new ImageView(new Image(url, width, height, false, true));
  }

  public static Image toFXImage(ImageResource image) {
    try {
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      ImageIO.write(image.getImage(), image.getImageFormat(), os);
      InputStream is = new ByteArrayInputStream(os.toByteArray());
      return new Image(is, image.getImage().getWidth(), image.getImage().getHeight(), false, true);
    } catch (Exception e) {
      LOG.error("Error converting buffered image to FX image: " + e.getMessage(), e);
    }
    return null;
  }
}
