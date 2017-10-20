package com.mhj;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Cronometro extends JFrame {

	private static final long serialVersionUID = 3545053785228009472L;

	// GUI Components
	private JPanel panel;
	private JLabel timeLabel;

	private JPanel buttonPanel;
	private JButton startButton;
	private JButton resetButton;
	private JButton stopButton;

	// Properties of Program.
	private byte centiseconds = 0;
	private byte seconds = 10;
	private short minutes = 0;

	private DecimalFormat timeFormatter;

	private Timer timer;

	private Som som;

	public Cronometro() {

		som = new Som();

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Consolas", Font.PLAIN, 13));
		timeLabel.setHorizontalAlignment(JLabel.CENTER);
		panel.add(timeLabel);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				timer.start();

			}
		});
		buttonPanel.add(startButton);

		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				timer.stop();

				centiseconds = 0;
				seconds = 30;
				minutes = 0;

				timeLabel.setText(timeFormatter.format(minutes) + ":" + timeFormatter.format(seconds) + "."
						+ timeFormatter.format(centiseconds));
			}
		});

		buttonPanel.add(resetButton);

		stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});

		buttonPanel.add(stopButton);

		panel.add(buttonPanel, BorderLayout.SOUTH);

		timeFormatter = new DecimalFormat("00");

		timer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (centiseconds > 0) {
					centiseconds--;
				} else {
					if (seconds == 0 && minutes == 0) {
						timer.stop();
						tocarSomContinuo();
					} else if (seconds > 0) {
						tocarSom();
						seconds--;
						centiseconds = 99;
					} else if (minutes > 0) {
						minutes--;
						seconds = 59;
						centiseconds = 99;
					}
				}
				timeLabel.setText(timeFormatter.format(minutes) + ":" + timeFormatter.format(seconds) + "."
						+ timeFormatter.format(centiseconds));
			}
		});

		timeLabel.setText(timeFormatter.format(minutes) + ":" + timeFormatter.format(seconds) + "."
				+ timeFormatter.format(centiseconds));

		add(panel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("StopwatchGUI.java");

		pack();
		setVisible(true);
	}

	private void tocarSom() {
		if (seconds % 5 == 0) {
			System.out.println("teste: " + seconds);
			// System.out.println("data: " + new Date());
			try {
				som.tocaSom(0);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void tocarSomContinuo() {
			System.out.println("teste: " + seconds);
			// System.out.println("data: " + new Date());
			try {
				som.tocaSom(5);
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				new Cronometro();
			}
		});
	}
}