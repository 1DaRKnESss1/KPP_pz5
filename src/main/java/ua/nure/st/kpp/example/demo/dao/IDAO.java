package ua.nure.st.kpp.example.demo.dao;

import java.util.List;

import ua.nure.st.kpp.example.demo.entity.Music;

public interface IDAO {

	List<Music> getAllMusics();
	void addMusic(Music music);
	void deleteMusic(String title);
	List<Music> findMusicByComposer(String composer);
	void updateMusic(int id, Music updatedMusic);
}
