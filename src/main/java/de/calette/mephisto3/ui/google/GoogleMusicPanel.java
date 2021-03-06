package de.calette.mephisto3.ui.google;

import callete.api.Callete;
import de.calette.mephisto3.ui.ControllablePanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Well, this panel is not used!
 * Since the music selection is delegated to subpanel, we only use
 * this panel to initialize Google music.
 */
public class GoogleMusicPanel extends ControllablePanel {
  private final static Logger LOG = LoggerFactory.getLogger(GoogleMusicPanel.class);

  public GoogleMusicPanel() {
  }

  //--------------- Helper ------------------------------------------------------

  /**
   * Google Music Login
   */
  public boolean loadGoogleMusic() {
    try {
      return Callete.getGoogleMusicService().authenticate();
    } catch (Exception e) {
      LOG.error("Error authenticating Google music: " + e.getMessage(), e);
    }
    return false;
  }
}
