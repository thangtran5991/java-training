/*
 * ReadFileMain
 *
 * Version 1.0
 *
 * 12/14/2020
 *
 * Copyright thang-tran
 */
package training.java.exercise.readfile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class ReadFileMain {
    public static void main(String[] args) {
        // Create array of files
        FileList[] fileLists = new FileList[9];
        fileLists[0] = new FileList("/var/admage/log/file2.log", false);
        fileLists[1] = new FileList("/var/admage/log/file3.log", false);
        fileLists[2] = new FileList("/var/admage/log/file4.log", false);
        fileLists[3] = new FileList("/var/admage/log/file5.log", false);
        fileLists[4] = new FileList("/var/admage/log/file6.log", false);
        fileLists[5] = new FileList("/var/admage/log/file7.log", false);
        fileLists[6] = new FileList("/var/admage/log/file8.log", false);
        fileLists[7] = new FileList("/var/admage/log/file9.log", false);
        fileLists[8] = new FileList("/var/admage/log/file10.log", false);

        // Get callable return
        List<Future<List<String>>> list = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<List<String>> callable;
        for (int i = 0; i < fileLists.length; i++) {
            callable = new ReadFile(fileLists[i]);
            Future<List<String>> future = executorService.submit(callable);
            list.add(future);
        }

        // Add all to lineList
        List<String> lineList = new ArrayList<>();
        for (Future<List<String>> f : list) {
            try {
                lineList.addAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        }
        executorService.shutdown();

        // Split string line and add to LogInfo object
        LogInfo[] logInfos = new LogInfo[lineList.size()];
        for (int i = 0; i < lineList.size(); i++) {
            String[] stringLine = lineList.get(i).split("\t");
            int adId = Integer.parseInt(stringLine[0]);
            int siteId = Integer.parseInt(stringLine[1]);
            int logType = Integer.parseInt(stringLine[2]);
            int cost = Integer.parseInt(stringLine[3]);
            int wholesale = Integer.parseInt(stringLine[4]);
            for (int j = 0; j < lineList.size(); j++) {
                if (j == i) {
                    logInfos[j] = new LogInfo(adId, siteId, logType, cost, wholesale);
                }
            }
        }

        // Statistic cost with adId
        Map<Integer, Integer> costAdId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = costAdId.containsKey(logInfos[i].getAdId());

            // Check exist adId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : costAdId.entrySet()) {
                    if (logInfos[i].getAdId() == entry.getKey()) {
                        int cost = entry.getValue() + logInfos[i].getCost();
                        costAdId.replace(logInfos[i].getAdId(), cost);
                    }
                }
            } else {
                costAdId.put(logInfos[i].getAdId(), logInfos[i].getCost());
            }
        }

        // Print
        System.out.println("Ad_Id    " + "Cost");
        for (Map.Entry<Integer, Integer> entry : costAdId.entrySet()) {
            System.out.println(entry.getKey() + "        " + entry.getValue());
        }

        // Statistic wholesale with adId
        Map<Integer, Integer> wholesaleAdId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = wholesaleAdId.containsKey(logInfos[i].getAdId());

            // Check exist adId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : wholesaleAdId.entrySet()) {
                    if (logInfos[i].getAdId() == entry.getKey()) {
                        int wholesale = entry.getValue() + logInfos[i].getWholesale();
                        wholesaleAdId.replace(logInfos[i].getAdId(), wholesale);
                    }
                }
            } else {
                wholesaleAdId.put(logInfos[i].getAdId(), logInfos[i].getWholesale());
            }
        }

        // Print
        System.out.println("Ad_Id    " + "Wholesale");
        for (Map.Entry<Integer, Integer> entry : wholesaleAdId.entrySet()) {
            System.out.println(entry.getKey() + "        " + entry.getValue());
        }

        // Statistic cost with siteId
        Map<Integer, Integer> costSiteId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = costSiteId.containsKey(logInfos[i].getSiteId());

            // Check exist siteId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : costSiteId.entrySet()) {
                    if (logInfos[i].getSiteId() == entry.getKey()) {
                        int cost = entry.getValue() + logInfos[i].getCost();
                        costSiteId.replace(logInfos[i].getSiteId(), cost);
                    }
                }
            } else {
                costSiteId.put(logInfos[i].getSiteId(), logInfos[i].getCost());
            }
        }

        // Print
        System.out.println("Site_Id    " + "Cost");
        for (Map.Entry<Integer, Integer> entry : costSiteId.entrySet()) {
            System.out.println(entry.getKey() + "          " + entry.getValue());
        }

        // Statistic wholesale with siteId
        Map<Integer, Integer> wholesaleSiteId =  new HashMap<>();
        for (int i = 0; i < logInfos.length; i++) {
            Boolean check = wholesaleSiteId.containsKey(logInfos[i].getSiteId());

            // Check exist siteId in map
            if (check) {
                for (Map.Entry<Integer, Integer> entry : wholesaleSiteId.entrySet()) {
                    if (logInfos[i].getSiteId() == entry.getKey()) {
                        int wholasale = entry.getValue() + logInfos[i].getWholesale();
                        wholesaleSiteId.replace(logInfos[i].getSiteId(), wholasale);
                    }
                }
            } else {
                wholesaleSiteId.put(logInfos[i].getSiteId(), logInfos[i].getWholesale());
            }
        }

        // Print
        System.out.println("Site_Id    " + "Wholesale");
        for (Map.Entry<Integer, Integer> entry : wholesaleSiteId.entrySet()) {
            System.out.println(entry.getKey() + "          " + entry.getValue());
        }
    }
}
