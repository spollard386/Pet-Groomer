import java.math.BigDecimal;

/**
 * Represents a Pet with properties such as type, name, age, and stay details.
 * Handles the check-in and check-out processes, including rate calculations.
 */
public class Pet {

    private static final BigDecimal DOG_RATE_UNDER_2 = new BigDecimal("25.00");
    private static final BigDecimal DOG_RATE_2_TO_5 = new BigDecimal("20.00");
    private static final BigDecimal DOG_RATE_OVER_5 = new BigDecimal("15.00");
    private static final BigDecimal CAT_RATE = new BigDecimal("18.00");

    private String type;
    private String name;
    private int age;
    private boolean needsDogSpace;
    private boolean needsCatSpace;
    private int stayDuration;
    private BigDecimal totalAmountDue;

    /**
     * Default constructor for Pet.
     */
    public Pet() {
        this("", "", 0, false, false, 0, BigDecimal.ZERO);
    }

    /**
     * Parameterized constructor for Pet.
     * 
     * @param type            the type of the pet (dog or cat)
     * @param name            the name of the pet
     * @param age             the age of the pet
     * @param needsDogSpace   whether the pet needs dog space
     * @param needsCatSpace   whether the pet needs cat space
     * @param stayDuration    the duration of the pet's stay
     * @param totalAmountDue  the total amount due for the pet's stay
     */
    public Pet(String type, String name, int age, boolean needsDogSpace, boolean needsCatSpace, int stayDuration, BigDecimal totalAmountDue) {
        setType(type);
        setName(name);
        setAge(age);
        setNeedsDogSpace(needsDogSpace);
        setNeedsCatSpace(needsCatSpace);
        setStayDuration(stayDuration);
        setTotalAmountDue(totalAmountDue);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty.");
        }
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        this.age = age;
    }

    public boolean needsDogSpace() {
        return needsDogSpace;
    }

    public void setNeedsDogSpace(boolean needsDogSpace) {
        this.needsDogSpace = needsDogSpace;
    }

    public boolean needsCatSpace() {
        return needsCatSpace;
    }

    public void setNeedsCatSpace(boolean needsCatSpace) {
        this.needsCatSpace = needsCatSpace;
    }

    public int getStayDuration() {
        return stayDuration;
    }

    public void setStayDuration(int stayDuration) {
        if (stayDuration < 0) {
            throw new IllegalArgumentException("Stay duration cannot be negative.");
        }
        this.stayDuration = stayDuration;
    }

    public BigDecimal getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(BigDecimal totalAmountDue) {
        if (totalAmountDue.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Total amount due cannot be negative.");
        }
        this.totalAmountDue = totalAmountDue;
    }

    /**
     * Checks in the pet and calculates the initial amount due based on the type and age.
     */
    public void checkIn() {
        BigDecimal dailyRate = calculateDailyRate(type, age);
        totalAmountDue = dailyRate.multiply(BigDecimal.valueOf(stayDuration));
        System.out.println(name + " has been checked in. Initial amount due: $" + totalAmountDue);
    }

    /**
     * Checks out the pet and resets the stay details.
     */
    public void checkOut() {
        System.out.println(name + " has been checked out. Total amount due: $" + totalAmountDue);
        resetStayDetails();
    }

    /**
     * Returns the details of the pet.
     * 
     * @return a string representation of the pet's details
     */
    public String getPetDetails() {
        return this.toString();
    }

    /**
     * Creates a new pet with the specified details.
     * 
     * @param type            the type of the pet
     * @param name            the name of the pet
     * @param age             the age of the pet
     * @param needsDogSpace   whether the pet needs dog space
     * @param needsCatSpace   whether the pet needs cat space
     * @param stayDuration    the duration of the pet's stay
     * @param totalAmountDue  the total amount due for the pet's stay
     */
    public void createPet(String type, String name, int age, boolean needsDogSpace, boolean needsCatSpace, int stayDuration, BigDecimal totalAmountDue) {
        setType(type);
        setName(name);
        setAge(age);
        setNeedsDogSpace(needsDogSpace);
        setNeedsCatSpace(needsCatSpace);
        setStayDuration(stayDuration);
        setTotalAmountDue(totalAmountDue);
        System.out.println("Pet created: " + this.toString());
    }

    /**
     * Updates the pet's details with the specified values.
     * 
     * @param type            the type of the pet
     * @param name            the name of the pet
     * @param age             the age of the pet
     * @param needsDogSpace   whether the pet needs dog space
     * @param needsCatSpace   whether the pet needs cat space
     * @param stayDuration    the duration of the pet's stay
     * @param totalAmountDue  the total amount due for the pet's stay
     */
    public void updatePet(String type, String name, int age, boolean needsDogSpace, boolean needsCatSpace, int stayDuration, BigDecimal totalAmountDue) {
        setType(type);
        setName(name);
        setAge(age);
        setNeedsDogSpace(needsDogSpace);
        setNeedsCatSpace(needsCatSpace);
        setStayDuration(stayDuration);
        setTotalAmountDue(totalAmountDue);
        System.out.println("Pet updated: " + this.toString());
    }

    /**
     * Calculates the daily rate based on the type and age of the pet.
     * 
     * @param type  the type of the pet
     * @param age   the age of the pet
     * @return the daily rate for the pet's stay
     */
    private BigDecimal calculateDailyRate(String type, int age) {
        switch (type.toLowerCase()) {
            case "dog":
                if (age < 2) {
                    return DOG_RATE_UNDER_2;
                } else if (age <= 5) {
                    return DOG_RATE_2_TO_5;
                } else {
                    return DOG_RATE_OVER_5;
                }
            case "cat":
                return CAT_RATE;
            default:
                return BigDecimal.ZERO;
        }
    }

    /**
     * Resets the stay details of the pet.
     */
    private void resetStayDetails() {
        this.stayDuration = 0;
        this.totalAmountDue = BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", needsDogSpace=" + needsDogSpace +
                ", needsCatSpace=" + needsCatSpace +
                ", stayDuration=" + stayDuration +
                ", totalAmountDue=" + totalAmountDue +
                '}';
    }
}
