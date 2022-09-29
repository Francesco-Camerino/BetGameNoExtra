package com.example.betgamenoextra.bet;

public class BetLogic {
    private Integer amount;
    private Integer bet;
    private Boolean evenOdd;

    public BetLogic(Integer amount) {
        this.amount = amount;
    }
    public int[] bet() {
        Integer numberToBet =  (int) (Math.random() * 100) + 1;
        boolean isOdd;

        if (numberToBet % 2 != 0) {
            isOdd = true;
        } else {
            isOdd = false;
        }
        if(isOdd == evenOdd) {
           setAmount(amount + bet);
           return new int[]{1, numberToBet};
        } else {
            if (amount - bet > 0) {
                setAmount(amount - bet);
            } else {
                setAmount(0);
            }
            return new int[]{0, numberToBet};
        }
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public Boolean getEvenOdd() {
        return evenOdd;
    }

    public void setEvenOdd(Boolean evenOdd) {
        this.evenOdd = evenOdd;
    }
}
