/*
Classes to create in one project:

        Item
        CartCalculator
        StringToolkit
        DateToolkit
        MathToolkit
*/



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public final class CartCalculator {
    private CartCalculator() {}

    // Sum price * quantity; null or empty -> 0.00
    public static BigDecimal total(List<Item> items) {
        if (items == null || items.isEmpty()) return new BigDecimal("0.00");
        BigDecimal sum = BigDecimal.ZERO;
        for (Item it : items) {
            sum = sum.add(it.price().multiply(BigDecimal.valueOf(it.quantity())));
        }
        return sum.setScale(2, RoundingMode.HALF_UP);

    }

    // Apply percentage discount [0,100]; round HALF_UP to 2 decimals; invalid pct -> IllegalArgumentException
    public static BigDecimal applyDiscount(BigDecimal total, BigDecimal pct) {
        if (total == null || pct == null) throw new IllegalArgumentException("null");
        if (pct.compareTo(BigDecimal.ZERO) < 0 || pct.compareTo(new BigDecimal("100")) > 0)
            throw new IllegalArgumentException("pct");
        BigDecimal factor = BigDecimal.ONE.subtract(pct.movePointLeft(2));
        return total.multiply(factor).setScale(2, RoundingMode.HALF_UP);
    }
}





import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class DateToolkit {
    private DateToolkit() {}

    // Absolute days between dates (inclusive-exclusive): daysBetween(2024-01-01, 2024-01-02) == 1
    public static long daysBetween(LocalDate a, LocalDate b) {
        return Math.abs(ChronoUnit.DAYS.between(a, b));
    }

    // Gregorian leap year rule
    public static boolean isLeapYear(int y) {
        return (y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0));
    }

    // True if two date ranges [s1,e1] and [s2,e2] overlap (inclusive). If any start> end -> IllegalArgumentException
    public static boolean overlaps(LocalDate s1, LocalDate e1, LocalDate s2, LocalDate e2) {
        if (s1.isAfter(e1) || s2.isAfter(e2)) throw new IllegalArgumentException("bad range");
        return !e1.isBefore(s2) && !e2.isBefore(s1);
    }
}




import java.math.BigDecimal;
import java.util.Objects;

public final class Item {
    private final String name;
    private final BigDecimal price; // must be >= 0, scale up to 2 ok
    private final int quantity;     // must be >= 0

    public Item(String name, BigDecimal price, int quantity) {
        if (price == null || price.signum() < 0) throw new IllegalArgumentException("price");
        if (quantity < 0) throw new IllegalArgumentException("qty");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String name() { return name; }
    public BigDecimal price() { return price; }
    public int quantity() { return quantity; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item i)) return false;
        return quantity == i.quantity &&
                Objects.equals(name, i.name) &&
                Objects.equals(price, i.price);
    }

    @Override public int hashCode() { return Objects.hash(name, price, quantity); }
}




public final class MathToolkit {
    private MathToolkit() {}

    // Factorial for 0..12 (fits in int). n<0 or n>12 -> IllegalArgumentException
    public static int factorial(int n) {
        if (n < 0 || n > 12) throw new IllegalArgumentException("n out of range");
        int r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }

    // Mean of an int array as double; empty or null -> IllegalArgumentException
    public static double mean(int[] arr) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("empty");
        long sum = 0;
        for (int v : arr) sum += v;
        return (double) sum / arr.length;
    }

    // Clamp v to [min, max]; if min>max -> IllegalArgumentException
    public static int clamp(int v, int min, int max) {
        if (min > max) throw new IllegalArgumentException("min>max");
        if (v < min) return min;
        if (v > max) return max;
        return v;
    }
}



public final class StringToolkit {
    private StringToolkit() {}

    // Returns the reversed string; null -> null
    public static String reverse(String s) {
        if (s == null) return null;
        return new StringBuilder(s).reverse().toString();
    }

    // Counts vowels (a, e, i, o, u only), case-insensitive
    public static int countVowels(String s) {
        if (s == null) return 0;
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    // True if a and b are anagrams ignoring spaces, punctuation, and case
    public static boolean isAnagram(String a, String b) {
        if (a == null || b == null) return false;
        String ca = a.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String cb = b.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        return ca.chars().sorted().collect(StringBuilder::new,
                        StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .equals(
                        cb.chars().sorted().collect(StringBuilder::new,
                                        StringBuilder::appendCodePoint, StringBuilder::append)
                                .toString()
                );
    }
}

