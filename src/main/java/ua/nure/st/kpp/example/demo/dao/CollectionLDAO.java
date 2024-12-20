package ua.nure.st.kpp.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ua.nure.st.kpp.example.demo.entity.Music;

public class CollectionLDAO implements IDAO{

	private static List<Music> musics = new ArrayList<Music>();

	static
	{
		musics.add(new Music("test", "composer", 50, "Song"));
		musics.add(new Music("test2", "composer2", 120, "Instrumental"));
	}


	@Override
	public List<Music> getAllMusics() {
		return musics;
	}

	@Override
	public void addMusic(Music music) {

		musics.add(music);
	}

	@Override
	public void deleteMusic(String title) {
		musics = musics.stream()
				.filter(music -> !music.getTitle().equals(title))
				.collect(Collectors.toList());
	}

	@Override
	public List<Music> findMusicByComposer(String composer) {
		return musics.stream()
				.filter(music -> music.getComposer().equalsIgnoreCase(composer))
				.collect(Collectors.toList());
	}

	@Override
	public void updateMusic(int id, Music updatedMusic) {
		for (int i = 0; i < musics.size(); i++) {
			if (i == id) {
				musics.set(i, updatedMusic);
				break;
			}
		}
	}
}

