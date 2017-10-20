package com.mhj;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.mhj.util.FileUtil;

public class Som {
	private Clip clip;
	private String wav = "beep-02.wav";
	private AudioInputStream stream;
	private File file;

	public Som() {
		try {
			carregaFile();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public void carregaFile()
			throws UnsupportedAudioFileException, IOException, LineUnavailableException, URISyntaxException {
		file = FileUtil.carregaFile(getClass(), wav, new File(wav));
	}

	public void tocaSom(int qtde) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		stream = AudioSystem.getAudioInputStream(file);
		clip = AudioSystem.getClip();
		clip.open(stream);
		clip.loop(qtde);
	}

}
