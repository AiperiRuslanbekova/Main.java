package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {260, 270, 300, 200, 320, 280, 290, 250, 350};
    public static int[] heroesDamage = {10, 20, 5, 0, 3, 10, 5, 50, 50};
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Medic", "Golem", "lucky", "Berserk", "Thor"};
    public static int k = 0;


    public static void main(String[] args) {
        while (!isFinished()) {
            round();
        }

    }

    public static void round() {
        printStatistics();
        System.out.println("Round was started!");
        heroesHit();
        golemHit();
        luckyHit();
        berserkHit();
        changeBossDefence();
        bossHit();
        thorHit();
        medicHeal();
        printStatistics();

    }

    public static void printStatistics() {
        System.out.println("-----------------------");
        System.out.println("Boss health: " + bossHealth);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("Golem health: " + heroesHealth[4]);
        System.out.println("Lucky health: " + heroesHealth[5]);
        System.out.println("Berserk health: " + heroesHealth[6]);
        System.out.println("Thor health: " + heroesHealth[7]);
        System.out.println("-----------------------");
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossHealth - heroesDamage[i] < 0) bossHealth = 0;
                else bossHealth = bossHealth - heroesDamage[i];

            }
        }
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) heroesHealth[i] = 0;
                else heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }

        return false;
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss defence type: " + bossDefenceType);
    }

    public static void medicHeal() {
        for (int i = 0; i < heroesHealth.length; i++) {
            int min = heroesHealth[i];
            if (heroesHealth[i] < min) {
                k = i;
            }
        }
        if (heroesHealth[3] != 0) {
            Random random = new Random();
            int kHill = random.nextInt(25);
            heroesHealth[k] += kHill;
            System.out.println("Medic hill hero " + heroesAttackType[k] + " for " + kHill);
        }
    }

    public static void golemHit() {
        if (heroesHealth[4] != 0) {
            Random random = new Random();
            int gChance = random.nextInt(2);
            if (gChance == 1) {
                bossDamage -= bossDamage / 5;
                heroesHealth[4] -= (heroesHealth.length * bossDamage) / 5;
                System.out.println("Golem take boss damage " + gChance);
            } else {
                bossDamage = 50;

            }
        }
    }

    public static void luckyHit() {
        if (heroesHealth[5] != 0) {
            Random random = new Random();
            int lChance = random.nextInt(2);
            if (lChance == 1) {
                bossDamage = 0;
            }
        }

    }

    public static void berserkHit() {
        if (heroesHealth[6] != 0) {
            Random random = new Random();
            int bChance = random.nextInt(2);
            if (bChance == 1) {
                bossDamage = bossDamage / 2;
                heroesHealth[6] -= bossDamage / 2;
                bossHealth += heroesHealth[6];
            } else {
                bossDamage = 50;
            }

        }
    }

    public static void thorHit() {
        if (heroesHealth[7] != 0) {
            Random random = new Random();
            int tChance = random.nextInt(2);
            if (tChance == 2) {
                bossDamage = 0;
                System.out.println("Boss stunned!");
            } else bossDamage = 50;

        }
    }
}

