
import java.awt.List;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    static final String DB_URL = "jdbc:mysql://localhost:3306/Music_Player";
    static final String DB_NAME = "Music_Player";
    static final String USER = "root";
    static final String PASS = "";
    private static Connection conn = null;

    Database() {
        Statement stmt = null;
        try {
            // Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create songs and playlists table
            Table_Creator();
            Create_Music_Path_Table();
            Create_Playlist_Table();

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }

    public void Table_Creator() {
        Statement stmt = null;
        try {
            // Create songs table
            String sql = "CREATE TABLE IF NOT EXISTS songs "
                    + "(SongName VARCHAR(255) NOT NULL, "
                    + " ArtistName VARCHAR(255) NOT NULL, "
                    + " Genre VARCHAR(255) NOT NULL, "
                    + " Duration VARCHAR(255) NOT NULL, "
                    + " Image LONGBLOB, "
                    + " FilePath VARCHAR(255) NOT NULL, "
                    + " PRIMARY KEY (SongName))";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully...");
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try
    }

    public static void Insert_Song(String songName, String artistName, String genre, String duration, byte[] image, String filepath) {
        PreparedStatement stmt = null;
        ByteArrayInputStream bis = null;

        try {
            // Check if song already exists
            String checkSql = "SELECT * FROM songs WHERE SongName = ? AND FilePath = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, songName);
            checkStmt.setString(2, filepath);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Song already exists
                System.out.println("Song already exists in database...");
            } else {
                // Song does not exist, insert it
                String insertSql = "INSERT INTO songs (SongName, ArtistName, Genre, Duration, Image, FilePath) VALUES (?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, songName);
                stmt.setString(2, artistName);
                stmt.setString(3, genre);
                stmt.setString(4, duration);
                if (image != null) {
                    bis = new ByteArrayInputStream(image);
                    stmt.setBinaryStream(5, bis, image.length);
                } else {
                    stmt.setNull(5, Types.BLOB);
                }
                stmt.setString(6, filepath);

                // Execute insert statement
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Song added successfully...");
                } else {
                    System.out.println("Failed to add song...");
                }
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try
    }

    public static String Fetch_Path_From_Song(String songName) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String filepath = null;

        try {
            // Prepare select statement
            String sql = "SELECT FilePath FROM songs WHERE SongName = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, songName);

            // Execute select statement
            rs = stmt.executeQuery();
            if (rs.next()) {
                filepath = rs.getString("FilePath");
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try

        return filepath;
    }

    public static void Create_Music_Path_Table() {
        Statement stmt = null;

        try {
            // Check if song already exists
            String sql = "CREATE TABLE IF NOT EXISTS MUSIC_PATH(Path Varchar(255));";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        }
    }

    public static void Add_MUSIC_Path(String path) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            String old_path = Fetch_Music_Path();
            if (old_path != null) {
                // A path exists, update it
                Update_Music_Path(path);
            } else {
                // No path exists, insert it
                String insertSql = "INSERT INTO MUSIC_PATH (Path) VALUES (?)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, path);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Path added successfully...");
                } else {
                    System.out.println("Failed to add Path...");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                // ignore
            }
        }
    }

    public static String Fetch_Music_Path() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String filepath = null;

        try {
            // Prepare select statement
            String sql = "SELECT Path FROM MUSIC_PATH";
            stmt = conn.prepareStatement(sql);

            // Execute select statement
            rs = stmt.executeQuery();
            if (rs.next()) {
                filepath = rs.getString("Path");
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                // ignore
            }
        }

        return filepath;
    }

    public static void Update_Music_Path(String newPath) {
        PreparedStatement stmt = null;

        try {
            // Prepare update statement
            String sql = "UPDATE MUSIC_PATH SET Path = ? LIMIT 1";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPath);

            // Execute update statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Path updated successfully...");
            } else {
                System.out.println("Failed to update path...");
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                // ignore
            }
        }
    }

    public void Create_Playlist_Table() {
        Statement stmt = null;
        try {
            // Create songs table
            String sql = "CREATE TABLE IF NOT EXISTS PlayLists "
                    + "(playlist_name VARCHAR(255) NOT NULL, "
                    + " song_name VARCHAR(255) NULL, "
                    + " FOREIGN KEY (song_name) REFERENCES songs(SongName), "
                    + " PRIMARY KEY (playlist_name,song_name));";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully...");
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try
    }

    public static void Add_Song_in_Playlist(String song_name, String playlist_name) {
        PreparedStatement stmt = null;

        try {
            // Check if song already exists in playlist
            String checkSql = "SELECT * FROM playlists WHERE playlist_name = ? AND song_name = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, playlist_name);
            checkStmt.setString(2, song_name);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Song already exists
                System.out.println("Song already exists in this playlist...");
            } else {
                // Song does not exist, insert it
                String insertSql = "INSERT INTO playlists (playlist_name, song_name) VALUES (?, ?)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, playlist_name);
                stmt.setString(2, song_name);

                // Execute insert statement
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Song added successfully in " + playlist_name + " playlist...");
                } else {
                    System.out.println("Failed to add song in " + playlist_name + " playlist...");
                }
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try
    }

    public static void Delete_Song_in_Playlist(String song_name, String playlist_name) {
        PreparedStatement stmt = null;

        try {
            // Check if song already exists in playlist
            String checkSql = "DELETE FROM playlists WHERE playlist_name = ? AND song_name = ?";
            stmt = conn.prepareStatement(checkSql);
            stmt.setString(1, playlist_name);
            stmt.setString(2, song_name);
            // Execute update statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                System.out.println("Song deleted successfully from " + playlist_name + " playlist...");
            } else {
                System.out.println("Failed to delete song from " + playlist_name + " playlist...");
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try
    }

    public static void Create_Playlist(String playlist_name,String song_name) {
        PreparedStatement stmt = null;

        try {
            // Check if playlist already exists
            String checkSql = "SELECT * FROM playlists WHERE playlist_name = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, playlist_name);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Song already exists
                System.out.println("Playlist already exists...");
            } else {
                // Song does not exist, insert it
                String insertSql = "INSERT INTO playlists (playlist_name,song_name) VALUES (?,?)";
                stmt = conn.prepareStatement(insertSql);
                stmt.setString(1, playlist_name);
                stmt.setString(2, song_name);
                
                // Execute insert statement
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected == 1) {
                    System.out.println("Playlist created successfully...");
                } else {
                    System.out.println("Failed to create playlist...");
                }
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try
    }

    public static void Delete_Playlist(String playlist_name) {
        PreparedStatement stmt = null;

        try {
            // Check if song already exists in playlist
            String checkSql = "DELETE FROM playlists WHERE playlist_name = ?";
            stmt = conn.prepareStatement(checkSql);
            stmt.setString(1, playlist_name);
            // Execute update statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected >= 1) {
                System.out.println("Playlist deleted successfully...");
            } else {
                System.out.println("Failed to delete playlist...");
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            } // nothing we can do
        } // end try
    }

    // Retrieves all playlist names from the PlayLists table
    public static ArrayList<String> getAllPlaylists() {
        ArrayList<String> playlistNames = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Prepare select statement
            String sql = "SELECT DISTINCT playlist_name FROM PlayLists";
            stmt = conn.prepareStatement(sql);

            // Execute select statement
            rs = stmt.executeQuery();
            while (rs.next()) {
                String playlistName = rs.getString("playlist_name");
                playlistNames.add(playlistName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlistNames;
    }

    public static int countSongsInPlaylist(String playlistName) {
        int totalSongs = 0;

        // Assuming you have a database connection
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Prepare the SQL statement to count songs in the playlist excluding null values
            String sql = "SELECT COUNT(*) AS total_songs FROM PlayLists WHERE playlist_name = ? AND song_name IS NOT NULL";
            statement = conn.prepareStatement(sql);
            statement.setString(1, playlistName);

            // Execute the SQL statement
            resultSet = statement.executeQuery();

            // Retrieve the result
            if (resultSet.next()) {
                totalSongs = resultSet.getInt("total_songs");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return totalSongs;
    }

    public static ArrayList<String> getFilePathsForPlaylist(String playlistName) {
        ArrayList<String> filePaths = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT FilePath FROM songs AS s INNER JOIN playlists AS p ON s.SongName = p.song_name WHERE playlist_name = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, playlistName);
            rs = stmt.executeQuery();

            while (rs.next()) {
                String filePath = rs.getString("FilePath");
                filePaths.add(filePath);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
        }
        return filePaths;
    }
    
    public static byte[] getFirstSongImage(String playlistName) {
        byte[] imageBytes = null;

        // Assuming you have already established a database connection
        try {
            // Create a prepared statement to retrieve the image from the database
            String query = "SELECT Image FROM songs INNER JOIN PlayLists ON songs.SongName = PlayLists.song_name WHERE playlist_name = ? LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, playlistName);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if there is a result
            if (resultSet.next()) {
                // Retrieve the image from the result set
                imageBytes = resultSet.getBytes("Image");
            }

            // Close the result set and statement
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageBytes;
    }
    
    public static void updatePlaylistName(String oldPlaylistName, String newPlaylistName) {
        PreparedStatement stmt = null;

        try {
            // Prepare update statement
            String sql = "UPDATE PlayLists SET playlist_name = ? WHERE playlist_name = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPlaylistName);
            stmt.setString(2, oldPlaylistName);

            // Execute update statement
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected >= 1) {
                System.out.println("Name updated successfully...");
            } else {
                System.out.println("Failed to update Name...");
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
                // ignore
            }
        }
    }
} // end Database
