package moviescraper.doctord.controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import moviescraper.doctord.view.GUIMain;

public class OpenFileAction implements ActionListener {
	/**
	 * 
	 */
	private final GUIMain guiMain;

	/**
	 * @param guiMain
	 */
	public OpenFileAction(GUIMain guiMain) {
		this.guiMain = guiMain;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		for (int movieNumberInList = 0; movieNumberInList < this.guiMain.getCurrentlySelectedMovieFileList().size(); movieNumberInList++) {
			if (this.guiMain.getCurrentlySelectedMovieFileList() != null) {
				try {
					if (this.guiMain.getCurrentlySelectedMovieFileList().get(movieNumberInList).exists()) {
						Desktop.getDesktop().open(this.guiMain.getCurrentlySelectedMovieFileList().get(movieNumberInList));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
}