package ua.nure.st.kpp.example.demo.dao;

import java.util.*;
import java.sql.*;

import ua.nure.st.kpp.example.demo.entity.Music;

public class MySQLDAO implements IDAO {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/MusicDB?useSSL=false&serverTimezone=Europe/Kiev";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "VC@!YI#Tukryfgukh";

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}

	private static String SQL_ADD_MUSIC = "INSERT INTO Music (title, composer, duration, m_type) VALUES (?, ?, ?, ?)";
	private static String SQL_GET_ALL_MUSIC = "SELECT * FROM Music";
	private static String SQL_DELETE_MUSIC = "DELETE FROM Music WHERE title = ?";
	private static String SQL_GET_MUSIC_BY_COMPOSER = "SELECT * FROM Music WHERE composer = ?";
	String SQL_UPDATE_MUSIC = "UPDATE Music SET title = ?, composer = ?, duration = ?, m_type = ? WHERE id = ?";

	public MySQLDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Music> getAllMusics() {
		List<Music> records = new ArrayList<>();
		try (Connection connection = getConnection();
			 Statement statement = connection.createStatement();
			 ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_MUSIC)) {

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String composer = resultSet.getString("composer");
				int duration = resultSet.getInt("duration");
				String type = resultSet.getString("m_type");

				Music music = new Music(title, composer, duration, type);
				music.setId(id);
				records.add(music);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	@Override
	public void addMusic(Music music) {
		try (Connection con = getConnection();
			 PreparedStatement ps = con.prepareStatement(SQL_ADD_MUSIC, Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1, music.getTitle());
			ps.setString(2, music.getComposer());
			ps.setInt(3, music.getDuration());
			ps.setString(4, music.getType());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						long musicId = generatedKeys.getLong(1);
						System.out.println("New music entry added with ID: " + musicId);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMusic(String title) {
		try (Connection con = getConnection();
			 PreparedStatement ps = con.prepareStatement(SQL_DELETE_MUSIC)) {

			ps.setString(1, title);
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Music entry with title '" + title + "' has been deleted.");
			} else {
				System.out.println("No music entry found with title '" + title + "'.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Music> findMusicByComposer(String composer) {
		List<Music> musics = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement ps = connection.prepareStatement(SQL_GET_MUSIC_BY_COMPOSER)) {

			ps.setString(1, composer);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				String title = resultSet.getString("title");
				String comp = resultSet.getString("composer");
				int duration = resultSet.getInt("duration");
				String type = resultSet.getString("m_type");

				Music music = new Music(title, comp, duration, type);
				musics.add(music);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return musics;
	}

	@Override
	public void updateMusic(int id, Music updatedMusic) {
		try (Connection con = getConnection();
			 PreparedStatement ps = con.prepareStatement(SQL_UPDATE_MUSIC)) {

			ps.setString(1, updatedMusic.getTitle());
			ps.setString(2, updatedMusic.getComposer());
			ps.setInt(3, updatedMusic.getDuration());
			ps.setString(4, updatedMusic.getType());
			ps.setInt(5, id);

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Music entry with ID " + id + " has been updated.");
			} else {
				System.out.println("No music entry found with ID " + id + ".");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}