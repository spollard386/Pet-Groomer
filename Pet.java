import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public Pet() {
        this("", "", 0, false, false, 0, BigDecimal.ZERO);
    }

    public Pet(String type, String name, int age, boolean needsDogSpace, boolean needsCatSpace, int stayDuration,
            BigDecimal totalAmountDue) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.needsDogSpace = needsDogSpace;
        this.needsCatSpace = needsCatSpace;
        this.stayDuration = stayDuration;
        this.totalAmountDue = totalAmountDue;
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

    public void checkIn() {
        BigDecimal dailyRate = calculateDailyRate(type, age);
        totalAmountDue = dailyRate.multiply(BigDecimal.valueOf(stayDuration));
        System.out.println(name + " has been checked in. Initial amount due: $" + totalAmountDue);
    }

    public void checkOut() {
        System.out.println(name + " has been checked out. Total amount due: $" + totalAmountDue);
        resetStayDetails();
    }

    public String getPetDetails() {
        return this.toString();
    }

    public void createPet(String type, String name, int age, boolean needsDogSpace, boolean needsCatSpace,
            int stayDuration, BigDecimal totalAmountDue) {
        setType(type);
        setName(name);
        setAge(age);
        setNeedsDogSpace(needsDogSpace);
        setNeedsCatSpace(needsCatSpace);
        setStayDuration(stayDuration);
        setTotalAmountDue(totalAmountDue);
        System.out.println("Pet created: " + this.toString());
    }

    public void updatePet(String type, String name, int age, boolean needsDogSpace, boolean needsCatSpace,
            int stayDuration, BigDecimal totalAmountDue) {
        setType(type);
        setName(name);
        setAge(age);
        setNeedsDogSpace(needsDogSpace);
        setNeedsCatSpace(needsCatSpace);
        setStayDuration(stayDuration);
        setTotalAmountDue(totalAmountDue);
        System.out.println("Pet updated: " + this.toString());
    }

    private BigDecimal calculateDailyRate(String type, int age) {
        if (type.equalsIgnoreCase("dog")) {
            if (age < 2) {
                return DOG_RATE_UNDER_2;
            } else if (age <= 5) {
                return DOG_RATE_2_TO_5;
            } else {
                return DOG_RATE_OVER_5;
            }
        } else if (type.equalsIgnoreCase("cat")) {
            return CAT_RATE;
        }
        return BigDecimal.ZERO;
    }

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
