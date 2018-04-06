package hcmus.fetel.nhnhan.adpro;

import java.util.ArrayList;
import java.util.List;

import hcmus.fetel.nhnhan.adpro.model.AdPackage;
import hcmus.fetel.nhnhan.adpro.model.GroupAdPack;

public class FutureAdPro {
    
    private static final String HEADER = "DAY, TOTAL CAPITAL, ADPACKS NO, DAILY PROFIT, TOTAL PROFIT, ACCOUNT BALANCE, NEW ADPACKS, REDUCE ADPACKS";
    private static final String CONTENT_FORMAT = "%,1d, %.0f $, %,1d, %.2f $, %.2f $, %.2f $, %,1d, %,1d ";
    
    private static final int LIFE_PERIOD = 120;
    private static final double ADPACK_VALUE = 50;
    private static final double RATE = 0.0095;
    
    private static int newAdPacks = 0;
    private static int reduceAdPacks = 0;
    private static int adPacksNo = 0;
    
    private static double totalCapital = 0;
    private static double dailyProfit = 0;
    private static double totalProfit = 0;
    private static double accountBalance = 0;

    public static void main(String[] args) {
        int duration = 120;
        int startAdPack = 4;
        boolean reInvest = false;
        
        adPacksNo = startAdPack;
        
        totalCapital = startAdPack * ADPACK_VALUE;
        dailyProfit = totalCapital * RATE;
        
        List<GroupAdPack> adPacks = new ArrayList<GroupAdPack>();
        adPacks.add(createGroupPackage(startAdPack, createPackage50()));
        
        System.out.println(HEADER);
        for (int day = 1; day <= duration; day++) {
            countDownDay(adPacks);
            totalProfit += dailyProfit;
            accountBalance += dailyProfit;
            if (reInvest && accountBalance >= 50 && adPacksNo < 1000) {
                double currentBalance = accountBalance;
                int missAdPack = 1000 - adPacksNo;
                do {
                    newAdPacks++;
                    currentBalance = currentBalance - 50;
                    if (newAdPacks == missAdPack) {
                        break;
                    }
                }
                while (currentBalance - 50 >= 50);
                
                accountBalance -= 50 * newAdPacks;
                if (newAdPacks != 0) {
                    adPacksNo += newAdPacks;
                    totalCapital = adPacksNo * ADPACK_VALUE;
                    dailyProfit = totalCapital * RATE;
                    
                    adPacks.add(createGroupPackage(newAdPacks, createPackage50()));
                }
                dataSummary(CONTENT_FORMAT, day, totalCapital, adPacksNo, dailyProfit, totalProfit, accountBalance, newAdPacks, reduceAdPacks);
            } else {
                dataSummary(CONTENT_FORMAT, day, totalCapital, adPacksNo, dailyProfit, totalProfit, accountBalance, newAdPacks, reduceAdPacks);
            }
            
            newAdPacks = 0;
            reduceAdPacks = 0;
        }

    }
    
    public static AdPackage createPackage50() {
        return new AdPackage(LIFE_PERIOD, ADPACK_VALUE);
    }
    
    public static GroupAdPack createGroupPackage(int numAd, AdPackage adPack) {
        return new GroupAdPack(numAd, adPack);
    }
    
    private static void dataSummary(String format, int day, double totalCapital, int adPacksNo, double dailyProfit,
            double totalProfit, double accountBalance, int newAdPacks, int reduceAdPacks) {
        System.out.printf(CONTENT_FORMAT, day, totalCapital, adPacksNo, dailyProfit, totalProfit, accountBalance, newAdPacks, reduceAdPacks);
        System.out.println();
    }
    
    private static void countDownDay(List<GroupAdPack> adPacks) {
        List<Integer> indexes = new ArrayList<Integer>();
        for (GroupAdPack currentAdPack : adPacks) {
            AdPackage adPackage = currentAdPack.getAdPackage();
            int duration = adPackage.getDuration() - 1;
            
            if (duration <= 0) {
                reduceAdPacks = currentAdPack.getVolume();
                adPacksNo -= reduceAdPacks;
                totalCapital = adPacksNo * ADPACK_VALUE;
                dailyProfit = totalCapital * RATE;
                indexes.add(adPacks.indexOf(currentAdPack));
            } else {
                adPackage.setDuration(duration);
                currentAdPack.setAdPackage(adPackage);
            }
        }
        
        for (Integer index : indexes) {
            adPacks.remove(adPacks.get(index));
        }
    }
}
