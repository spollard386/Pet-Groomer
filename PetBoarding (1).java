import java.sql.*;
import java.math.BigDecimal;

public class PetBoarding {

    private static final String DB_URL = "jdbc:sqlite:test.db";

    public static void main(String[] args) {
        Pet pet = new Pet("dog", "Buddy", 3, true, false, 7, BigDecimal.ZERO);

        // Check in the pet
        pet.checkIn();

        // Create a pet entry in the database
        PetBoarding petBoarding = new PetBoarding();
        petBoarding.createPetInDatabase(pet);

        // Load the pet entry from the database
        Pet loadedPet = petBoarding.loadPetFromDatabase(1);

        // Update the pet entry
        loadedPet.updatePet("dog", "Buddy", 4, true, false, 7, BigDecimal.ZERO);
        petBoarding.updatePetInDatabase(loadedPet, 1);

        // Get updated pet details
        System.out.println(loadedPet.getPetDetails());

        // Check out the pet
        loadedPet.checkOut();

        // Delete the pet entry from the database
        petBoarding.deletePetFromDatabase(1);
    }

    public void createPetInDatabase(Pet pet) {
        String sql = "INSERT INTO pets (type, name, age, needsDogSpace, needsCatSpace, stayDuration, totalAmountDue) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pet.getType());
            pstmt.setString(2, pet.getName());
            pstmt.setInt(3, pet.getAge());
            pstmt.setBoolean(4, pet.needsDogSpace());
            pstmt.setBoolean(5, pet.needsCatSpace());
            pstmt.setInt(6, pet.getStayDuration());
            pstmt.setBigDecimal(7, pet.getTotalAmountDue());

            pstmt.executeUpdate();
            System.out.println("Pet entry created in the database.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Pet loadPetFromDatabase(int id) {
        String sql = "SELECT * FROM pets WHERE id = ?";
        Pet pet = null;

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                pet = new Pet(
                        rs.getString("type"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getBoolean("needsDogSpace"),
                        rs.getBoolean("needsCatSpace"),
                        rs.getInt("stayDuration"),
                        rs.getBigDecimal("totalAmountDue"));
                System.out.println("Pet entry loaded from the database.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return pet;
    }

    public void updatePetInDatabase(Pet pet, int id) {
        String sql = "UPDATE pets SET type = ?, name = ?, age = ?, needsDogSpace = ?, needsCatSpace = ?, stayDuration = ?, totalAmountDue = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pet.getType());
            pstmt.setString(2, pet.getName());
            pstmt.setInt(3, pet.getAge());
            pstmt.setBoolean(4, pet.needsDogSpace());
            pstmt.setBoolean(5, pet.needsCatSpace());
            pstmt.setInt(6, pet.getStayDuration());
            pstmt.setBigDecimal(7, pet.getTotalAmountDue());
            pstmt.setInt(8, id);

            pstmt.executeUpdate();
            System.out.println("Pet entry updated in the database.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletePetFromDatabase(int id) {
        String sql = "DELETE FROM pets WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Pet entry deleted from the database.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
