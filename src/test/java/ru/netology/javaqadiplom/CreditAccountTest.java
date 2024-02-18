package ru.netology.javaqadiplom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditAccountTest {


    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        assertEquals(3_000, account.getBalance());
    }

    // проверка на положительный баланс
    @Test
    public void positiveBalance() {
        CreditAccount account = new CreditAccount(
                1_000,
                5_000,
                15
        );

        account.add(3_000);

        assertEquals(4_000, account.getBalance());
    }

    // Проверка на отрицаетльный rate

    @Test
    public void negativeRate() {
        int initialBalance = 100;
        int creditLimit = 500;
        int rate = -5;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);
        });

        assertEquals("Накопительная ставка не может быть отрицательной, а у вас: " + rate,
                exception.getMessage());
    }

    // Проверка на отрицаетльный initialBalance
    @Test
    public void negativeInitialBalance() {
        int initialBalance = -1000;
        int creditLimit = 5000;
        int rate = 10;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);
        });

        assertEquals("Начальный баланс не может быть отрицательным, а у вас: "
                + initialBalance, exception.getMessage());
    }
    // в результате выполнения теста выпадет ошибка, что ожидалось исключение,
    // но ничего не было создано

    // Проверка на отрицаетльный creditLimit
    @Test
    public void negativeСreditLimit() {
        int initialBalance = 1000;
        int creditLimit = -5000;
        int rate = 10;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);
        });

        assertEquals("Долг банку не должен быть отрицательный, а у вас: "
                + creditLimit, exception.getMessage());
    }
    // в результате выполнения теста выпадет ошибка, что ожидалось исключение,
    // но ничего не было создано


    // Тестирование метода pay

    //проверка на успешный случай, когда сумма покупки меньше баланса
    // и не превышает лимита кредита.

    @Test
    void testPayWithValidAmount() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int rate = 10;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        boolean result = account.pay(500);

        assertTrue(result);
        assertEquals(500, account.getBalance());
    }

    // проверка случая, когда сумма покупки равная нулю

    @Test
    void testPayWithZeroAmount() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int rate = 10;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        boolean result = account.pay(0);

        assertFalse(result);
        assertEquals(1000, account.getBalance());
    }

    // проверка случая, когда сумма покупки равная отрицательному значению

    @Test
    void testPayWithNegativeAmount() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int rate = 10;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        boolean result = account.pay(-500);

        assertFalse(result);
        assertEquals(1000, account.getBalance());
    }

    // сумма покупки превышает лимит кредита

    @Test
    void testPayExceedingCreditLimit() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int rate = 10;
        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        boolean result = account.pay(7000);

        assertFalse(result);
        assertEquals(1000, account.getBalance());
    }

    //проверяет, что при передаче положительного значения в метод add,
    // баланс увеличивается на указанную сумму, и возвращается true.

    @Test
    public void testAddWithValidAmount() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int rate = 10;
        int amount = 500;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        assertTrue(account.add(amount));
        assertEquals(initialBalance + amount, account.getBalance());
    }

    // проверка на отрицательную сумму покупки
    @Test
    public void testAddWithInvalidAmount() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int rate = 10;
        int amount = -500;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        assertFalse(account.add(amount));
        assertEquals(initialBalance, account.getBalance());
    }

    // проверка на то, что при сумме покупке = 0, состояние счета не изменится

    @Test
    public void testAddZeroAmount() {
        int initialBalance = 0;
        int creditLimit = 5000;
        int rate = 10;
        int amount = 0;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        assertFalse(account.add(amount));
        assertEquals(initialBalance, account.getBalance());
    }

    // расчет процентов на отрицательном балансе
    @Test
    public void testYearChangeWithNegativeBalance() {
        int initialBalance = 300;
        int creditLimit = 5000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        account.pay(500);

        int expectedInterest = -30;
        int actualInterest = account.yearChange();

        assertEquals(expectedInterest, actualInterest);
    }

    // расчет процентов на балансе равном нулю


    @Test
    public void testYearChangeWithZeroBalance() {
        int initialBalance = 0;
        int creditLimit = 5000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int expectedInterest = 0;
        int actualInterest = account.yearChange();

        assertEquals(expectedInterest, actualInterest);
    }

    // расчет процентов на положительном балансе

    @Test
    public void testYearChangeWithPositiveBalance() {
        int initialBalance = 200;
        int creditLimit = 5000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int expectedInterest = 0;
        int actualInterest = account.yearChange();

        assertEquals(expectedInterest, actualInterest);
    }

    @Test
    public void testGetCreditLimit() {
        int initialBalance = 1000;
        int creditLimit = 5000;
        int rate = 15;

        CreditAccount account = new CreditAccount(initialBalance, creditLimit, rate);

        int expectedCreditLimit = creditLimit;
        int actualCreditLimit = account.getCreditLimit();

        assertEquals(expectedCreditLimit, actualCreditLimit);
    }
}

