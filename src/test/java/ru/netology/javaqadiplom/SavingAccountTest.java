package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

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

    @Test
    public void shouldPayZero() {
        SavingAccount account = new SavingAccount(
                7_000,
                12_000,
                10_000,
                5
        );

        account.pay(0);

        Assertions.assertEquals(7_000, account.getBalance());
    }

    @Test
    public void shouldPayRateBalanceAboveZero() {
        SavingAccount account = new SavingAccount(
                2000,
                1_000,
                10_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(300, account.yearChange());
    }

    @Test
    public void shouldPayRateBalanceUnderZero() {
        SavingAccount account = new SavingAccount(
                -2000,
                1_000,
                10_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }
    
}
