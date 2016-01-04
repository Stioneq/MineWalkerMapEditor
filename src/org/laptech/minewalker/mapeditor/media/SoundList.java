package org.laptech.minewalker.mapeditor.media;

import java.io.InputStream;

/**
 * Collects different sounds
 * @author rlapin
 */
public class SoundList {
    public static final InputStream MAGNET_SOUND = SoundList.class.getClassLoader().getResourceAsStream("sounds/magnet.wav");
}
