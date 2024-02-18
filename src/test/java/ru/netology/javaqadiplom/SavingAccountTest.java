package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    // Пополнение на сумму, не превышающую максимальный баланс
    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    // Пополнение на сумму, превышающую максимальный баланс
    @Test
    public void shouldAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(9_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    // Пополнение на отрицательную сумму
    @Test
    public void shouldAddMinusAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    // Пополнение на нулевое значение
    @Test
    public void shouldAddZeroAmount() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    // Оплата на сумму в пределах баланса
    @Test
    public void shouldPayLessThanBalance() {
        SavingAccount account = new SavingAccount(
                7_000,
                1_000,
                10_000,
                5
        );

        account.pay(2_000);

        Assertions.assertEquals(5_000, account.getBalance());
    }

    // Оплата на сумму, превышающую баланс
    @Test
    public void shouldPayMoreThanBalance() {
        SavingAccount account = new SavingAccount(
                7_000,
                1_000,
                10_000,
                5
        );

        account.pay(8_000);

        Assertions.assertEquals(7_000, account.getBalance());
    }

    // Оплата отрицательной суммы
    @Test
    public void shouldPayMinus() {
        SavingAccount account = new SavingAccount(
                7_000,
                1_000,
                10_000,
                5
        );

        account.pay(-8_000);

        Assertions.assertEquals(7_000, account.getBalance());
    }

    // Оплата нулевого значения
    @Test
    public void shouldPayZero() {
        SavingAccount account = new SavingAccount(
                7_000,
                0,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(7000, account.getBalance());
    }

    // Начисление процентов на баланс выше нуля
    @Test
    public void shouldPayRateBalanceAboveZero() {
        SavingAccount account = new SavingAccount(
                200,
                100,
                10_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(30, account.yearChange());
    }

    // Начисление процентов на отрицательный баланс
    @Test
    public void shouldPayRateBalanceUnderZero() {
        SavingAccount account = new SavingAccount(
                2_000,
                -2_000,
                10_000,
                15
        );

        account.pay(3000);
        account.yearChange();

        Assertions.assertEquals(-150, account.yearChange());
    }

    // IllegalArgumentException при отрицательном начальном балансе
    @Test
    public void initialBalanceUnderZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    -2_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

    // IllegalArgumentException при балансе, превышающем максимальный
    @Test
    public void initialBalanceMoreThanMax() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    11_000,
                    1_000,
                    10_000,
                    5
            );
        });
    }

}